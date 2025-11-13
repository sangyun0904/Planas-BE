package com.sykim.planas.memo.controller

import com.sykim.planas.auth.User
import com.sykim.planas.auth.repository.UserRepository
import com.sykim.planas.common.ItemColor
import com.sykim.planas.common.ItemColorRegistry
import com.sykim.planas.memo.model.*
import com.sykim.planas.memo.repository.MemoFolderRepository
import com.sykim.planas.memo.repository.MemoRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/memo")
class MemoController(private val userRepo: UserRepository, private val memoFolderRepo: MemoFolderRepository, private val memoRepo: MemoRepository, private val colorRegis: ItemColorRegistry) {

    @PostMapping("/folder")
    @ResponseStatus(value = HttpStatus.CREATED)
    fun createMemoFolder(@RequestBody body: MemoFolderCreateRequestBodyDTO) {
        val user: User = userRepo.findById(1).get()
        memoFolderRepo.save(MemoFolder(null, body.name, user, LocalDateTime.now(), LocalDateTime.now()))
    }

    @PostMapping("/folder/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    fun createMemo(@RequestParam id: Long, @RequestBody body: MemoCreateBodyDTO) {
        val user: User = userRepo.findById(1).get()
        val folder: MemoFolder = memoFolderRepo.findById(id).get()
        val color: Int = colorRegis.getColorIdByName(body.color)
        memoRepo.save(Memo(null, user, folder, body.title, body.content, body.tags, color , LocalDateTime.now(), LocalDateTime.now()))
    }

    @GetMapping("/folder")
    fun getMemoFolderListByUser(): List<MemoFolderSelectResponseDTO> {
        val memoFolders: List<MemoFolder> = memoFolderRepo.findAll()
        return memoFolders.map { memoFolder -> MemoFolderSelectResponseDTO(memoFolder.id, memoFolder.name, memoFolder.createdAt.toString()) }
    }

    @GetMapping("/folder/{id}")
    fun getMemoListByUser(@PathVariable id: Long): List<MemoSelectResponseDTO> {
        val memoList: List<Memo> = memoRepo.findAllByFolderId(MemoFolder(id,"", null, null, null))
        val colors: List<ItemColor> = colorRegis.getAllColorList()

        return memoList.map { memo -> MemoSelectResponseDTO(memo.id, memo.title, memo.content, memo.tags, memo.createdAt.toString(), memo.updatedAt.toString(), colors.get(memo.memoColor).bgColor,
            id.toString()
        ) }
    }

}