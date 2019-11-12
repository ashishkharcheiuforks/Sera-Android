package com.guerra.enrico.data.remote

import com.guerra.enrico.data.remote.response.ApiError


/**
 * Created by enrico
 * on 17/08/2018.
 */
class ApiException(val code: Int, val internalServerError: String, val messageServer: String) : Exception() {
  constructor(error: ApiError) : this(error.code, error.internalError, error.message)

  companion object {
    const val UNAUTHENTICATED = 800
    const val EXPIRED_SESSION = 801
  }

  fun isUnauthenticated() = code == UNAUTHENTICATED
  fun isExpiredSession() = code == EXPIRED_SESSION
}