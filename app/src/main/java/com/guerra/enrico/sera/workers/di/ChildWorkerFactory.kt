package com.guerra.enrico.sera.workers.di

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

/**
 * Created by enrico
 * on 17/07/2019.
 */
interface ChildWorkerFactory {
  fun create(context: Context, params: WorkerParameters): ListenableWorker
}