package com.plhery.peekdeliverytracker.platform

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.plhery.peekdeliverytracker.BuildConfig
import com.plhery.peekdeliverytracker.core.DeviceAttestation
import com.plhery.peekdeliverytracker.core.KeyAttestation
import com.plhery.peekdeliverytracker.core.NativeVerificationFailed
import com.plhery.peekdeliverytracker.core.PlayIntegrityTokens
import com.plhery.peekdeliverytracker.core.UserDefaults
import java.security.GeneralSecurityException
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.ProviderException
import java.security.SecureRandom
import java.security.Signature
import java.security.spec.ECGenParameterSpec
import java.util.Base64
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * App Attest's Android counterpart (DeviceAttestation), in both builds: a P-256 signing key made in the phone's secure
 * hardware (StrongBox where it has one, else the TEE), which attests it with the server's challenge in a certificate
 * chain up to Google's roots (src/server/androidKeyAttestation.ts checks it), then signs each request as App Attest's
 * assertions do. The Play build adds Play Integrity's token for the same challenge and key ([tokens]) where the server
 * gives a Cloud project; the F-Droid build has no Google library and sends the attestation alone.
 *
 * One key per installation, under an alias of its own: a new key replaces the last. Without a token, a system whose
 * attestation no server takes (an emulator's software keys, a failed boot: KeyAttestation.stopsAsking) is remembered by
 * its build fingerprint, and the app stops asking there until the system changes; so does a build the server refused
 * 3 times in a row, until the system or the app changes. The browser check stands in.
 */
class KeystoreAttestation(
    private val defaults: UserDefaults,
    /** Play Integrity's tokens, in the Play build. */
    private val tokens: PlayIntegrityTokens? = null,
    private val keys: AttestationKeys = AndroidAttestationKeys(strongBox = false),
    /** This system's build, `Build.FINGERPRINT`. */
    private val system: String = Build.FINGERPRINT.orEmpty(),
    /** This app's build, its versionCode. */
    private val version: Long = BuildConfig.VERSION_CODE.toLong(),
    private val random: SecureRandom = SecureRandom(),
    /** Where the Keystore is asked, off the main thread. */
    private val io: CoroutineContext = Dispatchers.IO,
) : DeviceAttestation {
    override fun supported(): Boolean = defaults.string(STOPPED_SYSTEM) != system && refusals() < MAX_REFUSALS

    override suspend fun generateKey(): String = withContext(io) {
        for (alias in keys.aliases()) {
            if (alias.startsWith(ALIAS_PREFIX)) runCatching { keys.delete(alias) }
        }
        Base64.getEncoder().encodeToString(ByteArray(32).also(random::nextBytes))
    }

    override suspend fun prepare(playIntegrityProject: Long) {
        tokens?.prepare(playIntegrityProject)
    }

    override suspend fun attest(keyId: String, clientDataHash: ByteArray, playIntegrityProject: Long?): DeviceAttestation.Statement {
        val alias = alias(keyId)
        val chain = withContext(io) { keys.generate(alias, clientDataHash) }
        val token = if (tokens != null && playIntegrityProject != null) {
            KeyAttestation.leafPublicKey(chain)?.let { tokens.token(playIntegrityProject, KeyAttestation.requestHash(clientDataHash, it)) }
        } else {
            null
        }
        // Play Integrity can vouch for a phone whose keystore can't.
        if (token == null && KeyAttestation.stopsAsking(chain)) {
            defaults.set(system, STOPPED_SYSTEM)
            withContext(io) { runCatching { keys.delete(alias) } }
            throw NativeVerificationFailed()
        }
        return DeviceAttestation.Statement(KeyAttestation.statement(chain), token)
    }

    override suspend fun assert(keyId: String, clientDataHash: ByteArray): ByteArray = withContext(io) {
        val key = try {
            keys.privateKey(alias(keyId))
        } catch (_: GeneralSecurityException) {
            null
        } ?: throw DeviceAttestation.InvalidKey()
        try {
            Signature.getInstance(SIGNATURE).run {
                initSign(key)
                update(clientDataHash)
                sign()
            }
        } catch (_: java.security.InvalidKeyException) {
            // A key the system invalidated.
            throw DeviceAttestation.InvalidKey()
        }
    }

    /** Counts the server's refusals in a row on this build of the system and the app; a registration clears them. */
    override fun registered(accepted: Boolean) {
        if (accepted) defaults.removeObject(REFUSALS) else defaults.set("${refusals() + 1} $build", REFUSALS)
    }

    private val build: String get() = "$version $system"

    private fun refusals(): Int {
        val saved = defaults.string(REFUSALS) ?: return 0
        return if (saved.substringAfter(' ') == build) saved.substringBefore(' ').toIntOrNull() ?: 0 else 0
    }

    /** The key id is random base64; its alias keeps Keystore's alias characters plain. */
    private fun alias(keyId: String): String = ALIAS_PREFIX + Base64.getUrlEncoder().withoutPadding().encodeToString(Base64.getDecoder().decode(keyId))

    companion object {
        const val ALIAS_PREFIX = "peek.attestation."
        const val STOPPED_SYSTEM = "sdt.keyAttestation.stoppedSystem"
        const val REFUSALS = "sdt.keyAttestation.refusals"
        const val MAX_REFUSALS = 3
        const val SIGNATURE = "SHA256withECDSA"
    }
}

/** Where attested keys live: the Android Keystore in the app, a software stand-in in tests. */
interface AttestationKeys {
    /** A new key under `alias`, replacing any there, attested with `challenge`: its certificate chain, leaf first. */
    fun generate(alias: String, challenge: ByteArray): List<ByteArray>

    /** The private key under `alias`, or nil when there is none. */
    fun privateKey(alias: String): PrivateKey?

    fun aliases(): List<String>

    fun delete(alias: String)
}

/**
 * The Android Keystore's attested keys: EC P-256, signing with SHA-256 alone, made in StrongBox where the phone has one
 * ([strongBox]) and in the TEE where it hasn't or StrongBox can't make it. No user authentication: requests are signed
 * in the background too.
 */
class AndroidAttestationKeys(
    private val strongBox: Boolean,
    /** Makes the key a spec describes: the Keystore's generator. */
    private val make: (KeyGenParameterSpec) -> Unit = ::makeKey,
) : AttestationKeys {
    /** On a phone whose Keystore has StrongBox, `PackageManager.FEATURE_STRONGBOX_KEYSTORE`. */
    constructor(context: Context) : this(context.packageManager.hasSystemFeature(PackageManager.FEATURE_STRONGBOX_KEYSTORE))

    override fun generate(alias: String, challenge: ByteArray): List<ByteArray> {
        made(alias, challenge)
        val chain = keyStore().getCertificateChain(alias)
        if (chain.isNullOrEmpty()) throw GeneralSecurityException("No attestation")
        return chain.map { it.encoded }
    }

    /** Makes the key under [alias]; whether in StrongBox. */
    internal fun made(alias: String, challenge: ByteArray): Boolean {
        if (strongBox) {
            try {
                make(spec(alias, challenge, strongBox = true))
                return true
            } catch (_: ProviderException) {
                // StrongBoxUnavailableException, or a StrongBox that can't attest this key: the TEE makes it.
            }
        }
        make(spec(alias, challenge, strongBox = false))
        return false
    }

    override fun privateKey(alias: String): PrivateKey? = keyStore().getKey(alias, null) as? PrivateKey

    override fun aliases(): List<String> = keyStore().aliases().toList()

    override fun delete(alias: String) {
        keyStore().deleteEntry(alias)
    }

    companion object {
        private const val PROVIDER = "AndroidKeyStore"

        private fun keyStore(): KeyStore = KeyStore.getInstance(PROVIDER).apply { load(null) }

        private fun makeKey(spec: KeyGenParameterSpec) {
            KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_EC, PROVIDER).run {
                initialize(spec)
                generateKeyPair()
            }
        }

        fun spec(alias: String, challenge: ByteArray, strongBox: Boolean): KeyGenParameterSpec =
            KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_SIGN)
                .setAlgorithmParameterSpec(ECGenParameterSpec("secp256r1"))
                .setDigests(KeyProperties.DIGEST_SHA256)
                .setAttestationChallenge(challenge)
                .setIsStrongBoxBacked(strongBox)
                .build()
    }
}
