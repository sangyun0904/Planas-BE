package com.sykim.planas.memo.model

import com.sykim.planas.auth.User
import com.sykim.planas.common.ItemColor
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "memo_folders")
data class MemoFolder(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    var user: User,
    val folderColor: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
