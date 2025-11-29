package com.sykim.planas.auth.dto

data class GoogleLoginCallbackRequest(
    val code: String,
)

data class GoogleTokenResponse(
    val access_token: String,
    val expires_in: Long,
    val token_type: String,
    val scope: String?,
    val id_token: String?
)

data class GoogleUserInfo(
    val id: String,
    val email: String,
    val verified_email: Boolean?,
    val name: String?,
    val given_name: String?,
    val family_name: String?,
    val picture: String?,
    val locale: String?
)