package com.sykim.planas.calendar.controller

import com.sykim.planas.auth.User
import com.sykim.planas.auth.repository.UserRepository
import com.sykim.planas.calendar.model.*
import com.sykim.planas.calendar.repository.CalendarRepository
import com.sykim.planas.calendar.repository.EventRepository
import com.sykim.planas.common.ItemColorRegistry
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.Optional

@RestController
@RequestMapping("api/v1/calendar")
class CalendarController(private val calendarRepo: CalendarRepository, private val eventRepo: EventRepository, private val userRepo: UserRepository, private val colorRegistry: ItemColorRegistry) {

    @GetMapping
    fun getCalendarsByUser(@AuthenticationPrincipal auth: org.springframework.security.core.userdetails.User): ResponseEntity<List<CalendarSelectResponseDTO>> {
        val user: User? = userRepo.findByEmail(auth.username)

        user ?: throw IllegalStateException("User not found")

        val calendars: List<Calendar> = calendarRepo.findAllByUserId(user.id)
        var ret: List<CalendarSelectResponseDTO> = ArrayList()
        calendars.forEach { calendar ->
            val color = colorRegistry.getColorById(calendar.calendarColor)
            ret = ret.plus(CalendarSelectResponseDTO(calendar.id, calendar.name, color.color, color.bgColor, color.textColor))
        }
        return ResponseEntity.ok(ret)
    }

    @GetMapping("/event")
    fun getEventsByUser(@AuthenticationPrincipal auth: org.springframework.security.core.userdetails.User): ResponseEntity<List<EventSelectResponseDTO>> {
        val user: User? = userRepo.findByEmail(auth.username)

        user ?: throw IllegalStateException("User not found")

        val events: List<Event> = eventRepo.findAllByUserId(user.id)
        return ResponseEntity.ok(events.map { event -> EventSelectResponseDTO(event.id, event.calendar.id, event.title, event.startDateTime.toString(), event.endDateTime.toString(), event.description) })
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCalendar(@AuthenticationPrincipal auth: org.springframework.security.core.userdetails.User, @RequestBody createCalendar: CreateCalendarRequestBodyDTO): ResponseEntity<Long?> {
        val user: User? = userRepo.findByEmail(auth.username)

        user ?: throw IllegalStateException("User not found")

        return ResponseEntity.ok(calendarRepo.save(Calendar(null, user, createCalendar.name, colorRegistry.getColorIdByName(createCalendar.color))).id)
    }

    @PostMapping("/event")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveEvent(@AuthenticationPrincipal auth: org.springframework.security.core.userdetails.User, @RequestBody createEvent: EventCreateRequestBodyDTO): ResponseEntity<Long?> {
        val calendar: Optional<Calendar> = calendarRepo.findById(createEvent.calendarId)

        if (calendar.isPresent) {
            val user = calendar.get().user ?: throw IllegalStateException("User not found")
            return ResponseEntity.ok(eventRepo.save(Event(null, user, calendar.get(), createEvent.title, LocalDateTime.parse(createEvent.startDateTime), LocalDateTime.parse(createEvent.endDateTime), createEvent.description, createEvent.eventCategory, LocalDateTime.now(), LocalDateTime.now() )).id)
        } else {
            throw RuntimeException()
        }
    }

    @PostMapping("/event/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateEvent(@PathVariable id: Long, @RequestBody body: EventUpdateRequestBodyDTO) {
        val event: Event = eventRepo.findById(id).get()
        eventRepo.save(event.updateEvent(body.title, LocalDateTime.parse(body.startDateTime), LocalDateTime.parse(body.endDateTime), body.description, body.eventCategory))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteCalendar(@PathVariable id: Long) {
        eventRepo.deleteByCalendarId(id)
        calendarRepo.deleteById(id)
    }

    @DeleteMapping("/event/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteEvent(@PathVariable id: Long) {
        eventRepo.deleteById(id)
    }

}