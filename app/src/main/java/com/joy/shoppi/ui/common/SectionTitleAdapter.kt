package com.joy.shoppi.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joy.shoppi.databinding.ItemTitleBinding
import com.joy.shoppi.model.Title

class SectionTitleAdapter :
    ListAdapter<Title, SectionTitleAdapter.SectionTitleViewHolder>(
        SectionTitleDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SectionTitleViewHolder {
        val binding = ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SectionTitleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SectionTitleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SectionTitleViewHolder(private val binding: ItemTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(title: Title) {
            binding.title = title
            binding.executePendingBindings()
        }
    }
}

class SectionTitleDiffCallback : DiffUtil.ItemCallback<Title>() {

    override fun areItemsTheSame(oldItem: Title, newItem: Title): Boolean {
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItem: Title, newItem: Title): Boolean {
        return oldItem == newItem
    }
}