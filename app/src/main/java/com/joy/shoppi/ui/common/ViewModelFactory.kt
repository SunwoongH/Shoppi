package com.joy.shoppi.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joy.shoppi.AssetLoader
import com.joy.shoppi.network.ApiClient
import com.joy.shoppi.repository.category.CategoryRemoteDataSource
import com.joy.shoppi.repository.category.CategoryRepository
import com.joy.shoppi.repository.categorydetail.CategoryDetailRemoteDataSource
import com.joy.shoppi.repository.categorydetail.CategoryDetailRepository
import com.joy.shoppi.repository.home.HomeAssetDataSource
import com.joy.shoppi.repository.home.HomeRepository
import com.joy.shoppi.ui.category.CategoryViewModel
import com.joy.shoppi.ui.categorydetail.CategoryDetailViewModel
import com.joy.shoppi.ui.home.HomeViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(HomeRepository(HomeAssetDataSource(AssetLoader(context)))) as T
            }
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> {
                CategoryViewModel(CategoryRepository(CategoryRemoteDataSource(ApiClient.create()))) as T
            }
            modelClass.isAssignableFrom(CategoryDetailViewModel::class.java) -> {
                CategoryDetailViewModel(
                    CategoryDetailRepository(
                        CategoryDetailRemoteDataSource(
                            ApiClient.create()
                        )
                    )
                ) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}