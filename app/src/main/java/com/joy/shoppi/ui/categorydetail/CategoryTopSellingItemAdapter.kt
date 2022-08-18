package com.joy.shoppi.ui.categorydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joy.shoppi.databinding.ItemCategoryTopSellingBinding
import com.joy.shoppi.model.Category
import com.joy.shoppi.ui.common.CategoryDiffCallback

class CategoryTopSellingItemAdapter :
    ListAdapter<Category, CategoryTopSellingItemAdapter.CategoryTopSellingItemViewHolder>(
        CategoryDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryTopSellingItemViewHolder {
        val binding = ItemCategoryTopSellingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryTopSellingItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryTopSellingItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class CategoryTopSellingItemViewHolder(private val binding: ItemCategoryTopSellingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.category = category
            binding.executePendingBindings()
        }
    }
}