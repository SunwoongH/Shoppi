package com.joy.shoppi.repository.category

import com.joy.shoppi.model.Category

interface CategoryDataSource {

    suspend fun getCategories(): List<Category>
}