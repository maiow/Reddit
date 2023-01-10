package com.mivanovskaya.humblr.data.api.dto.profileDto

data class Pro(
    val accept_followers: Boolean,
    val awardee_karma: Int,
    val awarder_karma: Int,
    val can_create_subreddit: Boolean,
    val can_edit_name: Boolean,
    val coins: Int,
    val comment_karma: Int,
    val created: Double,
    val created_utc: Double,
    val features: Features,
    val force_password_reset: Boolean,
    val gold_creddits: Int,
    val gold_expiration: Any?,
    val has_android_subscription: Boolean,
    val has_external_account: Boolean,
    val has_gold_subscription: Boolean,
    val has_ios_subscription: Boolean,
    val has_mail: Boolean,
    val has_mod_mail: Boolean,
    val has_paypal_subscription: Boolean,
    val has_stripe_subscription: Boolean,
    val has_subscribed: Boolean,
    val has_subscribed_to_premium: Boolean,
    val has_verified_email: Boolean,
    val has_visited_new_profile: Boolean,
    val hide_from_robots: Boolean,
    val icon_img: String,
    val id: String,
    val in_beta: Boolean,
    val in_chat: Boolean,
    val in_redesign_beta: Boolean,
    val inbox_count: Int,
    val is_employee: Boolean,
    val is_gold: Boolean,
    val is_mod: Boolean,
    val is_sponsor: Boolean,
    val is_suspended: Boolean,
    val link_karma: Int,
    val linked_identities: List<String>,
    val name: String,
    val new_modmail_exists: Any?,
    val num_friends: Int,
    val oauth_client_id: String,
    val over_18: Boolean,
    val password_set: Boolean,
    val pref_autoplay: Boolean,
    val pref_clickgadget: Int,
    val pref_geopopular: String,
    val pref_nightmode: Boolean,
    val pref_no_profanity: Boolean,
    val pref_show_presence: Boolean,
    val pref_show_snoovatar: Boolean,
    val pref_show_trending: Boolean,
    val pref_show_twitter: Boolean,
    val pref_top_karma_subreddits: Boolean,
    val pref_video_autoplay: Boolean,
    val seen_give_award_tooltip: Boolean,
    val seen_layout_switch: Boolean,
    val seen_premium_adblock_modal: Boolean,
    val seen_redesign_modal: Boolean,
    val seen_subreddit_chat_ftux: Boolean,
    val snoovatar_img: String,
    val snoovatar_size: List<Int>,
    val subreddit: Subreddit,
    val suspension_expiration_utc: Any?,
    val total_karma: Int,
    val verified: Boolean
)