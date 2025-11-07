package com.sykim.planas.calendar.model

import com.sykim.planas.auth.User
import com.sykim.planas.common.ItemColor
import jakarta.persistence.*

@Entity
@Table(name = "calendars")
data class Calendar(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    var user: User,
    var name: String,
    var calendarColor: Int
)

data class CreateCalendarRequestBodyDTO(val name: String, val color: String)