package com.guerra.enrico.sera.workers.di

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by enrico
 * on 17/07/2019.
 */
class SeraWorkerFactory @Inject constructor(
        private val creators: Map<Class<out ListenableWorker>, @JvmSuppressWildcards Provider<ChildWorkerFactory>>
) : WorkerFactory() {
  override fun createWorker(
          appContext: Context,
          workerClassName: String,
          workerParameters: WorkerParameters
  ): ListenableWorker? {
    val workerClass = Class.forName(workerClassName)
    val foundEntry = creators.entries.find { workerClass.isAssignableFrom(it.key) }
    val factory = foundEntry?.value
            ?: throw IllegalArgumentException("unknown worker class name: $workerClassName")
    return factory.get().create(appContext, workerParameters)
  }
}