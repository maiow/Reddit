package com.mivanovskaya.humblr.data.api.profileDto

data class Features(
    val awards_on_streams: Boolean,
    val chat: Boolean,
    val chat_group_rollout: Boolean,
    val chat_subreddit: Boolean,
    val chat_user_settings: Boolean,
    val cookie_consent_banner: Boolean,
    val crowd_control_for_post: Boolean,
    val do_not_track: Boolean,
    val expensive_coins_package: Boolean,
    val images_in_comments: Boolean,
    val is_email_permission_required: Boolean,
    val mod_awards: Boolean,
    val mod_service_mute_reads: Boolean,
    val mod_service_mute_writes: Boolean,
    val modlog_copyright_removal: Boolean,
    val mweb_xpromo_interstitial_comments_android: Boolean,
    val mweb_xpromo_interstitial_comments_ios: Boolean,
    val mweb_xpromo_modal_listing_click_daily_dismissible_android: Boolean,
    val mweb_xpromo_modal_listing_click_daily_dismissible_ios: Boolean,
    val noreferrer_to_noopener: Boolean,
    val premium_subscriptions_table: Boolean,
    val promoted_trend_blanks: Boolean,
    val resized_styles_images: Boolean,
    val show_amp_link: Boolean,
    val use_pref_account_deployment: Boolean
)