package com.guerra.enrico.data.remote.request

import com.guerra.enrico.data.models.Task
import java.util.*

/**
 * Created by enrico
 * on 21/07/2019.
 */
class TaskToggleCompleteParams(
        val id: String,
        val completed: Boolean,
        val completedAt: Date?
) {
  constructor(task: Task) : this(task.id, task.completed, task.completedAt)
}