package com.joy.shoppi.repository.category

import com.joy.shoppi.model.Category

class CategoryRepository(private val remoteDataSource: CategoryRemoteDataSource) {

    suspend fun getCategories(): List<Category> {
        return remoteDataSource.getCategories()
    }
}