package com.sykim.planas.auth.controller

import com.sykim.planas.auth.dto.GoogleLoginCallbackRequest
import com.sykim.planas.auth.dto.GoogleTokenResponse
import com.sykim.planas.auth.dto.GoogleUserInfo
import com.sykim.planas.auth.repository.UserRepository
import com.sykim.planas.security.JwtTokenProvider
import org.jboss.logging.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import java.nio.charset.StandardCharsets

@RestController
@RequestMapping("api/v1/auth/google")
class GoogleAuthController(
    @Value("\${google.client-id}") private val clientId: String,
    @Value("\${google.client-secret}") private val clientSecret: String,
    @Value("\${google.redirect-uri}") private val redirectUri: String,
    @Value("\${google.token-uri}") private val tokenUri: String,
    @Value("\${google.user-info-uri}") private val userInfoUri: String,
    @Value("\${google.scope}") private val scope: String,
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider
) {
    private val restTemplate = RestTemplate()
    private val logger = Logger.getLogger("google api logger")

    @GetMapping("/url")
    fun getGoogleLoginUrl(state: String? = null): ResponseEntity<String> {
        val encodedRedirect = java.net.URLEncoder.encode(redirectUri, StandardCharsets.UTF_8)
        val encodeScope = java.net.URLEncoder.encode(scope, StandardCharsets.UTF_8)

        val builder = StringBuilder("https://accounts.google.com/o/oauth2/v2/auth")
        builder.append("?client_id=$clientId")
        builder.append("&redirect_uri=$encodedRedirect")
        builder.append("&response_type=code")
        builder.append("&scope=$encodeScope")
        builder.append("&access_type=offline")
        builder.append("&prompt=consent")

        if (state != null) {
            builder.append("&state=${java.net.URLEncoder.encode(state, StandardCharsets.UTF_8)}")
        }

        return ResponseEntity.ok(builder.toString())
    }

    @PostMapping("/callback")
    fun handleGoogleCallback(
        @RequestBody googleAuth: GoogleLoginCallbackRequest
    ) {
        val googleAccessToken = exchangeCodeForToken(googleAuth.code)

        val userInfo = fetchUserInfo(googleAccessToken.access_token)
        logger.info("username : ${userInfo.name} email : ${userInfo.email}")
    }

    private fun exchangeCodeForToken(code: String): GoogleTokenResponse {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED

        val body: MultiValueMap<String, String> = LinkedMultiValueMap()
        body.add("code", code)
        body.add("client_id", clientId)
        body.add("client_secret", clientSecret)
        body.add("redirect_uri", redirectUri)
        body.add("grant_type", "authorization_code")

        val entity = HttpEntity(body, headers)

        val response = restTemplate.postForEntity(
            tokenUri,
            entity,
            GoogleTokenResponse::class.java
        )

        return response.body ?: throw IllegalStateException("Failed to get Google token")
    }

    private fun fetchUserInfo(accessToken: String): GoogleUserInfo {
        val headers = HttpHeaders()
        headers.setBearerAuth(accessToken)

        val entity = HttpEntity<Void>(headers)

        val response = restTemplate.exchange(
            userInfoUri,
            HttpMethod.GET,
            entity,
            GoogleUserInfo::class.java
        )

        return response.body ?: throw IllegalStateException("failed to get Google user info")
    }
}