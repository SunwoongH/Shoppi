package com.joy.shoppi.repository.cart

import com.joy.shoppi.database.CartSectionItemDao
import com.joy.shoppi.model.CartSectionItem

class CartLocalDataSource(private val dao: CartSectionItemDao) : CartDataSource {

    override suspend fun addCartSectionItem(cartSectionItem: CartSectionItem) {
        dao.insert(cartSectionItem)
    }

    override suspend fun getCartSectionItems(): List<CartSectionItem> {
        return dao.get()
    }
}