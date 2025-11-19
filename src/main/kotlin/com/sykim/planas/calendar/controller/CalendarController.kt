package com.sykim.planas.calendar.controller

import com.sykim.planas.auth.User
import com.sykim.planas.auth.repository.UserRepository
import com.sykim.planas.calendar.model.*
import com.sykim.planas.calendar.repository.CalendarRepository
import com.sykim.planas.calendar.repository.EventRepository
import com.sykim.planas.common.ItemColorRegistry
import org.springframework.http.HttpStatus
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
@RequestMapping("/calendar")
class CalendarController(private val calendarRepo: CalendarRepository, private val eventRepo: EventRepository, private val userRepo: UserRepository, private val colorRegistry: ItemColorRegistry) {

    @GetMapping
    fun getCalendarsByUser(): List<CalendarSelectResponseDTO> {
        val calendars: List<Calendar> = calendarRepo.findAll()
        var ret: List<CalendarSelectResponseDTO> = ArrayList()
        calendars.forEach { calendar ->
            val color = colorRegistry.getColorById(calendar.calendarColor)
            ret = ret.plus(CalendarSelectResponseDTO(calendar.id, calendar.name, color.color, color.bgColor, color.textColor))
        }
        return ret
    }

    @GetMapping("/event")
    fun getEventsByUser(): List<EventSelectResponseDTO> {

        val events: List<Event> = eventRepo.findAll()
        return events.map { event -> EventSelectResponseDTO(event.id, event.calendar.id, event.title, event.startDateTime.toString(), event.endDateTime.toString(), event.description) }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCalendar(@RequestBody createCalendar: CreateCalendarRequestBodyDTO): Long? {
        val user: Optional<User> =  userRepo.findById(1)

        if (user.isPresent) {
            return calendarRepo.save(Calendar(null, user.get(), createCalendar.name, colorRegistry.getColorIdByName(createCalendar.color))).id
        } else {
            throw RuntimeException()
        }
    }

    @PostMapping("/event")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveEvent(@RequestBody createEvent: EventCreateRequestBodyDTO): Long? {
        val calendar: Optional<Calendar> = calendarRepo.findById(createEvent.calendarId)

        if (calendar.isPresent) {
            val user: User = userRepo.findById(1).get()
            return eventRepo.save(Event(null, user, calendar.get(), createEvent.title, LocalDateTime.parse(createEvent.startDateTime), LocalDateTime.parse(createEvent.endDateTime), createEvent.description, createEvent.eventCategory, LocalDateTime.now(), LocalDateTime.now() )).id
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

}