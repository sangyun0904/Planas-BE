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
    var email: String,
    var password: String,
    var createdAt: LocalDateTime
)


data class UserCreateRequestBodyDTO(@Email val email: String, @NotBlank val password: String)