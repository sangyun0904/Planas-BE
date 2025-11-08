package com.sykim.planas.task.controller

import com.sykim.planas.task.model.Task
import com.sykim.planas.task.model.TaskSelectResponseDTO
import com.sykim.planas.task.repository.TaskRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskRepo: TaskRepository) {

    @GetMapping fun getTaskList() : List<TaskSelectResponseDTO> {
        val tasks: List<Task> = taskRepo.findAll()
        return tasks.map { task -> TaskSelectResponseDTO(task.id, task.title, task.description, task.completed, task.priority.name, task.duedate.toString()) }
    }

}