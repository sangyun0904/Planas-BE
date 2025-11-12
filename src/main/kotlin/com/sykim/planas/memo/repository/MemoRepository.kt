package com.sykim.planas.memo.repository

import com.sykim.planas.memo.model.Memo
import com.sykim.planas.memo.model.MemoFolder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MemoRepository: JpaRepository<Memo, Long> {

    fun findAllByFolderId(folder: MemoFolder): List<Memo>

}