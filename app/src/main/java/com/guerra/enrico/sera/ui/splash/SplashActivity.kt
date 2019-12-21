package com.guerra.enrico.sera.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.guerra.enrico.base.util.viewModelProvider
import com.guerra.enrico.sera.R
import com.guerra.enrico.sera.data.succeeded
import com.guerra.enrico.sera.ui.base.BaseActivity
import com.guerra.enrico.sera.ui.login.LoginActivity
import com.guerra.enrico.sera.ui.todos.TodosFragment
import com.guerra.enrico.sera.data.Result
import javax.inject.Inject

/**
 * Created by enrico
 * on 17/10/2018.
 */
class SplashActivity : BaseActivity() {
  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private lateinit var viewModel: SplashViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)
    viewModel = viewModelProvider(viewModelFactory)
    initView()
  }

  override fun initView() {
    viewModel.validationAccessTokenResult.observe(this, Observer { userResult ->
      if (userResult == null) return@Observer
      if (userResult is Result.Loading) return@Observer
      if (userResult.succeeded) {
        gotoTodosActivity()
      }
      if (userResult is Result.Error) {
        gotoLoginActivity()
      }
    })
  }

  private fun gotoTodosActivity() {
    startActivity(Intent(this, TodosFragment::class.java))
    finish()
  }

  private fun gotoLoginActivity() {
    startActivity(Intent(this, LoginActivity::class.java))
    finish()
  }
}