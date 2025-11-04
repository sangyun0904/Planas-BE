package com.sykim.planas.task.repository

import com.sykim.planas.task.model.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long>