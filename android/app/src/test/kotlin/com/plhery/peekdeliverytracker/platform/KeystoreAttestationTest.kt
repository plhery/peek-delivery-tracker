package com.plhery.peekdeliverytracker.platform

import android.security.keystore.KeyProperties
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.DeviceAttestation
import com.plhery.peekdeliverytracker.core.KeyAttestation
import com.plhery.peekdeliverytracker.core.NativeVerificationFailed
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.MessageDigest
import java.security.PrivateKey
import java.security.Signature
import java.security.spec.ECGenParameterSpec
import java.util.Base64
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith

/**
 * KeystoreAttestation over a software stand-in for the Keystore, which hands back real chains from Google's verifier's
 * test data (resources/android-attestation): Robolectric has no Keystore. What this cannot show is the Keystore's own
 * part: the key made in secure hardware, and the chain it writes; the server's tests check those chains.
 */
@RunWith(AndroidJUnit4::class)
class KeystoreAttestationTest {
    private fun chain(name: String): List<ByteArray> {
        val text = checkNotNull(javaClass.getResourceAsStream("/android-attestation/$name.pem")).bufferedReader().readText()
        return Regex("-----BEGIN CERTIFICATE-----([^-]+)-----END CERTIFICATE-----").findAll(text)
            .map { Base64.getMimeDecoder().decode(it.groupValues[1]) }
            .toList()
    }

    /** The Keystore's stand-in: software P-256 keys, each "attested" by the chain it is given. */
    private class Keys(var chain: List<ByteArray>) : AttestationKeys {
        val pairs = linkedMapOf<String, KeyPair>()
        val challenges = mutableListOf<ByteArray>()

        override fun generate(alias: String, challenge: ByteArray): List<ByteArray> {
            challenges += challenge
            pairs[alias] = KeyPairGenerator.getInstance("EC").apply { initialize(ECGenParameterSpec("secp256r1")) }.generateKeyPair()
            return chain
        }

        override fun privateKey(alias: String): PrivateKey? = pairs[alias]?.private

        override fun aliases(): List<String> = pairs.keys.toList()

        override fun delete(alias: String) {
            pairs.remove(alias)
        }
    }

    private val defaults = MemoryDefaults()
    private val hash = MessageDigest.getInstance("SHA-256").digest("synthetic challenge".toByteArray())

    private fun attestation(keys: Keys, system: String = "synthetic/phone:17/1:user/release-keys") =
        KeystoreAttestation(defaults, keys, system, io = EmptyCoroutineContext)

    @Test
    fun attestsANewKeyWithTheChallengeAndSendsItsChain() = runBlocking {
        val good = chain("frankel-tee-ecdsa-root")
        val keys = Keys(good)
        val attestation = attestation(keys)
        assertTrue(attestation.supported())
        val keyId = attestation.generateKey()
        assertEquals(32, Base64.getDecoder().decode(keyId).size)
        assertNotEquals(keyId, attestation.generateKey())

        assertArrayEquals(KeyAttestation.statement(good), attestation.attest(keyId, hash))
        assertArrayEquals(hash, keys.challenges.single())
        assertEquals(listOf(KeystoreAttestation.ALIAS_PREFIX + Base64.getUrlEncoder().withoutPadding().encodeToString(Base64.getDecoder().decode(keyId))), keys.aliases())
        assertTrue(attestation.supported())
    }

    @Test
    fun signsWithTheKeyAsTheServerChecks() = runBlocking {
        val keys = Keys(chain("frankel-tee-ecdsa-root"))
        val attestation = attestation(keys)
        val keyId = attestation.generateKey()
        attestation.attest(keyId, hash)
        val payload = MessageDigest.getInstance("SHA-256").digest("native-request-v1\nsynthetic".toByteArray())
        val assertion = attestation.assert(keyId, payload)
        val verifier = Signature.getInstance("SHA256withECDSA").apply {
            initVerify(keys.pairs.values.single().public)
            update(payload)
        }
        assertTrue(verifier.verify(assertion))
    }

    @Test
    fun aKeyItNoLongerHasIsInvalid() = runBlocking {
        val attestation = attestation(Keys(chain("frankel-tee-ecdsa-root")))
        try {
            attestation.assert(Base64.getEncoder().encodeToString(ByteArray(32)), hash)
            fail("signed without a key")
        } catch (_: DeviceAttestation.InvalidKey) {
        }
    }

    @Test
    fun aNewKeyReplacesTheLastAndLeavesTheOtherStoresKeys() = runBlocking {
        val keys = Keys(chain("frankel-tee-ecdsa-root"))
        keys.pairs["peek.secret.com.plhery.PeekDeliveryTracker.auth"] = KeyPairGenerator.getInstance("EC").generateKeyPair()
        val attestation = attestation(keys)
        val first = attestation.generateKey()
        attestation.attest(first, hash)
        attestation.generateKey()
        assertEquals(listOf("peek.secret.com.plhery.PeekDeliveryTracker.auth"), keys.aliases())
    }

    @Test
    fun stopsAskingOnASystemTheServerIsSureToRefuseUntilItChanges() = runBlocking {
        val keys = Keys(chain("pixel-3-unlocked-factory"))
        val attestation = attestation(keys)
        val keyId = attestation.generateKey()
        try {
            attestation.attest(keyId, hash)
            fail("attested an unlocked bootloader")
        } catch (_: NativeVerificationFailed) {
        }
        assertFalse(attestation.supported())
        assertTrue(keys.aliases().isEmpty())
        // An update of the system asks once more.
        assertTrue(attestation(keys, system = "synthetic/phone:17/2:user/release-keys").supported())
    }

    @Test
    fun theKeystoreKeyIsAP256SigningKeyAttestedWithTheChallenge() {
        val spec = AndroidAttestationKeys.spec("peek.attestation.synthetic", hash)
        assertEquals("peek.attestation.synthetic", spec.keystoreAlias)
        assertEquals(KeyProperties.PURPOSE_SIGN, spec.purposes)
        assertEquals(listOf(KeyProperties.DIGEST_SHA256), spec.digests.toList())
        assertEquals("secp256r1", (spec.algorithmParameterSpec as ECGenParameterSpec).name)
        assertArrayEquals(hash, spec.attestationChallenge)
        assertFalse(spec.isUserAuthenticationRequired)
    }
}
