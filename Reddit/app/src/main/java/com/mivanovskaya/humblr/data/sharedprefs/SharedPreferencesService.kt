package com.mivanovskaya.humblr.data.sharedprefs

//TODO: вот с этого начать
/*
private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val SHARED_KEY = "shared_key"

@Singleton
class SharedPreferencesService @Inject constructor(@ApplicationContext context: Context):
    SharedPrefsService {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveQuery(query: String) {
        sharedPreferences.edit().putString(SHARED_KEY, query).apply()
        Log.d(TAG, "saveQuery: $query")
    }

    override fun getQuery(): String {
        Log.d(TAG, "getQuery: ${sharedPreferences.getString(SHARED_KEY, "") ?: ""}")
        return sharedPreferences.getString(SHARED_KEY, "") ?: ""
    }*/
    /**и добавить создание encryptedSP:*/

/*    var masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    var sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secret_shared_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    // use the shared preferences and editor as you normally would
   // var editor = sharedPreferences.edit()

}*/
