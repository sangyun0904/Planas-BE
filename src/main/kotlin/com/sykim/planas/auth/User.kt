package com.sykim.planas.auth

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Column(unique = true, nullable = false)
    var email: String,
    @Column(nullable = false)
    var password: String,
    var createdAt: LocalDateTime,
    @Column(nullable = false)
    var googleUser: Boolean = false
)


data class UserCreateRequestBodyDTO(@Email val email: String, @NotBlank val password: String)
data class LoginRequest(@field:Email val email: String, val password: String)
data class LoginResponse(val accessToken: String, val tokenType: String = "Bearer")
data class GoogleLoginResponse(val email: String, val accessToken: String, val tokenType: String = "Bearer")
data class MeResponse(val email: String)
data class RegisterRequest(val email: String, val password: String)
data class RegisterResponse(val userId: Long?, val email: String)