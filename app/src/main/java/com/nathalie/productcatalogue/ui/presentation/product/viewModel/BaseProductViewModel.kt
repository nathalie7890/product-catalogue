package com.nathalie.productcatalogue.ui.presentation.product.viewModel

import com.nathalie.productcatalogue.data.repository.FireStoreProductRepository
import com.nathalie.productcatalogue.data.repository.ProductRepository
import com.nathalie.productcatalogue.ui.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseProductViewModel(val repo: ProductRepository) : BaseViewModel() {
    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()
}