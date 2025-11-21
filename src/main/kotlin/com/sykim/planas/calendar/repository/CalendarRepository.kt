package com.sykim.planas.calendar.repository

import com.sykim.planas.calendar.model.Calendar
import org.springframework.data.jpa.repository.JpaRepository

interface CalendarRepository: JpaRepository<Calendar, Long> {
    fun findAllByUserId(id: Long?): List<Calendar>
}