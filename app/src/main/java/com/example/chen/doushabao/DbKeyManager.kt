package com.example.chen.doushabao

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import net.sqlcipher.database.SQLiteDatabase
import java.security.SecureRandom

class DbKeyManager(private val context: Context) {
    private val prefs: SharedPreferences by lazy {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        EncryptedSharedPreferences.create(
            PREFS_NAME,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun getPassphrase(): ByteArray {
        val existing = prefs.getString(KEY_DB_PASSPHRASE, null)
        if (!existing.isNullOrBlank()) {
            return SQLiteDatabase.getBytes(existing.toCharArray())
        }
        val rawKey = ByteArray(32)
        SecureRandom().nextBytes(rawKey)
        val encoded = Base64.encodeToString(rawKey, Base64.NO_WRAP)
        prefs.edit().putString(KEY_DB_PASSPHRASE, encoded).apply()
        return SQLiteDatabase.getBytes(encoded.toCharArray())
    }

    companion object {
        private const val PREFS_NAME = "dsb_secure_prefs"
        private const val KEY_DB_PASSPHRASE = "db_passphrase"
    }
}
