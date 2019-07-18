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
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by enrico
 * on 30/05/2018.
 */
@Module(includes = [ViewModelModule::class, AppModuleBinds::class])
class AppModule {
    @Provides
    fun provideContext(application: SeraApplication): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideSeraDatabase(context: Context): SeraDatabase = SeraDatabase.getInstance(context)
}