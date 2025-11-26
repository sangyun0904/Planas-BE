package com.sykim.planas.calendar.model

import com.sykim.planas.auth.User
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "events")
class Event(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
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
) {
    fun updateEvent(calendar: Calendar, title: String, startDateTime: LocalDateTime, endDateTime: LocalDateTime, description: String, eventCategory: String): Event {
        this.calendar = calendar
        this.title = title
        this.startDateTime = startDateTime
        this.endDateTime = endDateTime
        this.description = description
        this.eventCategory = eventCategory
        return this
    }
}

data class EventCreateRequestBodyDTO(val calendarId: Long, val title: String, val startDateTime: String, val endDateTime: String, val description: String, val eventCategory: String)
data class EventSelectResponseDTO(val id: Long?, val calendarId: Long?, val title: String, val startDateTime: String, val endDateTime: String, val description: String)
data class EventUpdateRequestBodyDTO(val calendarId: Long, val title: String, val startDateTime: String, val endDateTime: String, val description: String, val eventCategory: String)