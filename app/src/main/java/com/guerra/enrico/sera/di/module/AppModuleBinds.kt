package com.guerra.enrico.sera.di.module

import android.app.Application
import android.content.Context
import com.guerra.enrico.sera.SeraApplication
import com.guerra.enrico.sera.data.local.db.LocalDbManager
import com.guerra.enrico.sera.data.local.db.LocalDbManagerImpl
import com.guerra.enrico.sera.data.local.db.SeraDatabase
import com.guerra.enrico.sera.data.local.prefs.PreferencesManager
import com.guerra.enrico.sera.data.local.prefs.PreferencesManagerImpl
import com.guerra.enrico.sera.data.remote.RemoteDataManager
import com.guerra.enrico.sera.data.remote.RemoteDataManagerImpl
import com.guerra.enrico.sera.data.repo.auth.AuthRepository
import com.guerra.enrico.sera.data.repo.auth.AuthRepositoryImpl
import com.guerra.enrico.sera.data.repo.category.CategoryRepository
import com.guerra.enrico.sera.data.repo.category.CategoryRepositoryImpl
import com.guerra.enrico.sera.data.repo.task.TaskRepository
import com.guerra.enrico.sera.data.repo.task.TaskRepositoryImpl
import com.guerra.enrico.sera.workers.TodosWorker
import com.guerra.enrico.sera.workers.TodosWorkerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by enrico
 * on 20/12/2018.
 */
@Module
abstract class AppModuleBinds {
  //    @Singleton
//    @Binds
//    abstract fun provideTodoJob(bind: TodosWorkerImpl): TodosWorker
  @Binds
  abstract fun provideApplication(application: SeraApplication): Application

  @Binds
  @Singleton
  abstract fun provideAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

  @Binds
  @Singleton
  abstract fun provideCategoryRepository(categoryRepository: CategoryRepositoryImpl): CategoryRepository

  @Binds
  @Singleton
  abstract fun provideTaskRepository(taskRepository: TaskRepositoryImpl): TaskRepository

  @Binds
  @Singleton
  abstract fun provideLocalDbManager(localDbManager: LocalDbManagerImpl): LocalDbManager

  @Binds
  @Singleton
  abstract fun providePreferencesManager(preferencesManager: PreferencesManagerImpl): PreferencesManager

  @Binds
  @Singleton
  abstract fun provideRemoteDataManager(remoteDataManager: RemoteDataManagerImpl): RemoteDataManager
}