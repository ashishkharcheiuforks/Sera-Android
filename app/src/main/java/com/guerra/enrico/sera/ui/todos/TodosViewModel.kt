package com.guerra.enrico.sera.ui.todos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.guerra.enrico.domain.interactors.SyncTasksAndCategories
import com.guerra.enrico.sera.data.Event
import com.guerra.enrico.sera.data.Result
import com.guerra.enrico.sera.data.models.Category
import com.guerra.enrico.sera.data.models.Task
import com.guerra.enrico.domain.interactors.UpdateTaskCompleteState
import com.guerra.enrico.domain.invoke
import com.guerra.enrico.domain.observers.ObserveCategories
import com.guerra.enrico.domain.observers.ObserveTasks
import com.guerra.enrico.sera.R
import com.guerra.enrico.sera.ui.base.BaseViewModel
import com.guerra.enrico.sera.ui.base.SnackbarMessage
import com.guerra.enrico.sera.ui.todos.entities.TaskView
import com.guerra.enrico.sera.ui.todos.entities.tasksToModelForView
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by enrico
 * on 30/05/2018.
 */
class TodosViewModel @Inject constructor(
  observeCategories: ObserveCategories,
  private val observeTasks: ObserveTasks,
  private val updateTaskCompleteState: UpdateTaskCompleteState,
  private val syncTasksAndCategories: SyncTasksAndCategories
) : BaseViewModel(), EventActions {
  private var searchText: String = ""
  private var searchSelectedCategory: Category? = null

  private val _categoriesResult: LiveData<Result<List<Category>>> = observeCategories.observe()
    .onStart { Result.Loading }
    .map { Result.Success(it) }
    .asLiveData()

  private var _tasksResult: LiveData<Result<List<Task>>> = observeTasks.observe()
    .onStart { Result.Loading }
    .map { Result.Success(it) }
    .asLiveData()

  private val _tasksViewResult = MediatorLiveData<Result<List<TaskView>>>()
  val tasksViewResult: LiveData<Result<List<TaskView>>>
    get() = _tasksViewResult

  private val _categories = MediatorLiveData<List<Category>>()
  val categories: LiveData<List<Category>>
    get() = _categories

  private val _snackbarMessage = MutableLiveData<Event<SnackbarMessage>>()
  val snackbarMessage: LiveData<Event<SnackbarMessage>>
    get() = _snackbarMessage

  private val _swipeRefresh = MutableLiveData<Boolean>(false)
  val swipeRefresh: LiveData<Boolean>
    get() = _swipeRefresh

  init {
    _categories.addSource(_categoriesResult) { result ->
      if (result is Result.Success) {
        _categories.value = result.data
      } else {
        _categories.value = emptyList()
      }
    }

    _tasksViewResult.addSource(_tasksResult) { result ->
      _tasksViewResult.value = when (result) {
        is Result.Success -> Result.Success(tasksToModelForView(result.data))
        is Result.Loading -> Result.Loading
        is Result.Error -> Result.Error(result.exception)
      }
    }

    // Start load tasks
    observeTasks(ObserveTasks.Params())
    observeCategories()
  }

  /**
   * Reload tasksResult
   */
  fun onRefreshData() {
    viewModelScope.launch {
      _swipeRefresh.value = true
      syncTasksAndCategories()
      _swipeRefresh.value = false
    }
  }

  /**
   * Search tasks by text
   * @param text text to onSearch
   */
  fun onSearch(text: String) {
    searchText = text
    searchSelectedCategory = null
    observeTasks(ObserveTasks.Params(text = searchText))
  }

  /**
   * Search task by category
   * @param category selected category
   */
  fun onSearchCategory(category: Category?) {
    searchText = ""
    searchSelectedCategory = category
    observeTasks(ObserveTasks.Params(category = category))
  }

  /**
   * Handle when a task is clicked
   * @param task selected task
   */
  override fun onTaskClick(task: Task) {
//    val currentTasksResult = _tasksViewResult.value ?: return
//    if (currentTasksResult is Result.Success) {
//      _tasksViewResult.value =
//        Result.Success(currentTasksResult.data.map { it.copy(isExpanded = it.task.id == task.id && !it.isExpanded) })
//    }
  }

  /**
   * Set task as completed on swipe out
   * @param taskPosition task position
   */
  fun onTaskSwipeToComplete(taskPosition: Int) {
    val tasksViewValues = _tasksViewResult.value
    if (tasksViewValues is Result.Success && taskPosition in tasksViewValues.data.indices) {
      val taskView = tasksViewValues.data[taskPosition]
      viewModelScope.launch {
        val completeTaskResult = updateTaskCompleteState(taskView.task)
        _snackbarMessage.postValue(
          when (completeTaskResult) {
            is Result.Error -> Event(
              SnackbarMessage(
                message = completeTaskResult.exception.message,
                actionId = R.string.snackbar_action_retry,
                action = { onTaskSwipeToComplete(taskPosition) })
            )
            is Result.Success -> Event(
              SnackbarMessage(
                messageId = R.string.message_task_completed,
                actionId = R.string.snackbar_action_abort,
                action = {}
                // TODO: Abort action
              )
            )
            else -> return@launch
          }
        )
      }
    }
  }
}