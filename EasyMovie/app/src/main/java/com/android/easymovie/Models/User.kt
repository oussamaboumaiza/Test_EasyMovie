package com.android.easymovie.Models

data class User(
    var success: Boolean = false,
    var email: String = "",
    var user_id: Int = -1,
    var credits: Int = -1,
    var username: String = "",
    var type: Int = -1,
    var created_at: String = "",
    var notifications: NotificationJson,
    var photo: String = "",
    var lang: String = "",
    var other_langs: Array<String>? = null,
    var eula: String = "",
    var token: String = "",
    var refresh_token: String = "",
    var expires_in: Int = -1,
    var errors: Array<ErrorJson>? = null
) {
    override fun toString(): String {
        return "User(success=$success, email='$email', user_id=$user_id, credits=$credits, username='$username', type=$type, created_at='$created_at', notifications=$notifications, photo='$photo', lang='$lang', other_langs=${other_langs?.contentToString()}, eula='$eula', token='$token', refresh_token='$refresh_token', expires_in=$expires_in)"
    }
}

data class NotificationJson(
    var mobile: Array<Any>? = null,
    var mail: Array<Int>? = null
) {
    override fun toString(): String {
        return "NotificationJson(mobile=${mobile?.contentToString()}, mail=${mail?.contentToString()})"
    }
}

data class ErrorJson(
    var code: Int,
    var errorMessage: String,
    var field: String
)
