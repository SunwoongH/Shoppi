package com.joy.shoppi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.joy.shoppi.R
import com.joy.shoppi.common.KEY_PRODUCT_ID
import com.joy.shoppi.databinding.FragmentHomeBinding
import com.joy.shoppi.ui.common.EventObserver
import com.joy.shoppi.ui.common.ViewModelFactory

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setView()
        setNavigation()
        setHomeBannerAdapter()
    }

    private fun setView() {
        viewModel.title.observe(
            viewLifecycleOwner
        ) { title ->
            binding.title = title
        }
    }

    private fun setNavigation() {
        viewModel.openProductEvent.observe(
            viewLifecycleOwner, EventObserver { productId ->
                openProductDetail(productId)
            }
        )
    }

    private fun setHomeBannerAdapter() {
        with(binding.homeBannerVp) {
            adapter = HomeBannerAdapter(viewModel).apply {
                viewModel.topBanners.observe(
                    viewLifecycleOwner
                ) { banners ->
                    submitList(banners)
                }
            }
            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageMargin - pageWidth
            offscreenPageLimit = 3
            setPageTransformer { page, position ->
                page.translationX = position * -offset
            }
            TabLayoutMediator(
                binding.homeBannerIndicatorTl, this
            ) { _, _ -> TODO("Not yet implemented") }
        }
    }

    private fun openProductDetail(productId: String) {
        findNavController().navigate(
            R.id.action_homeFragment_to_productDetailFragment, bundleOf(
                KEY_PRODUCT_ID to productId
            )
        )
    }
}