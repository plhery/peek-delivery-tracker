package com.plhery.peekdeliverytracker.core

import java.util.Base64
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertFalse
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
    fun keepsAskingOnLockedVerifiedPhones() {
        assertFalse(KeyAttestation.refused(chain("pixel-9-pro-tee-rkp")))
        assertFalse(KeyAttestation.refused(chain("frankel-tee-ecdsa-root")))
    }

    @Test
    fun stopsAskingWhereTheServerIsSureToRefuse() {
        assertTrue(KeyAttestation.refused(chain("pixel-3-unlocked-factory")))
        assertTrue(KeyAttestation.refused(chain("software-attestation")))
        // A leaf without a key description.
        assertTrue(KeyAttestation.refused(chain("frankel-tee-ecdsa-root").drop(1)))
    }

    @Test
    fun leavesSelfSignedSystemsToTheServerButNotAFailedBoot() {
        val leaf = chain("frankel-tee-ecdsa-root").first()
        // deviceLocked TRUE, then verifiedBootState Verified (0): the reader does not check the signature.
        val at = leaf.indexOf(byteArrayOf(0x01, 0x01, 0xff.toByte(), 0x0a, 0x01, 0x00))
        assertTrue(at > 0)
        fun withState(state: Byte) = listOf(leaf.copyOf().also { it[at + 5] = state })
        assertFalse(KeyAttestation.refused(withState(1)))
        assertTrue(KeyAttestation.refused(withState(2)))
        assertTrue(KeyAttestation.refused(withState(3)))
    }

    @Test
    fun leavesWhatItCannotReadToTheServer() {
        assertFalse(KeyAttestation.refused(listOf(byteArrayOf(1, 2, 3))))
    }

    private fun ByteArray.indexOf(part: ByteArray): Int =
        (0..size - part.size).firstOrNull { start -> part.indices.all { this[start + it] == part[it] } } ?: -1
}
