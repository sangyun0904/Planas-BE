package com.sykim.planas.security

import com.sykim.planas.auth.User
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "refresh_tokens")
class RefreshToken (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,
    @Column(nullable = false, unique = true, length = 255)
    val token: String,
    @Column(nullable = false)
    val expiresAt: Instant,
    @Column(nullable = false)
    val revoked: Boolean = false

)