package com.joy.shoppi.repository.productdetail

import com.joy.shoppi.model.Product
import com.joy.shoppi.network.ApiClient

class ProductDetailRemoteDataSource(private val apiClient: ApiClient) : ProductDetailDataSource {

    override suspend fun getProductDetail(productId: String): Product {
        return apiClient.getProductDetail(productId)
    }
}