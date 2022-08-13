package com.joy.shoppi.repository.category

import com.joy.shoppi.model.Category
import com.joy.shoppi.network.ApiClient

class CategoryRemoteDataSource(private val apiClient: ApiClient): CategoryDataSource {

    override suspend fun getCategories(): List<Category> {
        return apiClient.getCategories()
    }
}