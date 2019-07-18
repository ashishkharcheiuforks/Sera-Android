package com.guerra.enrico.sera.di.component

import com.guerra.enrico.sera.SeraApplication
import com.guerra.enrico.sera.di.module.*
import com.guerra.enrico.sera.workers.di.WorkerModule
import com.guerra.enrico.sera.workers.di.WorkerModuleBinds
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by enrico
 * on 30/05/2018.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppAssistedModule::class,
    WorkerModule::class,
    AppModule::class,
    ActivityBindingModule::class,
    ViewModelModule::class,
    RetrofitModule::class,
    WorkerModuleBinds::class
])
interface AppComponent: AndroidInjector<SeraApplication> {
   @Component.Factory
   interface Factory {
       fun create(@BindsInstance application: SeraApplication): AppComponent
   }
}