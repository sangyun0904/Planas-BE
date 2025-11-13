package com.sykim.planas.memo.repository

import com.sykim.planas.memo.model.Memo
import com.sykim.planas.memo.model.MemoFolder
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MemoRepository: JpaRepository<Memo, Long> {

    fun findAllByFolder(folder: MemoFolder): List<Memo>
    @Transactional
    fun deleteByFolder(folder: MemoFolder)
}