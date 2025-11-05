package com.sykim.planas.calendar.model

import com.sykim.planas.auth.User
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "events")
data class Event(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    var user: User,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "calendar_id")
    var calendar: Calendar,
    var title: String,
    var startDateTime: LocalDateTime,
    var endDateTime: LocalDateTime,
    @Lob
    @Column(columnDefinition = "TEXT")
    var description: String,
    @Column(nullable = false)
    var eventCategory: String = "none",
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime
)
