package com.sykim.planas.security

import com.sykim.planas.auth.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.time.Instant
import java.util.Base64

@Service
class RefreshTokenService(
    private val refreshTokenRepository: RefreshTokenRepository,
    @Value("\${jwt.refresh-expiration-ms}") private val refreshTokenValidityMs: Long
) {
    private val random = SecureRandom()

    fun createToken(user: User): RefreshToken {
        val token = generateRandomToken()
        val expiresAt = Instant.now().plusMillis(refreshTokenValidityMs)

        val refreshToken = RefreshToken(
            user = user,
            token = token,
            expiresAt = expiresAt,
            revoked = false
        )

        return refreshTokenRepository.save(refreshToken)
    }

    fun verifyToken(token: String): RefreshToken {
        val refreshToken = refreshTokenRepository.findByToken(token) ?: throw IllegalArgumentException("Invalid refresh token")

        if (refreshToken.revoked || refreshToken.expiresAt.isBefore(Instant.now())) {
            throw IllegalArgumentException("Expired or revoked refresh token")
        }

        return refreshToken
    }

    fun revokeToken(token: String) {
        val refreshToken = refreshTokenRepository.findByToken(token) ?: return
        val updated = RefreshToken(
            id = refreshToken.id,
            user = refreshToken.user,
            token = refreshToken.token,
            expiresAt = refreshToken.expiresAt,
            revoked = true
        )
        refreshTokenRepository.save(updated)
    }

    fun revokeAllForUser(user: User) {
        refreshTokenRepository.deleteByUser(user)
    }

    private fun generateRandomToken(length: Int = 64): String {
        val bytes = ByteArray(length)
        random.nextBytes(bytes)
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)
    }
}