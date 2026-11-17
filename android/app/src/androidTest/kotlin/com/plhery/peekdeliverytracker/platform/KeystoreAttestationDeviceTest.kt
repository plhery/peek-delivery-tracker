package com.plhery.peekdeliverytracker.platform

import androidx.test.ext.junit.runners.AndroidJUnit4
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
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith

/**
 * What only a device can show of KeystoreAttestation: the Keystore itself makes the key with the challenge, writes its
 * certificate chain and signs. An emulator's keys are software ones, which the server refuses, so there the app stops
 * asking and the browser check stands in.
 */
@RunWith(AndroidJUnit4::class)
class KeystoreAttestationDeviceTest {
    private val alias = KeystoreAttestation.ALIAS_PREFIX + "device-test"
    private val challenge = MessageDigest.getInstance("SHA-256").digest("synthetic challenge".toByteArray())

    @After
    fun deleteTheKeys() {
        for (each in AndroidAttestationKeys.aliases()) {
            if (each.startsWith(KeystoreAttestation.ALIAS_PREFIX)) AndroidAttestationKeys.delete(each)
        }
    }

    @Test
    fun theKeystoreAttestsAP256KeyWithTheChallengeAndSignsWithIt() {
        val chain = AndroidAttestationKeys.generate(alias, challenge)
        assertTrue(chain.size >= 2)
        val factory = CertificateFactory.getInstance("X.509")
        val leaf = factory.generateCertificate(ByteArrayInputStream(chain.first()))
        assertEquals("EC", leaf.publicKey.algorithm)
        // Every certificate signed by the next, as the server checks.
        for (index in 0 until chain.size - 1) {
            factory.generateCertificate(ByteArrayInputStream(chain[index]))
                .verify(factory.generateCertificate(ByteArrayInputStream(chain[index + 1])).publicKey)
        }
        if (Device.isEmulator) assertTrue(KeyAttestation.refused(chain))

        val data = MessageDigest.getInstance("SHA-256").digest("native-request-v1\nsynthetic".toByteArray())
        val signature = Signature.getInstance(KeystoreAttestation.SIGNATURE).run {
            initSign(AndroidAttestationKeys.privateKey(alias))
            update(data)
            sign()
        }
        val verifier = Signature.getInstance(KeystoreAttestation.SIGNATURE).apply {
            initVerify(leaf.publicKey)
            update(data)
        }
        assertTrue(verifier.verify(signature))

        AndroidAttestationKeys.delete(alias)
        assertNull(AndroidAttestationKeys.privateKey(alias))
    }

    @Test
    fun anEmulatorStopsAskingAndLeavesNoKey() = runBlocking {
        if (!Device.isEmulator) return@runBlocking
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
        val attestation = KeystoreAttestation(defaults)
        assertTrue(attestation.supported())
        val keyId = attestation.generateKey()
        try {
            attestation.attest(keyId, challenge)
            fail("an emulator's key was sent")
        } catch (_: NativeVerificationFailed) {
        }
        assertFalse(attestation.supported())
        assertTrue(AndroidAttestationKeys.aliases().none { it.startsWith(KeystoreAttestation.ALIAS_PREFIX) })
    }
}
