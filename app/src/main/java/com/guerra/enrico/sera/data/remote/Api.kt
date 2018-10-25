package com.guerra.enrico.sera.data.remote

import com.guerra.enrico.sera.data.local.models.Category
import com.guerra.enrico.sera.data.local.models.Task
import com.guerra.enrico.sera.data.local.models.User
import com.guerra.enrico.sera.data.remote.request.AuthRequestParams
import com.guerra.enrico.sera.data.remote.request.CategoryParams
import com.guerra.enrico.sera.data.remote.request.TaskParams
import com.guerra.enrico.sera.data.remote.request.ValidateAccessTokenParams
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by enrico
 * on 17/08/2018.
 */
interface Api {
    @POST("auth/google/signin/callback")
    fun googleSignInCallback(
             @Body params: AuthRequestParams
    ): Single<ApiResponse<User>>

    @POST("auth/google/validate/token")
    fun validateAccessToken(
            @Body params: ValidateAccessTokenParams
    ): Single<ApiResponse<User>>

    // Categories

    @GET("categories")
    fun getCategories(
            @Header("x-token") accessToken: String,
            @Query("limit") limit: Int,
            @Query("skip") skip: Int
    ): Flowable<ApiResponse<List<Category>>>

    @POST("categories")
    fun insertCategory(
            @Header("x-token") accessToken: String,
            @Body params: CategoryParams
    ): Single<ApiResponse<Category>>

    @DELETE("categories")
    fun deleteCategory(
            @Header("x-token") accessToken: String,
            @Query("id") id: String
    ): Single<ApiResponse<Any>>

    // Tasks

    @GET("tasks")
    fun getTasks(
            @Header("x-token") accessToken: String,
            @Query("categoriesId") categoriesId: String,
            @Query("completed") completed: Boolean,
            @Query("limit") limit: Int,
            @Query("skip") skip: Int
    ): Flowable<ApiResponse<List<Task>>>

    @POST("tasks")
    fun insertTask(
            @Header("x-token") accessToken: String,
            @Body params: TaskParams
    ): Single<ApiResponse<Task>>

    @DELETE("tasks")
    fun deleteTask(
            @Header("x-token") accessToken: String,
            @Query("id") id: String
    ): Single<ApiResponse<Any>>

    @PATCH("tasks")
    fun updateTask(
            @Header("x-token") accessToken: String,
            @Body params: TaskParams
    ): Single<ApiResponse<Task>>
}