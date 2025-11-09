package com.sykim.planas.memo.model

import com.sykim.planas.auth.User
import com.sykim.planas.common.converter.StringListConverter
import jakarta.persistence.*
import java.time.LocalDateTime
import kotlin.reflect.KClass

@Entity
@Table(name = "memo")
data class Memo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    val userId: User,
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
