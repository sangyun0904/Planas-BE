package com.sykim.planas.task.controller

import com.sykim.planas.auth.User
import com.sykim.planas.auth.repository.UserRepository
import com.sykim.planas.task.model.*
import com.sykim.planas.task.repository.TaskRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskRepo: TaskRepository, private val userRepo: UserRepository) {

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    fun createTask(@RequestBody body: TaskCreateRequestBodyDTO): Long? {
        val user: User = userRepo.findById(1).get()
        val task: Task = taskRepo.save(Task(null, body.title, user, body.content, false, TASK_PRIORITY.valueOf(body.priority), LocalDate.parse(body.dueDate), LocalDateTime.now(), LocalDateTime.now()))
        return task.id
    }

    @GetMapping
    fun getTaskList() : List<TaskSelectResponseDTO> {
        val tasks: List<Task> = taskRepo.findAll()
        return tasks.map { task -> TaskSelectResponseDTO(task.id, task.title, task.description, task.completed, task.priority.name, if (task.duedate != null) task.duedate.toString() else "" ) }
    }

    @PostMapping("/update/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    fun updateTask(@PathVariable id: Long, @RequestBody body: TaskUpdateRequestBodyDTO) {
        val task: Task = taskRepo.findById(id).get()
        taskRepo.save(task.updateTask(body.title, body.content, TASK_PRIORITY.valueOf(body.priority), LocalDate.parse(body.dueDate)))
    }

    @GetMapping("/complete/{id}")
    fun completeTask(@PathVariable id: Long) {
        val task: Task = taskRepo.findById(id).get()
        taskRepo.save(task.completeTask())
    }

    @GetMapping("/incomplete/{id}")
    fun incompleteTask(@PathVariable id: Long) {
        val task: Task = taskRepo.findById(id).get()
        taskRepo.save(task.cancelCompleteTask())
    }

    @GetMapping("/backlog/{id}")
    fun backlogTask(@PathVariable id: Long) {
        val task: Task = taskRepo.findById(id).get()
        taskRepo.save(task.toBacklog())
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    fun deleteTaskById(@PathVariable id: Long) {
        taskRepo.deleteById(id)
    }

}