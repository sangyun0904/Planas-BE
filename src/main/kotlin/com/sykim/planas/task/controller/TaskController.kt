package com.sykim.planas.task.controller

import com.sykim.planas.task.repository.TaskRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskRepo: TaskRepository) {

    @GetMapping fun list() = taskRepo.findAll()

}