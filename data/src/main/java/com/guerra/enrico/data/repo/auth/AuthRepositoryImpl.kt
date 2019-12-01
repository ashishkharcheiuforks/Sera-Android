package com.guerra.enrico.data.repo.auth

import android.content.Context
import com.guerra.enrico.base.util.ConnectionHelper
import com.guerra.enrico.data.Result
import com.guerra.enrico.data.exceptions.ConnectionException
import com.guerra.enrico.data.local.db.LocalDbManager
import com.guerra.enrico.data.models.User
import com.guerra.enrico.data.exceptions.RemoteException
import com.guerra.enrico.data.remote.RemoteDataManager
import com.guerra.enrico.data.succeeded
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by enrico
 * on 14/10/2018.
 */
@Singleton
class AuthRepositoryImpl @Inject constructor(
        private val context: Context,
        private val remoteDataManager: RemoteDataManager,
        private val localDbManager: LocalDbManager
) : AuthRepository {

  // Sign in

  override suspend fun googleSignInCallback(code: String): Result<User> {
    val apiResponse = remoteDataManager.googleSignInCallback(code)
    if (apiResponse.success && apiResponse.data != null) {
      localDbManager.saveSession(apiResponse.data.user.id, apiResponse.data.accessToken)
      localDbManager.saveUser(apiResponse.data.user)
      return Result.Success(apiResponse.data.user)
    }
    return Result.Error(RemoteException.fromApiError(apiResponse.error))
  }

  override suspend fun validateAccessToken(): Result<User> {
    val session = localDbManager.getSession()
    if (!ConnectionHelper.isInternetConnectionAvailable(context)) {
      val user = localDbManager.getUser(session.userId)
      return Result.Success(user)
    }
    val apiResponse = remoteDataManager.validateAccessToken(session.accessToken)
    if (apiResponse.success && apiResponse.data != null) {
      localDbManager.saveSession(apiResponse.data.user.id, apiResponse.data.accessToken)
      localDbManager.saveUser(apiResponse.data.user)
      return Result.Success(apiResponse.data.user)
    }
    return Result.Error(RemoteException.fromApiError(apiResponse.error))
  }

  override suspend fun refreshToken(): Result<Unit> {
    val session = localDbManager.getSession()
    if (!ConnectionHelper.isInternetConnectionAvailable(context)) {
      return Result.Error(ConnectionException.internetConnectionNotAvailable())
    }
    val apiResponse = remoteDataManager.refreshAccessToken(session.accessToken)
    if (apiResponse.success && apiResponse.data != null) {
      localDbManager.saveSession(apiResponse.data.userId, apiResponse.data.accessToken)
      return Result.Success(Unit)
    }
    return Result.Error(RemoteException.fromApiError(apiResponse.error))
  }

  override suspend fun <T> refreshTokenIfNotAuthorized(block: suspend () -> Result<T>): Result<T> {
    val result = block()
    if (result is Result.Error && result.exception is RemoteException) {
      if (result.exception.isExpiredSession()) {
        val refreshResult = refreshToken()
        if (refreshResult.succeeded) {
          return block()
        }
      }
    }
    return result
  }
}