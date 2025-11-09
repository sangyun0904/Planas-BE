package com.sykim.planas.memo.repository

import com.sykim.planas.memo.model.Memo
import org.springframework.data.jpa.repository.JpaRepository

interface MemoRepository: JpaRepository<Memo, Long> {
}