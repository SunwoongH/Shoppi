package com.joy.shoppi.repository.categorydetail

import com.joy.shoppi.model.CategoryDetail

interface CategoryDetailDataSource {

    suspend fun getCategoryDetail(): CategoryDetail
}