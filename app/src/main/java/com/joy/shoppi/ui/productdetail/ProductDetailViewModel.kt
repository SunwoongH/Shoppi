package com.joy.shoppi.ui.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joy.shoppi.model.Product
import com.joy.shoppi.repository.productdetail.ProductDetailRepository
import kotlinx.coroutines.launch

class ProductDetailViewModel(private val productDetailRepository: ProductDetailRepository) :
    ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    fun getProductDetail(productId: String) {
        viewModelScope.launch {
            _product.value = productDetailRepository.getProductDetail(productId)
        }
    }
}