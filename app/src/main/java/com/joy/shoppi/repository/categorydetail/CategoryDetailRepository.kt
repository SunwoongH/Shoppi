package com.joy.shoppi.repository.categorydetail

import com.joy.shoppi.model.CategoryDetail

class CategoryDetailRepository(private val categoryDetailRemoteDataSource: CategoryDetailRemoteDataSource) {

    suspend fun getCategoryDetail(): CategoryDetail {
        return categoryDetailRemoteDataSource.getCategoryDetail()
    }
}