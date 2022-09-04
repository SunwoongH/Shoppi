package com.joy.shoppi.ui.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.joy.shoppi.common.KEY_PRODUCT_ID
import com.joy.shoppi.databinding.FragmentProductDetailBinding
import com.joy.shoppi.ui.common.EventObserver
import com.joy.shoppi.ui.common.ViewModelFactory

class ProductDetailFragment : Fragment() {

    private val viewModel: ProductDetailViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        setView()
        setAddCart()
        setNavigation()
    }

    private fun getProductId(): String {
        return requireArguments().getString(KEY_PRODUCT_ID)!!
    }

    private fun setView() {
        viewModel.getProductDetail(getProductId())
        val adapter = ProductDescriptionAdapter()
        binding.productDetailRv.adapter = adapter
        viewModel.product.observe(
            viewLifecycleOwner
        ) { product ->
            binding.product = product
            adapter.submitList(product.descriptions)
        }
    }

    private fun setAddCart() {
        viewModel.addCartEvent.observe(
            viewLifecycleOwner, EventObserver {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("장바구니에 상품이 담겼습니다.")
                    .setPositiveButton(
                        "확인"
                    ) { _, _ -> }
                    .show()
            }
        )
    }

    private fun setNavigation() {
        binding.productDetailTb.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}