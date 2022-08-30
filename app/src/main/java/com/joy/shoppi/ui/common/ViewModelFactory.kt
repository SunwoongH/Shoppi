package com.joy.shoppi.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joy.shoppi.AssetLoader
import com.joy.shoppi.ServiceLocator
import com.joy.shoppi.repository.category.CategoryRemoteDataSource
import com.joy.shoppi.repository.category.CategoryRepository
import com.joy.shoppi.repository.categorydetail.CategoryDetailRemoteDataSource
import com.joy.shoppi.repository.categorydetail.CategoryDetailRepository
import com.joy.shoppi.repository.home.HomeAssetDataSource
import com.joy.shoppi.repository.home.HomeRepository
import com.joy.shoppi.repository.productdetail.ProductDetailRemoteDataSource
import com.joy.shoppi.repository.productdetail.ProductDetailRepository
import com.joy.shoppi.ui.category.CategoryViewModel
import com.joy.shoppi.ui.categorydetail.CategoryDetailViewModel
import com.joy.shoppi.ui.home.HomeViewModel
import com.joy.shoppi.ui.productdetail.ProductDetailViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(HomeRepository(HomeAssetDataSource(AssetLoader(context)))) as T
            }
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> {
                CategoryViewModel(CategoryRepository(CategoryRemoteDataSource(ServiceLocator.provideApiClient()))) as T
            }
            modelClass.isAssignableFrom(CategoryDetailViewModel::class.java) -> {
                CategoryDetailViewModel(
                    CategoryDetailRepository(
                        CategoryDetailRemoteDataSource(
                            ServiceLocator.provideApiClient()
                        )
                    )
                ) as T
            }
            modelClass.isAssignableFrom(ProductDetailViewModel::class.java) -> {
                ProductDetailViewModel(
                    ProductDetailRepository(
                        ProductDetailRemoteDataSource(ServiceLocator.provideApiClient())
                    )
                ) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}