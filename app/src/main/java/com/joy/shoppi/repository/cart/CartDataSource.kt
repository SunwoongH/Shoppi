package com.joy.shoppi.repository.cart

import com.joy.shoppi.model.CartSectionItem

interface CartDataSource {

    suspend fun addCartSectionItem(cartSectionItem: CartSectionItem)

    suspend fun getCartSectionItems(): List<CartSectionItem>
}