package com.nathalie.productcatalogue.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nathalie.productcatalogue.R
import com.nathalie.productcatalogue.data.model.Product
import com.nathalie.productcatalogue.databinding.ItemProductLayoutBinding
import com.nathalie.productcatalogue.ui.utils.update

class ProductAdapter(private var items: MutableList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ItemProductHolder>() {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProductHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductLayoutBinding.inflate(layoutInflater, parent, false)
        return ItemProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemProductHolder, position: Int) {
        val item = items[position]

        holder.binding.run {
            val price = "RM ${item.price}"
            tvTitle.text = item.title
            tvPrice.text = price
            tvDesc.text = item.description

            if (URLUtil.isValidUrl(item.thumbnail)) {
                Glide.with(holder.binding.root)
                    .load(item.thumbnail).into(ivImg)
            } else {
                Glide.with(holder.binding.root)
                    .load(R.drawable.no_image_found).into(ivImg)
            }

            cvProductItem.setOnClickListener {
                listener?.onClick(item)
            }
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

    fun setProducts(items: MutableList<Product>) {
        this.items = items
        update(mutableListOf(), this.items) { product1, product2 ->
            product1.id == product2.id
        }
    }

    class ItemProductHolder(val binding: ItemProductLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onClick(item: Product)
    }
}