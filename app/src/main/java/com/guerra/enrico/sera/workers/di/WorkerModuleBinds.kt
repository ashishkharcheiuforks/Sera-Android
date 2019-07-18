package com.guerra.enrico.sera.workers.di

import androidx.work.WorkerFactory
import com.guerra.enrico.sera.appinitializers.AppInitializer
import com.guerra.enrico.sera.appinitializers.TodosWorkerInitializer
import com.guerra.enrico.sera.workers.SyncTodosWorker
import com.guerra.enrico.sera.workers.TodosWorker
import com.guerra.enrico.sera.workers.TodosWorkerImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
import javax.inject.Singleton

/**
 * Created by enrico
 * on 17/12/2018.
 */
@Module
abstract class WorkerModuleBinds {
  @Binds
  @IntoMap
  @WorkerKey(SyncTodosWorker::class)
  abstract fun bindSyncTodosWorker(factory: SyncTodosWorker.Factory): ChildWorkerFactory

  @Binds
  @Singleton
  abstract fun provideSeraActions(bind: TodosWorkerImpl): TodosWorker

  @Binds
  @IntoSet
  abstract fun provideTodoWorkerInitializers(bind: TodosWorkerInitializer): AppInitializer
}