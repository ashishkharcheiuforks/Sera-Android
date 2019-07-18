package com.guerra.enrico.sera.workers.di

import androidx.work.RxWorker
import dagger.MapKey
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.Multibinds
import kotlin.reflect.KClass

/**
 * Created by enrico
 * on 17/12/2018.
 */
//internal object AndroidWorkerInjector {
//    fun inject(worker: RxWorker) {
//        val application = worker.applicationContext
//        if (application !is HasRxWorkerInjector) {
//            throw Throwable("${application.javaClass.canonicalName} does not implement ${HasRxWorkerInjector::class.java.canonicalName}")
//        }
//        val workerInjector = (application as HasRxWorkerInjector).workerInjector()
//        workerInjector.inject(worker)
//    }
//}
//
//interface HasRxWorkerInjector {
//    fun workerInjector(): AndroidInjector<RxWorker>
//}
//
//@Module
//abstract class AndroidRxWorkerInjectorModule {
//    @Multibinds
//    abstract fun rxworkerInjectorFactories(): Map<String, AndroidInjector.Factory<out RxWorker>>
//}

