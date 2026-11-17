package com.plhery.peekdeliverytracker.core

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.security.MessageDigest
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate

/**
 * What the Android app sends to register a key the phone's secure hardware attests (KeystoreAttestation), and what it
 * reads of the attestation before it does: src/server/androidKeyAttestation.ts verifies it.
 */
object KeyAttestation {
    /** Android's key description extension, in the leaf certificate. */
    private const val KEY_DESCRIPTION_OID = "1.3.6.1.4.1.11129.2.1.17"

    /**
     * The registration's attestation: the CBOR map `{"fmt": "android-key", "attStmt": {"x5c": [leaf, …, root]}}`, as
     * WebAuthn's android-key statement carries a certificate chain, each certificate in DER.
     */
    fun statement(chain: List<ByteArray>): ByteArray {
        val out = ByteArrayOutputStream()
        out.head(5, 2)
        out.text("fmt")
        out.text("android-key")
        out.text("attStmt")
        out.head(5, 1)
        out.text("x5c")
        out.head(4, chain.size.toLong())
        for (certificate in chain) {
            out.head(2, certificate.size.toLong())
            out.write(certificate)
        }
        return out.toByteArray()
    }

    /**
     * Whether the key `chain` (leaf first) attests is one no server would take without Play Integrity's word: a leaf
     * without a key description, an attestation made in software (an emulator's, a broken keystore's) or a failed boot.
     * Then the app stops asking on that system, and the browser check stands in. An unlocked bootloader and an
     * unverified or self-signed boot register (the server rates them basic), and what it cannot read the server judges.
     */
    fun stopsAsking(chain: List<ByteArray>): Boolean {
        val leaf = leaf(chain) ?: return false
        val extension = leaf.getExtensionValue(KEY_DESCRIPTION_OID) ?: return true
        return try {
            stopsAskingDescription(Der.read(extension).expect(UNIVERSAL, OCTET_STRING).body)
        } catch (_: Exception) {
            false
        }
    }

    /** The leaf's public key as X.509's SubjectPublicKeyInfo, in DER; null where the leaf can't be read. */
    fun leafPublicKey(chain: List<ByteArray>): ByteArray? = leaf(chain)?.publicKey?.encoded

    /**
     * The request hash a Play Integrity token for this registration carries, which binds it to the challenge and the
     * key: the SHA-256 of `clientDataHash` (the challenge's SHA-256) then the leaf's public key, in lowercase hex.
     */
    fun requestHash(clientDataHash: ByteArray, leafPublicKey: ByteArray): String =
        MessageDigest.getInstance("SHA-256").run {
            update(clientDataHash)
            update(leafPublicKey)
            digest()
        }.joinToString("") { "%02x".format(it) }

    private fun leaf(chain: List<ByteArray>): X509Certificate? = try {
        CertificateFactory.getInstance("X.509").generateCertificate(ByteArrayInputStream(chain.first())) as X509Certificate
    } catch (_: Exception) {
        null
    }

    private fun stopsAskingDescription(keyDescription: ByteArray): Boolean {
        val description = Der.read(keyDescription).expect(UNIVERSAL, SEQUENCE).children()
        if (description[1].expect(UNIVERSAL, ENUMERATED).number() == SOFTWARE) return true
        val trust = description[7].expect(UNIVERSAL, SEQUENCE).children().firstOrNull { it.tagClass == CONTEXT && it.tag == ROOT_OF_TRUST }
            ?: return false
        val root = trust.children().single().expect(UNIVERSAL, SEQUENCE).children()
        return root[2].expect(UNIVERSAL, ENUMERATED).number() == FAILED
    }

    private const val UNIVERSAL = 0
    private const val CONTEXT = 2
    private const val OCTET_STRING = 4
    private const val ENUMERATED = 10
    private const val SEQUENCE = 16
    private const val ROOT_OF_TRUST = 704
    private const val SOFTWARE = 0L
    private const val FAILED = 3L

    /** A CBOR head: the major type and its count or length. */
    private fun ByteArrayOutputStream.head(major: Int, value: Long) {
        val type = major shl 5
        when {
            value < 24 -> write(type or value.toInt())
            value < 0x100 -> {
                write(type or 24)
                write(value.toInt())
            }
            value < 0x10000 -> {
                write(type or 25)
                for (shift in intArrayOf(8, 0)) write((value shr shift).toInt() and 0xff)
            }
            else -> {
                write(type or 26)
                for (shift in intArrayOf(24, 16, 8, 0)) write((value shr shift).toInt() and 0xff)
            }
        }
    }

    private fun ByteArrayOutputStream.text(value: String) {
        val bytes = value.toByteArray(Charsets.UTF_8)
        head(3, bytes.size.toLong())
        write(bytes)
    }

    /** A DER element, read strictly: definite lengths only. */
    private class Der(val tagClass: Int, val constructed: Boolean, val tag: Int, val body: ByteArray) {
        fun expect(tagClass: Int, tag: Int): Der {
            require(this.tagClass == tagClass && this.tag == tag)
            return this
        }

        fun number(): Long {
            require(body.size in 1..6 && body[0] >= 0)
            return body.fold(0L) { value, byte -> value * 256 + (byte.toInt() and 0xff) }
        }

        fun children(): List<Der> {
            require(constructed)
            val items = mutableListOf<Der>()
            var at = 0
            while (at < body.size) {
                val (item, end) = parse(body, at)
                items += item
                at = end
            }
            return items
        }

        companion object {
            /** The one element `bytes` holds. */
            fun read(bytes: ByteArray): Der {
                val (item, end) = parse(bytes, 0)
                require(end == bytes.size)
                return item
            }

            fun parse(bytes: ByteArray, offset: Int): Pair<Der, Int> {
                var at = offset
                fun next(): Int {
                    require(at < bytes.size)
                    return bytes[at++].toInt() and 0xff
                }
                val first = next()
                var tag = first and 0x1f
                if (tag == 0x1f) {
                    tag = 0
                    var count = 0
                    do {
                        require(count++ < 4)
                        val byte = next()
                        tag = tag * 128 + (byte and 0x7f)
                    } while (byte and 0x80 != 0)
                }
                var length = next()
                if (length and 0x80 != 0) {
                    val count = length and 0x7f
                    require(count in 1..4)
                    length = 0
                    repeat(count) { length = length * 256 + next() }
                }
                require(length >= 0 && at + length <= bytes.size)
                return Der(first shr 6, first and 0x20 != 0, tag, bytes.copyOfRange(at, at + length)) to at + length
            }
        }
    }
}
