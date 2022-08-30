package com.joy.shoppi.ui.productdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joy.shoppi.databinding.ItemProductDescriptionBinding
import com.joy.shoppi.model.Description

class ProductDescriptionAdapter :
    ListAdapter<Description, ProductDescriptionAdapter.ProductDescriptionViewHolder>(
        ProductDescriptionDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductDescriptionViewHolder {
        val binding = ItemProductDescriptionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductDescriptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductDescriptionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductDescriptionViewHolder(private val binding: ItemProductDescriptionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(description: Description) {
            binding.imageUrl = description.imageUrl
            binding.executePendingBindings()
        }
    }
}

class ProductDescriptionDiffCallback : DiffUtil.ItemCallback<Description>() {

    override fun areItemsTheSame(oldItem: Description, newItem: Description): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Description, newItem: Description): Boolean {
        return oldItem == newItem
    }
}