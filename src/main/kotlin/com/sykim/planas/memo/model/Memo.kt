package com.sykim.planas.memo.model

import com.sykim.planas.auth.User
import com.sykim.planas.common.converter.StringListConverter
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "memo")
class Memo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    val userId: User,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "folder_id")
    val folder: MemoFolder,
    var title: String,
    @Lob
    @Column(columnDefinition = "TEXT")
    var content: String,
    @Convert(converter = StringListConverter::class)
    var tags: List<String>,
    val memoColor: Int,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime
) {
    fun updateMomo(title: String, content: String, tags: List<String>): Memo {
        this.title = title
        this.content = content
        this.tags = tags
        return this
    }
}

data class MemoSelectResponseDTO(val id: Long?, val title: String, val content: String, val tags: List<String>, val createdAt: String, val updatedAt: String, val color: String, val folderId: String)
data class MemoCreateBodyDTO(val title: String, val content: String, val tags: List<String>, val color: String)
data class MemoUpdateBodyDTO(val title: String, val content: String, val tags: List<String>)