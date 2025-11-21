package com.sykim.planas.auth.controller

import com.sykim.planas.auth.*
import com.sykim.planas.auth.repository.UserRepository
import com.sykim.planas.security.JwtTokenProvider
import com.sykim.planas.security.RefreshTokenService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("api/v1/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenProvider: JwtTokenProvider,
    private val userRepo: UserRepository,
    private val refreshTokenService: RefreshTokenService,
    private val passwordEncoder: PasswordEncoder,
    @Value("\${jwt.refresh-expiration-ms}") private val refreshValidityMs: Long
) {

    private val refreshCookieName = "refresh_token"

    @PostMapping("/register")
    fun register(
        @RequestBody request: RegisterRequest
    ): ResponseEntity<RegisterResponse> {
        if (userRepo.findByEmail(request.email) != null) {
            return ResponseEntity.status(409).body(null)
        }

        val encodedPassword = passwordEncoder.encode(request.password)

        val newUser = userRepo.save(
            User(
                id = null,
                email = request.email,
                password = encodedPassword,
                createdAt = LocalDateTime.now()
            )
        )

        val responseBody = RegisterResponse(
            userId = newUser.id,
            email = newUser.email
        )

        return ResponseEntity.ok(responseBody)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest,
        response: HttpServletResponse
    ): ResponseEntity<LoginResponse> {
        val authToken = UsernamePasswordAuthenticationToken(request.email, request.password)
        val authentication = authenticationManager.authenticate(authToken)

        val principal = authentication.principal as org.springframework.security.core.userdetails.User
        val accessToken = jwtTokenProvider.generateAccessToken(principal.username)

        val user = userRepo.findByEmail(principal.username) ?: throw IllegalStateException("User not found after auth")

        val refreshToken = refreshTokenService.createToken(user)

        addRefreshTokenCookie(response, refreshToken.token)

        return ResponseEntity.ok(LoginResponse(accessToken = accessToken))
    }

    @PostMapping("/refresh")
    fun refreshToken(
        @CookieValue(name = "refresh_token", required = false) refreshTokenCookie: String?,
        response: HttpServletResponse
    ): ResponseEntity<LoginResponse> {
        if (refreshTokenCookie.isNullOrBlank()) {
            return ResponseEntity.status(401).build()
        }

        val refreshToken = refreshTokenService.verifyToken(refreshTokenCookie)
        val newAccessToken = jwtTokenProvider.generateAccessToken(refreshToken.user.email)

        refreshTokenService.revokeToken(refreshTokenCookie)
        val newRefresh = refreshTokenService.createToken(refreshToken.user)
        addRefreshTokenCookie(response, newRefresh.token)

        return ResponseEntity.ok(LoginResponse(accessToken = newAccessToken))
    }

    @PostMapping("/logout")
    fun logout(
        @CookieValue(name = "refresh_token", required = false) refreshTokenCookie:String?,
        response: HttpServletResponse
    ): ResponseEntity<Void> {
        if (!refreshTokenCookie.isNullOrBlank()) {
            refreshTokenService.revokeToken(refreshTokenCookie)
        }
        val cookie = jakarta.servlet.http.Cookie(refreshCookieName, "")
        cookie.path = "/"
        cookie.maxAge = 0
        cookie.isHttpOnly = true
        cookie.secure = true
        response.addCookie(cookie)

        return ResponseEntity.noContent().build()
    }

    private fun addRefreshTokenCookie(response: HttpServletResponse, token: String) {
        val cookie = jakarta.servlet.http.Cookie(refreshCookieName, token)
        cookie.path = "/"
        cookie.isHttpOnly = true
        cookie.secure = true
        cookie.maxAge = (refreshValidityMs / 1000).toInt()
        response.addCookie(cookie)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody userCreate: UserCreateRequestBodyDTO) {
        userRepo.save(User(null, userCreate.email, userCreate.password, LocalDateTime.now()))
    }
}