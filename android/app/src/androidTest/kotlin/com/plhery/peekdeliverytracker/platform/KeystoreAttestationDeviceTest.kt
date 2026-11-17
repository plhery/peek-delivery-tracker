package com.plhery.peekdeliverytracker.platform

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.KeyAttestation
import com.plhery.peekdeliverytracker.core.NativeVerificationFailed
import com.plhery.peekdeliverytracker.core.UserDefaults
import java.io.ByteArrayInputStream
import java.security.MessageDigest
import java.security.Signature
import java.security.cert.CertificateFactory
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * What only a device can show of KeystoreAttestation: the Keystore itself makes the key with the challenge (in StrongBox
 * where the phone has one), writes its certificate chain and signs. Where no server takes that chain without Play
 * Integrity's word (KeyAttestation.stopsAsking), the app stops asking and the browser check stands in.
 */
@RunWith(AndroidJUnit4::class)
class KeystoreAttestationDeviceTest {
    private val alias = KeystoreAttestation.ALIAS_PREFIX + "device-test"
    private val challenge = MessageDigest.getInstance("SHA-256").digest("synthetic challenge".toByteArray())
    private val keys = AndroidAttestationKeys(InstrumentationRegistry.getInstrumentation().targetContext)

    @After
    fun deleteTheKeys() {
        for (each in keys.aliases()) {
            if (each.startsWith(KeystoreAttestation.ALIAS_PREFIX)) keys.delete(each)
        }
    }

    @Test
    fun theKeystoreAttestsAP256KeyWithTheChallengeAndSignsWithIt() {
        val chain = keys.generate(alias, challenge)
        assertTrue(chain.size >= 2)
        val factory = CertificateFactory.getInstance("X.509")
        val leaf = factory.generateCertificate(ByteArrayInputStream(chain.first()))
        assertEquals("EC", leaf.publicKey.algorithm)
        // Every certificate signed by the next, as the server checks.
        for (index in 0 until chain.size - 1) {
            factory.generateCertificate(ByteArrayInputStream(chain[index]))
                .verify(factory.generateCertificate(ByteArrayInputStream(chain[index + 1])).publicKey)
        }

        val data = MessageDigest.getInstance("SHA-256").digest("native-request-v1\nsynthetic".toByteArray())
        val signature = Signature.getInstance(KeystoreAttestation.SIGNATURE).run {
            initSign(keys.privateKey(alias))
            update(data)
            sign()
        }
        val verifier = Signature.getInstance(KeystoreAttestation.SIGNATURE).apply {
            initVerify(leaf.publicKey)
            update(data)
        }
        assertTrue(verifier.verify(signature))

        keys.delete(alias)
        assertNull(keys.privateKey(alias))
    }

    @Test
    fun aKeyRegistersOrStopsAskingAsItsAttestationSays() = runBlocking {
        val defaults = object : UserDefaults {
            val strings = mutableMapOf<String, String>()
            override fun bool(forKey: String) = false
            override fun set(value: Boolean, forKey: String) = Unit
            override fun data(forKey: String): ByteArray? = null
            override fun set(value: ByteArray, forKey: String) = Unit
            override fun string(forKey: String) = strings[forKey]
            override fun set(value: String, forKey: String) {
                strings[forKey] = value
            }
            override fun removeObject(forKey: String) {
                strings.remove(forKey)
            }
            override fun hasObject(forKey: String) = forKey in strings
        }
        val stops = KeyAttestation.stopsAsking(keys.generate(alias, challenge))
        val attestation = KeystoreAttestation(defaults, keys = keys)
        assertTrue(attestation.supported())
        val keyId = attestation.generateKey()
        try {
            val statement = attestation.attest(keyId, challenge, null)
            assertFalse("a key no server takes was sent", stops)
            assertNull(statement.playIntegrity)
            assertTrue(attestation.supported())
            assertEquals(1, keys.aliases().count { it.startsWith(KeystoreAttestation.ALIAS_PREFIX) })
        } catch (_: NativeVerificationFailed) {
            assertTrue("a key a server takes was not sent", stops)
            assertFalse(attestation.supported())
            assertTrue(keys.aliases().none { it.startsWith(KeystoreAttestation.ALIAS_PREFIX) })
        }
    }
}
