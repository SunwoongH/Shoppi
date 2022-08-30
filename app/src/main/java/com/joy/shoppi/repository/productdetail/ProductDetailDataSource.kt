package com.joy.shoppi.repository.productdetail

import com.joy.shoppi.model.Product

interface ProductDetailDataSource {

    suspend fun getProductDetail(productId: String): Product
}