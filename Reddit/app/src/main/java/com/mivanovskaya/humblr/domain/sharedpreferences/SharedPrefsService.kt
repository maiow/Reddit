package com.mivanovskaya.humblr.domain.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.mivanovskaya.humblr.data.api.TOKEN_ENABLED_KEY
import com.mivanovskaya.humblr.data.api.TOKEN_SHARED_KEY
import com.mivanovskaya.humblr.data.api.TOKEN_SHARED_NAME
import javax.inject.Inject
import javax.inject.Singleton

interface StorageService {
    fun create(context: Context): SharedPreferences
    fun saveEncryptedToken(context: Context, data: String)
    fun save(context: Context, key: String, data: Any?)
    fun load(context: Context, key: String): Boolean
}

@Singleton
class SharedPrefsService @Inject constructor() : StorageService {

    override fun save(context: Context, key: String, data: Any?) {
        create(context).save(key, data)
    }

    override fun load(context: Context, key: String): Boolean {
        return create(context).getBoolean(key, false)
    }

    override fun create(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            TOKEN_SHARED_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun saveEncryptedToken(context: Context, data: String) {
        save(context, TOKEN_ENABLED_KEY, true)
        return create(context).edit().putString(TOKEN_SHARED_KEY, data).apply()
    }

    private fun SharedPreferences.save(key: String, value: Any?) {
        val edit = this.edit()
        if (value is String)
            edit.putString(key, value)
        if (value is Boolean)
            edit.putBoolean(key, value)
        edit.commit() || throw Exception("Could not save $value to SharedPreferences")
    }
}