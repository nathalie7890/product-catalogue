package com.nathalie.productcatalogue.ui.presentation.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nathalie.productcatalogue.R
import com.nathalie.productcatalogue.databinding.FragmentLoginBinding
import com.nathalie.productcatalogue.ui.presentation.BaseFragment
import com.nathalie.productcatalogue.ui.presentation.authentication.viewModel.LoginViewModel
import com.nathalie.productcatalogue.ui.viewModel.BaseViewModel

class LoginFragment(override val viewModel: BaseViewModel) : BaseFragment<FragmentLoginBinding>() {
    override fun getLayoutResource() = R.layout.fragment_login

    override fun onBindData(view: View) {
        super.onBindData(view)
    }

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
    }

}