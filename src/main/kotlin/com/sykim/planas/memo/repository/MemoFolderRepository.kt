package com.sykim.planas.memo.repository

import com.sykim.planas.memo.model.MemoFolder
import org.springframework.data.jpa.repository.JpaRepository

interface MemoFolderRepository: JpaRepository<MemoFolder, Long> {
}