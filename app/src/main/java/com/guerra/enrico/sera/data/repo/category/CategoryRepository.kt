package com.guerra.enrico.sera.data.repo.category

import com.guerra.enrico.sera.data.local.models.Category
import com.guerra.enrico.sera.data.result.Result
import com.guerra.enrico.sera.ui.todos.CategoryFilter
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by enrico
 * on 10/09/2018.
 */
interface CategoryRepository {
    fun getCategories(): Flowable<Result<List<Category>>>
    fun insertCategory(category: Category): Single<Result<Category>>
    fun deleteCategory(id: String): Single<Result<Any>>
    fun getCategoriesFilter(): Flowable<Result<List<CategoryFilter>>>
    fun fetchThenStoreCategories(): Completable
}