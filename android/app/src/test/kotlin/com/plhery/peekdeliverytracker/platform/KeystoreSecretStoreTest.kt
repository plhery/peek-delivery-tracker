package com.plhery.peekdeliverytracker.platform

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.test.ext.junit.runners.AndroidJUnit4
import java.io.InputStream
import java.io.OutputStream
import java.security.Key
import java.security.KeyStoreSpi
import java.security.Provider
import java.security.SecureRandom
import java.security.Security
import java.security.cert.Certificate
import java.security.spec.AlgorithmParameterSpec
import java.util.Collections
import java.util.Date
import java.util.Enumeration
import java.util.concurrent.ConcurrentHashMap
import javax.crypto.KeyGeneratorSpi
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import org.junit.After
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith

/**
 * The session's store (SessionPersistence) through AndroidKeystoreKeys, with a software stand-in registered as the
 * "AndroidKeyStore" provider: Robolectric has no Keystore. What this cannot show is the Keystore's own part: that the
 * key is made and kept in secure hardware and never leaves it, its own AES-GCM, a key lost to a reinstall or a factory
 * reset, and storage locked before the first unlock. The backup rules are the manifest's (data_extraction_rules.xml).
 */
@RunWith(AndroidJUnit4::class)
class KeystoreSecretStoreTest {
    @get:Rule
    val folder = TemporaryFolder()

    private val session = """{"access_token":"synthetic-access","refresh_token":"synthetic-refresh"}""".toByteArray()

    @Before
    fun installTheStandIn() {
        FakeAndroidKeyStore.keys.clear()
        FakeAndroidKeyStore.specs.clear()
        Security.addProvider(FakeAndroidKeyStore())
    }

    @After
    fun removeTheStandIn() {
        Security.removeProvider("AndroidKeyStore")
    }

    private fun store(service: String = "com.plhery.PeekDeliveryTracker.auth", keys: SecretKeys = AndroidKeystoreKeys) =
        KeystoreSecretStore(service, folder.root, keys)

    @Test
    fun aSavedValueReadsBackSealedOnDiskAndFromAnotherInstance() {
        assertNull(store().load())
        store().save(session)
        assertArrayEquals(session, store().load())

        val sealed = folder.root.resolve("com.plhery.PeekDeliveryTracker.auth").readBytes()
        assertEquals(1, sealed[0].toInt())
        assertFalse("synthetic-access" in String(sealed, Charsets.ISO_8859_1))
        assertEquals(1 + 12 + session.size + 16, sealed.size)
        // A fresh nonce each time: the same value never seals to the same bytes.
        store().save(session)
        assertFalse(sealed.contentEquals(folder.root.resolve("com.plhery.PeekDeliveryTracker.auth").readBytes()))
    }

    @Test
    fun theKeyIsAnAesGcmKeyOfTheKeystoreThatAsksForNothing() {
        store().save(session)
        val spec = FakeAndroidKeyStore.specs.single()
        assertEquals("peek.secret.com.plhery.PeekDeliveryTracker.auth", spec.keystoreAlias)
        assertEquals(KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT, spec.purposes)
        assertEquals(listOf(KeyProperties.BLOCK_MODE_GCM), spec.blockModes.toList())
        assertEquals(listOf(KeyProperties.ENCRYPTION_PADDING_NONE), spec.encryptionPaddings.toList())
        assertEquals(256, spec.keySize)
        assertTrue(spec.isRandomizedEncryptionRequired)
        assertFalse(spec.isUserAuthenticationRequired)
        assertFalse(spec.isUnlockedDeviceRequired)
        // One key per service, made once.
        store().save(session)
        store("com.plhery.PeekDeliveryTracker.deviceParcels").save(session)
        assertEquals(2, FakeAndroidKeyStore.specs.size)
    }

    @Test
    fun aLostKeyOrChangedBytesReadAsNothingSaved() {
        store().save(session)
        FakeAndroidKeyStore.keys.clear()
        assertNull(store().load())
        // A new save makes a new key.
        store().save(session)
        assertArrayEquals(session, store().load())

        val file = folder.root.resolve("com.plhery.PeekDeliveryTracker.auth")
        val bytes = file.readBytes()
        bytes[bytes.size - 1] = (bytes[bytes.size - 1].toInt() xor 1).toByte()
        file.writeBytes(bytes)
        assertNull(store().load())
        file.writeBytes(byteArrayOf(1, 2, 3))
        assertNull(store().load())
        file.writeBytes(ByteArray(0))
        assertNull(store().load())
    }

