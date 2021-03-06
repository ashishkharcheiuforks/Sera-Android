package com.guerra.enrico.sera.ui.todos.add.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.guerra.enrico.base.Result
import com.guerra.enrico.base.extensions.observe
import com.guerra.enrico.sera.R
import com.guerra.enrico.sera.ui.base.BaseFragment
import com.guerra.enrico.sera.ui.todos.adapter.CategoryAdapter
import com.guerra.enrico.sera.ui.todos.add.TodoAddViewModel
import com.guerra.enrico.sera.ui.todos.presentation.CategoryPresentation
import com.guerra.enrico.sera.widget.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_todo_add_select_category.*
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by enrico
 * on 19/10/2018.
 */
class SelectCategoryFragment : BaseFragment() {
  private lateinit var root: WeakReference<View>

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel: TodoAddViewModel by activityViewModels { viewModelFactory }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_todo_add_select_category, container, false)
    root = WeakReference(view)
    return view
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    val gridLayoutManager = GridLayoutManager(context, 2)
    val filterAdapter =
      CategoryAdapter { categoryFilter ->
        val checked = !categoryFilter.isChecked
        viewModel.toggleCategory(categoryFilter, checked)
      }
    recycler_view_categories.apply {
      layoutManager = gridLayoutManager
      adapter = filterAdapter
      addItemDecoration(
        GridSpacingItemDecoration(
          2,
          resources.getDimensionPixelOffset(R.dimen.padding_s),
          true
        )
      )
    }
    observe(viewModel.categoriesPresentationResult) { processCategoryListResponse(it) }
  }

  private fun processCategoryListResponse(categoriesPresentationResult: Result<List<CategoryPresentation>>?) {
    if (categoriesPresentationResult == null) {
      return
    }
    if (categoriesPresentationResult is Result.Loading) {
      showOverlayLoader()
      return
    }
    hideOverlayLoader()
    if (categoriesPresentationResult is Result.Success) {
      (recycler_view_categories.adapter as CategoryAdapter).submitList(categoriesPresentationResult.data)
      observeSelectedCategory()
      return
    }
    if (categoriesPresentationResult is Error) {
      root.get()?.let {
        Snackbar.make(
          it, categoriesPresentationResult.message
            ?: "An error occur while fetching categories", Snackbar.LENGTH_LONG
        ).show()
      }
    }
  }

  private fun observeSelectedCategory() {
    buttonNext.setOnClickListener {
      if (viewModel.selectedCategory == null) {
        showSnackbar(
          resources.getString(R.string.message_select_category),
          it
        )
      } else {
        viewModel.goToNextStep(StepEnum.ADD_TASK)
      }
    }
  }
}