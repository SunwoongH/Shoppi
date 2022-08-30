package com.joy.shoppi.repository.productdetail

import com.joy.shoppi.model.Product

class ProductDetailRepository(private val productDetailRemoteDataSource: ProductDetailRemoteDataSource) {

    suspend fun getProductDetail(productId: String): Product {
        return productDetailRemoteDataSource.getProductDetail(productId)
    }
}