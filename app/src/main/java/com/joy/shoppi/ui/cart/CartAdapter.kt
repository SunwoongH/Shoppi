package com.joy.shoppi.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joy.shoppi.databinding.ItemCartSectionBinding
import com.joy.shoppi.databinding.ItemCartSectionItemBinding
import com.joy.shoppi.model.CartProduct
import com.joy.shoppi.model.CartSection
import com.joy.shoppi.model.CartSectionItem

private const val VIEW_TYPE_SECTION = 0
private const val VIEW_TYPE_SECTION_ITEM = 1

class CartAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val cartProducts = mutableListOf<CartProduct>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_SECTION -> CartSectionViewHolder(
                ItemCartSectionBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> CartSectionItemViewHolder(
                ItemCartSectionItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CartSectionViewHolder -> {
                holder.bind(cartProducts[position] as CartSection)
            }
            is CartSectionItemViewHolder -> {
                holder.bind(cartProducts[position] as CartSectionItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (cartProducts[position]) {
            is CartSection -> VIEW_TYPE_SECTION
            is CartSectionItem -> VIEW_TYPE_SECTION_ITEM
        }
    }

    override fun getItemCount(): Int {
        return cartProducts.size
    }

    fun submitSectionAndSectionItems(items: List<CartSectionItem>) {
        val itemGroups = items.groupBy { it.brandName }
        val products = mutableListOf<CartProduct>()
        itemGroups.entries.forEach { entry ->
            products.add(CartSection(entry.key))
            products.addAll(entry.value)
        }
        cartProducts.addAll(products)
        notifyItemRangeInserted(cartProducts.size, products.size)
    }

    class CartSectionViewHolder(private val binding: ItemCartSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartSection: CartSection) {
            binding.cartSection = cartSection
            binding.executePendingBindings()
        }

    }

    class CartSectionItemViewHolder(private val binding: ItemCartSectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartSectionItem: CartSectionItem) {
            binding.cartSectionItem = cartSectionItem
            binding.executePendingBindings()
        }
    }
}