package com.guerra.enrico.sera.workers.di

import androidx.work.ListenableWorker
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by enrico
 * on 17/07/2019.
 */
@MapKey
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class WorkerKey(val value: KClass<out ListenableWorker>)