package com.joy.shoppi.repository.cart

import com.joy.shoppi.model.CartSectionItem
import com.joy.shoppi.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepository(
    private val cartLocalDataSource: CartLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun addCartSectionItem(product: Product) {
        withContext(ioDispatcher) {
            val cartSectionItem = CartSectionItem(
                productId = product.productId,
                label = product.label,
                price = product.price,
                brandName = product.brandName ?: "",
                thumbnailImageUrl = product.thumbnailImageUrl ?: "",
                type = product.type ?: "",
                amount = 1
            )
            cartLocalDataSource.addCartSectionItem(cartSectionItem)
        }
    }

    suspend fun getCartSectionItems(): List<CartSectionItem> {
        return withContext(ioDispatcher) {
            cartLocalDataSource.getCartSectionItems()
        }
    }
}