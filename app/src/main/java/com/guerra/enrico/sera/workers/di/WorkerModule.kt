package com.guerra.enrico.sera.workers.di

import android.content.Context
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by enrico
 * on 17/12/2018.
 */
@Module(includes = [WorkerModuleBinds::class, WorkerAssistedModule::class])
class WorkerModule {
  @Provides
  @Singleton
  fun provideWorkManager(context: Context): WorkManager = WorkManager.getInstance(context)

  @Provides
  fun provideWorkConfiguration(workerFactory: SeraWorkerFactory): Configuration = Configuration.Builder().setWorkerFactory(workerFactory).build()
}