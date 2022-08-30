package com.joy.shoppi.ui.categorydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.joy.shoppi.common.KEY_CATEGORY_LABEL
import com.joy.shoppi.databinding.FragmentCategoryDetailBinding
import com.joy.shoppi.ui.common.ViewModelFactory

class CategoryDetailFragment : Fragment() {

    private val viewModel: CategoryDetailViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentCategoryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        setView()
        setCategoryDetailAdapter()
        setNavigation()
    }

    private fun setView() {
        binding.categoryDetailTb.title = requireArguments().getString(KEY_CATEGORY_LABEL)
    }

    private fun setCategoryDetailAdapter() {
        val topSellingSectionAdapter = CategoryTopSellingSectionAdapter()
        val titleAdapter = CategorySectionTitleAdapter()
        val promotionAdapter = CategoryPromotionAdapter()
        binding.categoryDetailRv.adapter =
            ConcatAdapter(topSellingSectionAdapter, titleAdapter, promotionAdapter)

        viewModel.topSelling.observe(viewLifecycleOwner) { topSelling ->
            topSellingSectionAdapter.submitList(listOf(topSelling))
        }

        viewModel.promotions.observe(viewLifecycleOwner) { promotions ->
            titleAdapter.submitList(listOf(promotions.title))
            promotionAdapter.submitList(promotions.items)
        }
    }

    private fun setNavigation() {
        binding.categoryDetailTb.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}