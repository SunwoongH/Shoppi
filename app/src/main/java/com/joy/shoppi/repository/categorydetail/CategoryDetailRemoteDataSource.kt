package com.joy.shoppi.repository.categorydetail

import com.joy.shoppi.model.CategoryDetail
import com.joy.shoppi.network.ApiClient

class CategoryDetailRemoteDataSource(private val apiClient: ApiClient) : CategoryDetailDataSource {

    override suspend fun getCategoryDetail(): CategoryDetail {
        return apiClient.getCategoryDetail()
    }
}