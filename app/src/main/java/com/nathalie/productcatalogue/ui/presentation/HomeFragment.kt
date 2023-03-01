package com.nathalie.productcatalogue.ui.presentation


import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nathalie.productcatalogue.R
import com.nathalie.productcatalogue.data.model.Product
import com.nathalie.productcatalogue.databinding.FragmentHomeBinding
import com.nathalie.productcatalogue.ui.adapter.ProductAdapter
import com.nathalie.productcatalogue.ui.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var adapter: ProductAdapter
    override val viewModel: HomeViewModel by viewModels()

    override fun getLayoutResource() = R.layout.fragment_home


    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
        setupAdapter()

        setFragmentResultListener("finish_add_product") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.onRefresh()
            }
        }
        setFragmentResultListener("finish_edit_product") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.onRefresh()
            }
        }

        setFragmentResultListener("finish_delete_product") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.onRefresh()
            }
        }

        binding!!.btnAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToAddProduct()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        viewModel.products.observe(viewLifecycleOwner) {
            adapter.setProducts(it.toMutableList())
        }
    }


    fun setupAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter(mutableListOf())
        adapter.listener = object : ProductAdapter.Listener {
            override fun onClick(item: Product) {
                val action = item.id?.let { HomeFragmentDirections.actionHomeToDetail(it) }
                if (action != null) {
                    NavHostFragment.findNavController(this@HomeFragment).navigate(action)
                }
            }
        }

        binding?.rvProducts?.adapter = adapter
        binding?.rvProducts?.layoutManager = layoutManager
    }
}