package com.sykim.planas.calendar.repository

import com.sykim.planas.calendar.model.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository: JpaRepository<Event, Long> {
}