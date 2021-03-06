package com.guerra.enrico.domain.interactors.todos

import com.guerra.enrico.base.Result
import com.guerra.enrico.base.dispatcher.CoroutineDispatcherProvider
import com.guerra.enrico.domain.Interactor
import com.guerra.enrico.sera.data.repo.auth.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by enrico
 * on 12/11/2019.
 */
class ValidateToken @Inject constructor(
  private val authRepository: AuthRepository,
  coroutineDispatcherProvider: CoroutineDispatcherProvider
) : Interactor<Unit, Result<com.guerra.enrico.models.User>>() {
  override val dispatcher: CoroutineDispatcher = coroutineDispatcherProvider.io()

  override suspend fun doWork(params: Unit): Result<com.guerra.enrico.models.User> {
    val result = authRepository.refreshTokenIfNotAuthorized({
      authRepository.validateAccessToken()
    }).first()
    if (result is Result.Success) {
//      syncTasksAndCategories() TODO: Replace with fetch all data or maybe sync is fine
    }
    return result
  }

}