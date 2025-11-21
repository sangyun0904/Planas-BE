package com.sykim.planas.calendar.repository

import com.sykim.planas.calendar.model.Calendar
import com.sykim.planas.calendar.model.Event
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository: JpaRepository<Event, Long> {
    @Transactional
    fun deleteByCalendarId(calendar: Long)
    fun findAllByUserId(id: Long?): List<Event>
}