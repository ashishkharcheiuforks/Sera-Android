package com.guerra.enrico.local.dao.todos

import androidx.room.*
import com.guerra.enrico.models.todos.Category
import com.guerra.enrico.models.todos.Task
import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * Created by enrico
 * on 02/06/2018.
 */
@Dao
interface TaskDao {
  @Query("SELECT * FROM Task  WHERE completed = :completed")
  fun observe(
    completed: Boolean
  ): Flow<List<Task>>

  @Query("SELECT * FROM TASK WHERE id = :id")
  suspend fun get(id: String): Task

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(task: Task): Long

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(tasks: List<Task>): List<Long>

  @Query("SELECT * FROM Task WHERE title LIKE :text OR description LIKE :text ")
  suspend fun search(text: String): List<Task>

  @Update
  suspend fun update(task: Task)

  @Query("UPDATE Task SET title= :title, description= :description, completed= :completed, completedAt= :completedAt, todoWithin= :todoWithin, categories= :categories WHERE id =:id")
  suspend fun updateFields(
    id: String,
    title: String,
    description: String,
    completed: Boolean,
    completedAt: Date?,
    todoWithin: Date,
    categories: List<Category>
  ): Int

  @Query("DELETE FROM task WHERE id = :id")
  suspend fun removeOne(id: String): Int

  @Query("DELETE FROM Task")
  suspend fun clear()

  @Transaction
  suspend fun sync(tasks: List<Task>) {
    clear()
    insert(tasks)
  }
}