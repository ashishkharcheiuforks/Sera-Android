package com.guerra.enrico.sera.di.module

import com.guerra.enrico.sera.di.PerActivity
import com.guerra.enrico.sera.di.PerFragment
import com.guerra.enrico.sera.ui.goals.GoalsFragment
import com.guerra.enrico.sera.ui.goals.GoalsModule
import com.guerra.enrico.sera.ui.login.LoginActivity
import com.guerra.enrico.sera.ui.login.LoginModule
import com.guerra.enrico.sera.ui.results.ResultsFragment
import com.guerra.enrico.sera.ui.results.ResultsModule
import com.guerra.enrico.sera.ui.settings.SettingsFragment
import com.guerra.enrico.sera.ui.settings.SettingsModule
import com.guerra.enrico.sera.ui.splash.SplashActivity
import com.guerra.enrico.sera.ui.splash.SplashModule
import com.guerra.enrico.sera.ui.todos.TodosFragment
import com.guerra.enrico.sera.ui.todos.TodosModule
import com.guerra.enrico.sera.ui.todos.add.TodoAddActivity
import com.guerra.enrico.sera.ui.todos.add.TodoAddModel
import com.guerra.enrico.sera.ui.todos.search.TodoSearchActivity
import com.guerra.enrico.sera.ui.todos.search.TodoSearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by enrico
 * on 29/05/2018.
 */

@Module
abstract class ActivityBindingModule {
  @PerActivity
  @ContributesAndroidInjector(
    modules = [
      SplashModule::class
    ]
  )
  abstract fun splashActivity(): SplashActivity

  @PerActivity
  @ContributesAndroidInjector(
    modules = [
      LoginModule::class
    ]
  )
  abstract fun loginActivity(): LoginActivity

  @PerFragment
  @ContributesAndroidInjector(
    modules = [
      TodosModule::class
    ]
  )
  abstract fun todosFragment(): TodosFragment

  @PerActivity
  @ContributesAndroidInjector(
    modules = [
      TodoAddModel::class
    ]
  )
  abstract fun todoAddActivity(): TodoAddActivity

  @PerActivity
  @ContributesAndroidInjector(
    modules = [
      TodoSearchModule::class
    ]
  )
  abstract fun todoSearchActivity(): TodoSearchActivity

  @PerFragment
  @ContributesAndroidInjector(
    modules = [
      GoalsModule::class
    ]
  )
  abstract fun goalsActivity(): GoalsFragment

  @PerFragment
  @ContributesAndroidInjector(
    modules = [
      ResultsModule::class
    ]
  )
  abstract fun resultsFragment(): ResultsFragment

  @PerFragment
  @ContributesAndroidInjector(
    modules = [
      SettingsModule::class
    ]
  )
  abstract fun settingsFragment(): SettingsFragment
}