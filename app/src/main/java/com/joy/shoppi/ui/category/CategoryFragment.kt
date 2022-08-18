package com.joy.shoppi.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.joy.shoppi.R
import com.joy.shoppi.common.KEY_CATEGORY_ID
import com.joy.shoppi.common.KEY_CATEGORY_LABEL
import com.joy.shoppi.databinding.FragmentCategoryBinding
import com.joy.shoppi.ui.common.EventObserver
import com.joy.shoppi.ui.common.ViewModelFactory

class CategoryFragment : Fragment() {

    private val viewModel: CategoryViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter(viewModel)
        binding.categoryRv.adapter = categoryAdapter
        viewModel.items.observe(
            viewLifecycleOwner
        ) {
            categoryAdapter.submitList(it)
        }

        viewModel.openCategoryEvent.observe(viewLifecycleOwner, EventObserver {
            openCategoryDetail(it.categoryId, it.label)
        })
    }

    private fun openCategoryDetail(categoryId: String, categoryLabel: String) {
        findNavController().navigate(
            R.id.action_categoryFragment_to_categoryDetailFragment, bundleOf(
                KEY_CATEGORY_ID to categoryId,
                KEY_CATEGORY_LABEL to categoryLabel
            )
        )
    }
}