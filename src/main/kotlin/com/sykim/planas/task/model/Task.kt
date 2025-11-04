package com.sykim.planas.task.model

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "tasks")
data class Task (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val title: String,
//    val user: User
    val description: String,
    val completed: Boolean,
    val priority: TASK_PRIORITY,
    val duedate: LocalDate,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

enum class TASK_PRIORITY {
    HIGH,
    NORMAL,
    LOW
}