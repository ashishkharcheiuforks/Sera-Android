package com.guerra.enrico.sera.data.remote.request

/**
 * Created by enrico
 * on 15/10/2018.
 */
data class AuthRequestParams (
        var code: String,
        var platform: String = "android"
)