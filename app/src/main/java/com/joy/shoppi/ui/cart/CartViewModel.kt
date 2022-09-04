package com.joy.shoppi.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joy.shoppi.model.CartSectionItem
import com.joy.shoppi.repository.cart.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(private val cartRepository: CartRepository) : ViewModel() {

    private val _cartSectionItems = MutableLiveData<List<CartSectionItem>>()
    val cartSectionItem: LiveData<List<CartSectionItem>> = _cartSectionItems

    init {
        getCartSectionItems()
    }

    private fun getCartSectionItems() {
        viewModelScope.launch {
            _cartSectionItems.value = cartRepository.getCartSectionItems()
        }
    }
}