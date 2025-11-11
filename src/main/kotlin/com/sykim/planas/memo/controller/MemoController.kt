package com.sykim.planas.memo.controller

import com.sykim.planas.auth.repository.UserRepository
import com.sykim.planas.common.ItemColor
import com.sykim.planas.common.ItemColorRegistry
import com.sykim.planas.memo.model.Memo
import com.sykim.planas.memo.model.MemoFolder
import com.sykim.planas.memo.model.MemoFolderSelectResponseDTO
import com.sykim.planas.memo.model.MemoSelectResponseDTO
import com.sykim.planas.memo.repository.MemoFolderRepository
import com.sykim.planas.memo.repository.MemoRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/memo")
class MemoController(private val userRepository: UserRepository, private val memoFolderRepository: MemoFolderRepository, private val memoRepository: MemoRepository, private val colorRegistry: ItemColorRegistry) {

    fun getMemoFolderListByUser(): List<MemoFolderSelectResponseDTO> {
        val memoFolders: List<MemoFolder> = memoFolderRepository.findAll()
        return memoFolders.map { memoFolder -> MemoFolderSelectResponseDTO(memoFolder.id, memoFolder.name, memoFolder.createdAt.toString()) }
    }

    fun getMemoListByUser(folderId: Long): List<MemoSelectResponseDTO> {
        val memoList: List<Memo> = memoRepository.findAll()
        val colors: List<ItemColor> = colorRegistry.getAllColorList()

        return memoList.map { memo -> MemoSelectResponseDTO(memo.id, memo.title, memo.content, memo.tags, memo.createdAt.toString(), memo.updatedAt.toString(), colors.get(memo.memoColor).bgColor,
            folderId.toString()
        ) }
    }

}