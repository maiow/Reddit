package com.mivanovskaya.humblr.data.api

const val ACCESS_KEY = "aFYYZK6kx8GMEoIHFhzT27rhwgZZtnENvc0M8mAW7XI"
const val SECRET_KEY = "cOKBHRzF_cc6Cer9SKQ4gR_Y4vD-YFE2u-YGbLYqEMA"
const val REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob"
const val SCOPE =
    "public+read_user+" +
            "write_user+read_photos+" +
            "write_photos+" +
            "write_likes+" +
            "write_followers+" +
            "read_collections+" +
            "write_collections"

const val CALL =
    "https://unsplash.com/oauth/authorize" +
            "?client_id=" + ACCESS_KEY +
            "&redirect_uri=" + REDIRECT_URI +
            "&response_type=code" +
            "&scope=" + SCOPE


const val TOKEN_SHARED_NAME = "pref_token"
const val TOKEN_SHARED_KEY = "token"
const val TOKEN_ENABLED_KEY = "token_enabled"
const val ONBOARDING_IS_SHOWN = "onboarding_is_shown"

