package com.nathalie.productcatalogue.ui.presentation

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.nathalie.productcatalogue.R
import com.nathalie.productcatalogue.data.api.RetrofitClient
import com.nathalie.productcatalogue.data.repository.ProductRepository
import com.nathalie.productcatalogue.databinding.FragmentAddProductBinding
import com.nathalie.productcatalogue.ui.viewModel.AddProductViewModel
import com.nathalie.productcatalogue.ui.viewModel.HomeViewModel

class AddProductFragment : BaseFragment<FragmentAddProductBinding>() {
    private lateinit var filePickerLauncher: ActivityResultLauncher<String>
    private var bytes: ByteArray? = null
    override val viewModel: AddProductViewModel by viewModels {
        AddProductViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

    override fun getLayoutResource() = R.layout.fragment_add_product
    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
    }
}