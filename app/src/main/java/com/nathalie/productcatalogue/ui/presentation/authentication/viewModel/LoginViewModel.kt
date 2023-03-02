package com.nathalie.productcatalogue.ui.presentation.authentication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nathalie.productcatalogue.ui.viewModel.BaseViewModel

class LoginViewModel : ViewModel() {
    class Provider() : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel() as T
        }
    }
}