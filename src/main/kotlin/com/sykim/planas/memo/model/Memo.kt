package com.sykim.planas.memo.model

import com.sykim.planas.auth.User
import com.sykim.planas.common.ItemColor
import com.sykim.planas.common.converter.StringListConverter
import jakarta.persistence.*
import java.time.LocalDateTime
import kotlin.reflect.KClass

@Entity
@Table(name = "memo")
data class Memo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    val userId: User,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "folder_id")
    val folderId: MemoFolder,
    val title: String,
    @Lob
    @Column(columnDefinition = "TEXT")
    val content: String,
    @Convert(converter = StringListConverter::class)
    val tags: List<String>,
    val memoColor: Int,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime
)

data class MemoSelectResponseDTO(val id: Long?, val title: String, val content: String, val tags: List<String>, val createdAt: String, val updatedAt: String, val color: String, val folderId: String)