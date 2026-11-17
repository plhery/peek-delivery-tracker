package com.plhery.peekdeliverytracker.platform

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.plhery.peekdeliverytracker.core.DeviceAttestation
import com.plhery.peekdeliverytracker.core.KeyAttestation
import com.plhery.peekdeliverytracker.core.NativeVerificationFailed
import com.plhery.peekdeliverytracker.core.UserDefaults
import java.security.GeneralSecurityException
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.SecureRandom
import java.security.Signature
import java.security.spec.ECGenParameterSpec
import java.util.Base64
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * App Attest's Android counterpart (DeviceAttestation), in both builds and without Google's services: a P-256 signing
 * key made in the phone's secure hardware, which attests it with the server's challenge in a certificate chain up to
 * Google's roots (src/server/androidKeyAttestation.ts checks it), then signs each request as App Attest's assertions do.
 *
 * One key per installation, under an alias of its own: a new key replaces the last. A system the server is sure to
 * refuse (an emulator's software keys, an unlocked bootloader: KeyAttestation.refused) is remembered by its build
 * fingerprint, and the app stops asking there until the system changes; the browser check stands in.
 */
class KeystoreAttestation(
    private val defaults: UserDefaults,
    private val keys: AttestationKeys = AndroidAttestationKeys,
    /** This system's build, `Build.FINGERPRINT`. */
    private val system: String = Build.FINGERPRINT.orEmpty(),
    private val random: SecureRandom = SecureRandom(),
    /** Where the Keystore is asked, off the main thread. */
    private val io: CoroutineContext = Dispatchers.IO,
) : DeviceAttestation {
    override fun supported(): Boolean = defaults.string(REFUSED_SYSTEM) != system

    override suspend fun generateKey(): String = withContext(io) {
        for (alias in keys.aliases()) {
            if (alias.startsWith(ALIAS_PREFIX)) runCatching { keys.delete(alias) }
        }
        Base64.getEncoder().encodeToString(ByteArray(32).also(random::nextBytes))
    }

    override suspend fun attest(keyId: String, clientDataHash: ByteArray): ByteArray = withContext(io) {
        val alias = alias(keyId)
        val chain = keys.generate(alias, clientDataHash)
        if (KeyAttestation.refused(chain)) {
            defaults.set(system, REFUSED_SYSTEM)
            runCatching { keys.delete(alias) }
            throw NativeVerificationFailed()
        }
        KeyAttestation.statement(chain)
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

    /** The key id is random base64; its alias keeps Keystore's alias characters plain. */
    private fun alias(keyId: String): String = ALIAS_PREFIX + Base64.getUrlEncoder().withoutPadding().encodeToString(Base64.getDecoder().decode(keyId))

    companion object {
        const val ALIAS_PREFIX = "peek.attestation."
        const val REFUSED_SYSTEM = "sdt.keyAttestation.refusedSystem"
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
 * The Android Keystore's attested keys: EC P-256, signing with SHA-256 alone, made in its secure hardware (the TEE, or
 * StrongBox where the system puts them). No user authentication: requests are signed in the background too.
 */
object AndroidAttestationKeys : AttestationKeys {
    private const val PROVIDER = "AndroidKeyStore"

    private fun keyStore(): KeyStore = KeyStore.getInstance(PROVIDER).apply { load(null) }

    override fun generate(alias: String, challenge: ByteArray): List<ByteArray> {
        val generator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_EC, PROVIDER)
        generator.initialize(spec(alias, challenge))
        generator.generateKeyPair()
        val chain = keyStore().getCertificateChain(alias)
        if (chain.isNullOrEmpty()) throw GeneralSecurityException("No attestation")
        return chain.map { it.encoded }
    }

    override fun privateKey(alias: String): PrivateKey? = keyStore().getKey(alias, null) as? PrivateKey

    override fun aliases(): List<String> = keyStore().aliases().toList()

    override fun delete(alias: String) {
        keyStore().deleteEntry(alias)
    }

    fun spec(alias: String, challenge: ByteArray): KeyGenParameterSpec =
        KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_SIGN)
            .setAlgorithmParameterSpec(ECGenParameterSpec("secp256r1"))
            .setDigests(KeyProperties.DIGEST_SHA256)
            .setAttestationChallenge(challenge)
            .build()
}
