package com.joy.shoppi.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.joy.shoppi.databinding.FragmentCartBinding
import com.joy.shoppi.ui.common.ViewModelFactory

class CartFragment : Fragment() {

    private val viewModel: CartViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setCartAdapter()
    }

    private fun setCartAdapter() {
        val cartAdapter = CartAdapter()
        binding.cartRv.adapter = cartAdapter
        viewModel.cartSectionItem.observe(
            viewLifecycleOwner
        ) { cartSectionItems ->
            cartAdapter.submitSectionAndSectionItems(cartSectionItems)
        }
    }
}