package com.joy.shoppi.ui.categorydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joy.shoppi.databinding.ItemCategoryTopSellingSectionBinding
import com.joy.shoppi.model.TopSelling

class CategoryTopSellingSectionAdapter :
    ListAdapter<TopSelling, CategoryTopSellingSectionAdapter.CategoryTopSellingSectionViewHolder>(
        CategoryTopSellingSectionDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryTopSellingSectionViewHolder {
        val binding = ItemCategoryTopSellingSectionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryTopSellingSectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryTopSellingSectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CategoryTopSellingSectionViewHolder(private val binding: ItemCategoryTopSellingSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val categoryTopSellingItemAdapter = CategoryTopSellingItemAdapter()

        init {
            binding.itemCategoryTopSellingSectionRv.adapter = categoryTopSellingItemAdapter
        }

        fun bind(topSelling: TopSelling) {
            binding.title = topSelling.title
            binding.executePendingBindings()
            categoryTopSellingItemAdapter.submitList(topSelling.categories)
        }
    }
}

class CategoryTopSellingSectionDiffCallback : DiffUtil.ItemCallback<TopSelling>() {

    override fun areItemsTheSame(oldItem: TopSelling, newItem: TopSelling): Boolean {
        return oldItem.title.text == newItem.title.text
    }

    override fun areContentsTheSame(oldItem: TopSelling, newItem: TopSelling): Boolean {
        return oldItem == newItem
    }
}