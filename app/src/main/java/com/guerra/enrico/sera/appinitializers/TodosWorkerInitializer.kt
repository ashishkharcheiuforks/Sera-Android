package com.guerra.enrico.sera.appinitializers

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import com.guerra.enrico.sera.workers.TodosWorker
import com.guerra.enrico.sera.workers.di.SeraWorkerFactory
import javax.inject.Inject

/**
 * Created by enrico
 * on 20/12/2018.
 */
class TodosWorkerInitializer @Inject constructor(
        private val todosWorker: TodosWorker,
        private val workerFactory: SeraWorkerFactory
): AppInitializer{
    override fun init(application: Application) {
      val config = Configuration.Builder().setWorkerFactory(workerFactory).build()
      WorkManager.initialize(application, config)
      todosWorker.setUpNightTodoSync()
    }
}