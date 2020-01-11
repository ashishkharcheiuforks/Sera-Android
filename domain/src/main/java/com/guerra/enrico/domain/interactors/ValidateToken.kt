package com.guerra.enrico.domain.interactors

import com.guerra.enrico.base.dispatcher.CoroutineDispatcherProvider
import com.guerra.enrico.sera.data.Result
import com.guerra.enrico.sera.data.models.User
import com.guerra.enrico.sera.repo.auth.AuthRepository
import com.guerra.enrico.domain.Interactor
import com.guerra.enrico.domain.invoke
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by enrico
 * on 12/11/2019.
 */
class ValidateToken @Inject constructor(
  private val authRepository: AuthRepository,
  private val syncTasksAndCategories: SyncTasksAndCategories,
  coroutineDispatcherProvider: CoroutineDispatcherProvider
) : Interactor<Unit, Result<User>>() {
  override val dispatcher: CoroutineDispatcher = coroutineDispatcherProvider.io()

  override suspend fun doWork(params: Unit): Result<User> {
    val result = authRepository.refreshTokenIfNotAuthorized({
      authRepository.validateAccessToken()
    }).first()
    if (result is Result.Success) {
      syncTasksAndCategories()
    }
    return result
  }

}