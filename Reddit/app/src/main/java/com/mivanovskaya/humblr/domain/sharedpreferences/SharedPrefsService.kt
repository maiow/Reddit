package com.mivanovskaya.humblr.domain.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

interface StorageService {
    fun create(context: Context, key: String): SharedPreferences
    fun save(context: Context, key: String, data: String)
    fun load(context: Context, key: String): String?
}

object SharedPrefsService : StorageService {

    // createSharedPreference(key: String) =
    // requireContext().getSharedPreferences(key, Context.MODE_PRIVATE)
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

/*     override fun load(context: Context, key: String): String? {
        return getSharedPreferences(key).getString(key, null)
    }

   // Always use local source for some settings, or always backed up source for others
    private fun getSharedPreferences(key: String) = when (key) {
        "localConfig" -> localSharedPreferences
        "networkSpecificConfigs" -> localSharedPreferences // Not send network names anywhere
        else -> if (useBackup) backedUpSharedPreferences else localSharedPreferences
    }*/

    private fun SharedPreferences.save(key: String, value: String) {
        val edit = this.edit()
        edit.putString(key, value)
        edit.commit() || throw Exception("Could not save $key to SharedPreferences")
    }
}