package com.guerra.enrico.sera.ui.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.transition.MaterialFadeThrough
import com.guerra.enrico.sera.R
import com.guerra.enrico.sera.databinding.FragmentResultsBinding
import com.guerra.enrico.sera.data.exceptions.MessageExceptionManager
import com.guerra.enrico.sera.ui.base.BaseFragment
import javax.inject.Inject

/**
 * Created by enrico
 * on 30/07/2019.
 */
class ResultsFragment : BaseFragment() {
  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel: ResultsViewModel by viewModels { viewModelFactory }

  private lateinit var binding: FragmentResultsBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentResultsBinding.inflate(inflater, container, false).apply {
      lifecycleOwner = viewLifecycleOwner
    }
    return binding.root
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enterTransition = MaterialFadeThrough.create(requireContext())
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView()
  }

  private fun initView() {
    binding.toolbar.toolbarTitle.text = resources.getString(R.string.title_results)
    val messageResources = MessageExceptionManager(Exception()).getResources()
    binding.messageLayout.apply {
      setImage(messageResources.icon)
      setMessage(messageResources.message)
      setButton(resources.getString(R.string.message_layout_button_try_again)) {}
      show()
    }
  }
}