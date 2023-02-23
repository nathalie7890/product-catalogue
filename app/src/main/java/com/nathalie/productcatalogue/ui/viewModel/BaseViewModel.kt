package com.nathalie.productcatalogue.ui.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseViewModel : ViewModel() {
    val error: MutableSharedFlow<String> = MutableSharedFlow()
    suspend fun <T> safeApiCall(callback: suspend () -> T): T? {
        return try {
            callback.invoke()
        } catch (e: Exception) {
            error.emit(e.message.toString())
            null
        }
    }
}