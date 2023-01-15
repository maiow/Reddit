package com.mivanovskaya.humblr.data.api

const val CLIENT_ID = "3y_mSCFNH-rGTtZHDYj6mw"
const val CLIENT_SECRET = ""
const val RESPONSE_TYPE = "code"
const val STATE = "my_state"
const val REDIRECT_URI = "http://humblrmi/redirect"
const val DURATION = "permanent"
const val SCOPE = "identity edit flair history modconfig" +
            " modflair modlog modposts modwiki " +
            "mysubreddits privatemessages read report " +
            "save submit subscribe vote wikiedit wikiread"

const val CALL =
    "https://www.reddit.com/api/v1/authorize.compact" +
            "?client_id=" + CLIENT_ID +
            "&response_type=" + RESPONSE_TYPE +
            "&state=" + STATE +
            "&redirect_uri=" + REDIRECT_URI +
            "&duration=" + DURATION +
            "&scope=" + SCOPE


const val TOKEN_SHARED_NAME = "pref_token"
const val TOKEN_SHARED_KEY = "token"
const val SECRET_SHARED_NAME = "secret_shared_prefs"
const val SECRET_SHARED_KEY = "encrypted_token"
const val TOKEN_ENABLED_KEY = "token_enabled"
const val ONBOARDING_IS_SHOWN = "onboarding_is_shown"

