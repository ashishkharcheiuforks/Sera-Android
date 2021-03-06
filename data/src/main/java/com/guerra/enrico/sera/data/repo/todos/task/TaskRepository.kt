package com.guerra.enrico.sera.data.repo.todos.task

import com.guerra.enrico.base.Result
import com.guerra.enrico.models.todos.Category
import com.guerra.enrico.models.todos.Task
import com.guerra.enrico.models.sync.SyncAction
import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * Created by enrico
 * on 10/09/2018.
 */
interface TaskRepository {

  suspend fun pullTasks(from: Date?): Result<Unit>

  suspend fun insertTask(task: Task): Result<Task>

  suspend fun deleteTask(task: Task): Result<Int>

  suspend fun updateTask(task: Task): Result<Task>

  fun getTasks(
          searchText: String = "",
          category: Category?,
          completed: Boolean = false
  ): Flow<List<Task>>

  suspend fun syncAction(syncAction: SyncAction): Result<Any>
}