package com.sykim.planas.auth.controller

import com.sykim.planas.auth.User
import com.sykim.planas.auth.UserCreateRequestBodyDTO
import com.sykim.planas.auth.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/user")
class UserController(private val userRepo: UserRepository) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody userCreate: UserCreateRequestBodyDTO) {
        userRepo.save(User(null, userCreate.email, userCreate.password, LocalDateTime.now()))
    }
}