    @Test
    fun anEntryMovedUnderAnotherServiceReadsAsNothing() {
        val key = SecretKeySpec(ByteArray(32) { it.toByte() }, "AES")
        val oneKey = object : SecretKeys {
            override fun existing(alias: String): SecretKey = key

            override fun create(alias: String): SecretKey = key

            override fun delete(alias: String) {}
        }
        store("first", oneKey).save(session)
        folder.root.resolve("first").copyTo(folder.root.resolve("second"))
        assertArrayEquals(session, store("first", oneKey).load())
        assertNull(store("second", oneKey).load())
    }

    @Test
    fun deletingRemovesTheEntryAndItsKeyAndNeverThrows() {
        store().save(session)
        store().delete()
        assertNull(store().load())
        assertFalse(folder.root.resolve("com.plhery.PeekDeliveryTracker.auth").exists())
        assertTrue(FakeAndroidKeyStore.keys.isEmpty())
        store().delete()
    }

    @Test
    fun withoutAKeystoreSavingThrowsAndReadingFindsNothing() {
        Security.removeProvider("AndroidKeyStore")
        try {
            store().save(session)
            fail("Expected the save to fail")
        } catch (error: Exception) {
            assertNotNull(error)
        }
        assertFalse(folder.root.resolve("com.plhery.PeekDeliveryTracker.auth").exists())
        assertNull(store().load())
        store().delete()
    }
}

/** The Android Keystore's provider name, with keys kept in memory: enough for the store's calls to reach it. */
@Suppress("DEPRECATION")
class FakeAndroidKeyStore : Provider("AndroidKeyStore", 1.0, "Software stand-in for the Android Keystore") {
    init {
        put("KeyStore.AndroidKeyStore", Store::class.java.name)
        put("KeyGenerator.AES", Generator::class.java.name)
    }

    companion object {
        val keys = ConcurrentHashMap<String, SecretKey>()
        val specs: MutableList<KeyGenParameterSpec> = Collections.synchronizedList(mutableListOf())
    }

    class Store : KeyStoreSpi() {
        override fun engineGetKey(alias: String, password: CharArray?): Key? = keys[alias]

        override fun engineGetCertificateChain(alias: String): Array<Certificate>? = null

        override fun engineGetCertificate(alias: String): Certificate? = null

        override fun engineGetCreationDate(alias: String): Date? = if (keys.containsKey(alias)) Date() else null

        override fun engineSetKeyEntry(alias: String, key: Key, password: CharArray?, chain: Array<out Certificate>?) {
            keys[alias] = key as SecretKey
        }

        override fun engineSetKeyEntry(alias: String, key: ByteArray, chain: Array<out Certificate>?) = throw UnsupportedOperationException()

        override fun engineSetCertificateEntry(alias: String, cert: Certificate) = throw UnsupportedOperationException()

        override fun engineDeleteEntry(alias: String) {
            keys.remove(alias)
        }

        override fun engineAliases(): Enumeration<String> = Collections.enumeration(keys.keys.toList())

        override fun engineContainsAlias(alias: String): Boolean = keys.containsKey(alias)

        override fun engineSize(): Int = keys.size

        override fun engineIsKeyEntry(alias: String): Boolean = keys.containsKey(alias)

        override fun engineIsCertificateEntry(alias: String): Boolean = false

        override fun engineGetCertificateAlias(cert: Certificate): String? = null

        override fun engineStore(stream: OutputStream?, password: CharArray?) {}

        override fun engineLoad(stream: InputStream?, password: CharArray?) {}
    }

    class Generator : KeyGeneratorSpi() {
        private var spec: KeyGenParameterSpec? = null

        override fun engineInit(random: SecureRandom?) = throw UnsupportedOperationException("The Keystore needs a spec")

        override fun engineInit(params: AlgorithmParameterSpec, random: SecureRandom?) {
            spec = params as KeyGenParameterSpec
            specs += params
        }

        override fun engineInit(keysize: Int, random: SecureRandom?) = throw UnsupportedOperationException("The Keystore needs a spec")

        override fun engineGenerateKey(): SecretKey {
            val spec = requireNotNull(spec)
            val key = SecretKeySpec(ByteArray(spec.keySize / 8).also(SecureRandom()::nextBytes), "AES")
            keys[spec.keystoreAlias] = key
            return key
        }
    }
}
