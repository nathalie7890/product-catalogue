package com.nathalie.productcatalogue.ui.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.nathalie.productcatalogue.ui.viewModel.BaseViewModel
import kotlinx.coroutines.launch

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    abstract val viewModel: BaseViewModel
    var binding: T? = null

    abstract fun getLayoutResource(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBindView(view, savedInstanceState)
        onBindData(view)
    }

    open fun onBindView(view: View, savedInstanceState: Bundle?) {
        binding = DataBindingUtil.bind(view)
        lifecycleScope.launch {
            viewModel.error.collect {
                Snackbar.make(view, it, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    open fun onBindData(view: View) {

    }
}