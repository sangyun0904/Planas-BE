package com.sykim.planas.calendar.model

import com.sykim.planas.common.ItemColor
import jakarta.persistence.*

@Entity
@Table(name = "calendars")
data class Calendar(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
//    var user: User
    var name: String,
    var calendarColor: ItemColor
)
