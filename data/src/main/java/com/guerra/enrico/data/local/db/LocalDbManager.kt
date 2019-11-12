package com.guerra.enrico.data.local.db

import com.guerra.enrico.data.models.Category
import com.guerra.enrico.data.models.Session
import com.guerra.enrico.data.models.Task
import com.guerra.enrico.data.models.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by enrico
 * on 31/05/2018.
 */
interface LocalDbManager {
  // Session
  fun getSession(): Single<Session>

  fun getSessionAccessToken(): Single<String>
  fun saveSession(userId: String, accessToken: String): Completable

  // User
  fun getUser(userId: String): Single<User>

  fun saveUser(user: User): Completable

  // Categories
  fun observeAllCategories(): Flowable<List<Category>>

  fun saveCategorySingle(category: Category): Single<Long>
  fun saveCategoriesSingle(categories: List<Category>): Single<List<Long>>
  fun saveCategories(categories: List<Category>)
  fun clearCategoriesCompletable(): Completable
  fun updateCategorySingle(category: Category): Single<Int>
  fun deleteCategorySingle(category: Category): Single<Int>

  // Tasks
  fun observeTasks(
          completed: Boolean = false
  ): Flowable<List<Task>>

  fun saveTaskSingle(task: Task): Single<Long>
  fun saveTasksSingle(tasks: List<Task>): Single<List<Long>>
  fun saveTasks(tasks: List<Task>)
  fun clearTasksCompletable(): Completable
  fun searchTaskSingle(searchText: String): Single<List<Task>>
  fun updateTaskSingle(task: Task): Single<Int>
  fun deleteTaskSingle(task: Task): Single<Int>
}