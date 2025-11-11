package com.sykim.planas.memo.controller

import com.sykim.planas.auth.repository.UserRepository
import com.sykim.planas.memo.repository.MemoRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/memo")
class MemoController(private val userRepository: UserRepository, private val memoRepository: MemoRepository) {


}