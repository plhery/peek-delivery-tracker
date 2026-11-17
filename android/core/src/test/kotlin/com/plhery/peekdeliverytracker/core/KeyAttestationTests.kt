package com.plhery.peekdeliverytracker.core

import java.io.ByteArrayInputStream
import java.security.MessageDigest
import java.security.cert.CertificateFactory
import java.util.Base64
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class KeyAttestationTests {
    /** A real chain, leaf first, from Google's verifier's test data (resources/android-attestation). */
    private fun chain(name: String): List<ByteArray> {
        val text = checkNotNull(javaClass.getResourceAsStream("/android-attestation/$name.pem")).bufferedReader().readText()
        return Regex("-----BEGIN CERTIFICATE-----([^-]+)-----END CERTIFICATE-----").findAll(text)
            .map { Base64.getMimeDecoder().decode(it.groupValues[1]) }
            .toList()
    }

    @Test
    fun statementIsWebAuthnsAndroidKeyFormatInCbor() {
        val long = ByteArray(300) { 7 }
        val expected = byteArrayOf(0xa2.toByte(), 0x63) + "fmt".toByteArray() + byteArrayOf(0x6b) + "android-key".toByteArray() +
            byteArrayOf(0x67) + "attStmt".toByteArray() + byteArrayOf(0xa1.toByte(), 0x63) + "x5c".toByteArray() +
            byteArrayOf(0x82.toByte(), 0x42, 1, 2, 0x59, 0x01, 0x2c) + long
        assertArrayEquals(expected, KeyAttestation.statement(listOf(byteArrayOf(1, 2), long)))
    }

    @Test
    fun keepsAskingOnLockedVerifiedPhonesAndUnlockedOnes() {
        assertFalse(KeyAttestation.stopsAsking(chain("pixel-9-pro-tee-rkp")))
        assertFalse(KeyAttestation.stopsAsking(chain("frankel-tee-ecdsa-root")))
        // An unlocked bootloader registers: the server rates it basic.
        assertFalse(KeyAttestation.stopsAsking(chain("pixel-3-unlocked-factory")))
    }

    @Test
    fun stopsAskingWhereNoServerTakesTheAttestation() {
        assertTrue(KeyAttestation.stopsAsking(chain("software-attestation")))
        // A leaf without a key description.
        assertTrue(KeyAttestation.stopsAsking(chain("frankel-tee-ecdsa-root").drop(1)))
    }

    @Test
    fun stopsAskingOnAFailedBootAloneNotOnAnUnlockedOrUnverifiedOne() {
        val leaf = chain("frankel-tee-ecdsa-root").first()
        // deviceLocked TRUE, then verifiedBootState Verified (0): the reader does not check the signature.
        val at = leaf.indexOf(byteArrayOf(0x01, 0x01, 0xff.toByte(), 0x0a, 0x01, 0x00))
        assertTrue(at > 0)
        fun withState(state: Byte) = listOf(leaf.copyOf().also { it[at + 5] = state })
        assertFalse(KeyAttestation.stopsAsking(withState(1)))
        assertFalse(KeyAttestation.stopsAsking(withState(2)))
        assertTrue(KeyAttestation.stopsAsking(withState(3)))
        assertFalse(KeyAttestation.stopsAsking(listOf(leaf.copyOf().also { it[at + 2] = 0 })))
    }

    @Test
    fun leavesWhatItCannotReadToTheServer() {
        assertFalse(KeyAttestation.stopsAsking(listOf(byteArrayOf(1, 2, 3))))
        assertNull(KeyAttestation.leafPublicKey(listOf(byteArrayOf(1, 2, 3))))
    }

    @Test
    fun theRequestHashBindsTheChallengeToTheLeafsKey() {
        // The server's vector, src/server/playIntegrity.test.ts.
        val spki = Base64.getDecoder().decode(
            "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEOq/HCxVr3bwzRO3OycTUOuUFQ/r2FWAPCQcBdDBRqymSZFeW2MENv50nL2Z819BN45Vsd4OYLkEIm9RAJRLaSg==",
        )
        val challenge = "1767225600." + "A".repeat(43) + "." + "B".repeat(43)
        val clientDataHash = MessageDigest.getInstance("SHA-256").digest(challenge.toByteArray())
        assertEquals("f2d62029b5109a52abe18297cc3d0da6a7b3b1895106646f182f94fd54004841", clientDataHash.joinToString("") { "%02x".format(it) })
        assertEquals("9fbaba741e5504030f4b802299131c261574dc3a5b9a2b751db4595a2f4fee72", KeyAttestation.requestHash(clientDataHash, spki))
    }

    @Test
    fun theLeafsKeyIsItsSubjectPublicKeyInfo() {
        val leaf = chain("pixel-9-pro-tee-rkp")
        val key = checkNotNull(KeyAttestation.leafPublicKey(leaf))
        val expected = CertificateFactory.getInstance("X.509").generateCertificate(ByteArrayInputStream(leaf.first())).publicKey.encoded
        assertArrayEquals(expected, key)
        // EC P-256: 91 bytes of DER.
        assertEquals(91, key.size)
    }

    private fun ByteArray.indexOf(part: ByteArray): Int =
        (0..size - part.size).firstOrNull { start -> part.indices.all { this[start + it] == part[it] } } ?: -1
}
