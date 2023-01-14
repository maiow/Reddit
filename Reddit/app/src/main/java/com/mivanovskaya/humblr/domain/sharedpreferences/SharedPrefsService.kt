package com.mivanovskaya.humblr.domain.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.mivanovskaya.humblr.data.api.TOKEN_SHARED_NAME

interface StorageService {
    fun create(context: Context, key: String): SharedPreferences
    fun createEncrypted(context: Context, key: String): SharedPreferences
    fun save(context: Context, key: String, data: String)
    fun load(context: Context, key: String): String?
}

object SharedPrefsService : StorageService {

    override fun create(context: Context, key: String): SharedPreferences {
        return context.getSharedPreferences(key, Context.MODE_PRIVATE)
    }

    override fun save(context: Context, key: String, data: String) {
        val prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        prefs.save(key, data)
    }

    override fun load(context: Context, key: String): String? {
        return context.getSharedPreferences(key, Context.MODE_PRIVATE).getString(key, null)
    }

    override fun createEncrypted(context: Context, key: String): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val shard = EncryptedSharedPreferences.create(
            context,
            TOKEN_SHARED_NAME, //"secret_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        Log.e("Kart", "EncryptedSharedPreferences: $shard")
        return shard
    }

/*

    // use the shared preferences and editor as you normally would
   // var editor = sharedPreferences.edit()

 */

    private fun SharedPreferences.save(key: String, value: String) {
        val edit = this.edit()
        edit.putString(key, value)
        edit.commit() || throw Exception("Could not save $key to SharedPreferences")
    }
}