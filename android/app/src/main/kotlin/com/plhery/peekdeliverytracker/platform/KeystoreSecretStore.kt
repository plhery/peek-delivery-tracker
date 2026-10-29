package com.plhery.peekdeliverytracker.platform

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.plhery.peekdeliverytracker.core.SessionPersistence
import java.io.File
import java.io.IOException
import java.nio.ByteBuffer
import java.security.GeneralSecurityException
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

/**
 * KeychainStore's Android counterpart, by SessionPersistence's rules (Authentication.kt): one entry per service, its
 * bytes encrypted with an AES-GCM key of the Android Keystore that never leaves it, in
 * `filesDir/Keychain/<service>`. That folder is the app's credential-encrypted storage, readable once the phone was
 * first unlocked after a restart, as `kSecAttrAccessibleAfterFirstUnlockThisDeviceOnly`; it is left out of backups
 * and device transfers (res/xml/data_extraction_rules.xml), where the key could not follow it.
 *
 * The file holds a version byte, the 12-byte nonce and the sealed bytes, which are authenticated with the service's
 * name: an entry moved under another service reads as nothing. A save is synced before it returns (`AtomicFile`).
 */
class KeystoreSecretStore(
    private val service: String,
    folder: File,
    private val keys: SecretKeys = AndroidKeystoreKeys,
) : SessionPersistence {
    private val file = AtomicBytesFile(File(folder, service))
    private val alias = "$ALIAS_PREFIX$service"

    @Synchronized
    override fun save(data: ByteArray) {
        val key = try {
            keys.existing(alias) ?: keys.create(alias)
        } catch (e: GeneralSecurityException) {
            throw IOException("No key for $service", e)
        }
        val sealed = try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
            cipher.init(Cipher.ENCRYPT_MODE, key)
            cipher.updateAAD(service.toByteArray(Charsets.UTF_8))
            val nonce = cipher.iv
            check(nonce.size == NONCE_BYTES) { "Unexpected nonce" }
            val ciphertext = cipher.doFinal(data)
            ByteBuffer.allocate(1 + nonce.size + ciphertext.size).put(VERSION).put(nonce).put(ciphertext).array()
        } catch (e: GeneralSecurityException) {
            throw IOException("Could not seal $service", e)
        }
        file.write(sealed)
    }

    @Synchronized
    override fun load(): ByteArray? {
        val sealed = file.read() ?: return null
        if (sealed.size < 1 + NONCE_BYTES + TAG_BITS / 8 || sealed[0] != VERSION) return null
        return try {
            val key = keys.existing(alias) ?: return null
            val cipher = Cipher.getInstance(TRANSFORMATION)
            cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(TAG_BITS, sealed, 1, NONCE_BYTES))
            cipher.updateAAD(service.toByteArray(Charsets.UTF_8))
            cipher.doFinal(sealed, 1 + NONCE_BYTES, sealed.size - 1 - NONCE_BYTES)
        } catch (_: Exception) {
            // A key the Keystore lost or no longer allows, a Keystore that fails, or bytes changed on disk: nothing
            // saved, as KeychainStore's `try?` reads it.
            null
        }
    }

    /** The entry, then its key; the folder stays. */
    @Synchronized
    override fun delete() {
        file.delete()
        try {
            keys.delete(alias)
        } catch (_: Exception) {
            // Nothing sealed with it is left to read.
        }
    }

    companion object {
        private const val ALIAS_PREFIX = "peek.secret."
        private const val TRANSFORMATION = "AES/GCM/NoPadding"
        private const val VERSION: Byte = 1
        private const val NONCE_BYTES = 12
        private const val TAG_BITS = 128

        /** The store of `service`, in the app's `filesDir/Keychain`. */
        fun of(context: Context, service: String): KeystoreSecretStore = KeystoreSecretStore(service, File(context.filesDir, "Keychain"))
    }
}

/** Where the stores' keys live: the Android Keystore in the app, a software stand-in in tests. */
interface SecretKeys {
    /** The key under `alias`, or nil when there is none. */
    fun existing(alias: String): SecretKey?

    /** A new key under `alias`, replacing any there. */
    fun create(alias: String): SecretKey

    fun delete(alias: String)
}

/**
 * The Android Keystore's AES keys: 256 bits, GCM alone, a fresh nonce from the Keystore for each encryption. No user
 * authentication or unlocked-device requirement: the app reads its session at launch, after the first unlock, as the
 * iPhone's keychain item allows.
 */
object AndroidKeystoreKeys : SecretKeys {
    private const val PROVIDER = "AndroidKeyStore"

    private fun keyStore(): KeyStore = KeyStore.getInstance(PROVIDER).apply { load(null) }

    override fun existing(alias: String): SecretKey? = keyStore().getKey(alias, null) as? SecretKey

    override fun create(alias: String): SecretKey {
        val generator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, PROVIDER)
        generator.init(spec(alias))
        return generator.generateKey()
    }

    override fun delete(alias: String) {
        keyStore().deleteEntry(alias)
    }

    fun spec(alias: String): KeyGenParameterSpec =
        KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .setRandomizedEncryptionRequired(true)
            .build()
}
