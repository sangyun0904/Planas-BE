package com.sykim.planas.auth.repository

import com.sykim.planas.auth.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}