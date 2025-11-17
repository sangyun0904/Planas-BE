package com.sykim.planas.task.model

import com.sykim.planas.auth.User
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "tasks")
class Task (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    var title: String,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    val user: User,
    @Lob
    @Column(columnDefinition = "TEXT")
    var description: String,
    var completed: Boolean,
    @Enumerated(EnumType.STRING)
    var priority: TASK_PRIORITY,
    var duedate: LocalDate,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    fun updateTask(title: String, content: String, priority: TASK_PRIORITY, dueDate: LocalDate): Task {
        this.title = title
        this.description = content
        this.priority = priority
        this.duedate = dueDate
        return this
    }

    fun completeTask(): Task {
        this.completed = true
        return this
    }

    fun cancelCompleteTask(): Task {
        this.completed = false
        return this
    }
}

enum class TASK_PRIORITY {
    HIGH,
    MEDIUM,
    LOW
}

data class TaskSelectResponseDTO(val id: Long?, val title: String, val description: String, val completed: Boolean, val priority: String, val duedate: String)
data class TaskCreateRequestBodyDTO(val title: String, val content: String, val priority: String, val dueDate: String)
data class TaskUpdateRequestBodyDTO(val title:String, val content: String, val priority: String, val dueDate: String)