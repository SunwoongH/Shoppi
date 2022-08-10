package com.joy.shoppi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val assetsLoader = AssetLoader()
        val homeJsonString = assetsLoader.getJsonString(requireContext(), "home.json")
        val toolbarTitle = view.findViewById<TextView>(R.id.home_toolbar_tv)
        val toolbarIcon = view.findViewById<ImageView>(R.id.home_toolbar_iv)
        val viewPager = view.findViewById<ViewPager2>(R.id.home_banner_vp)
        val viewPagerIndicator = view.findViewById<TabLayout>(R.id.home_banner_indicator_tl)

        if (!homeJsonString.isNullOrEmpty()) {
            val gson = Gson()
            val homeData = gson.fromJson(homeJsonString, HomeData::class.java)
            toolbarTitle.text = homeData.title.text
            GlideApp.with(this)
                .load(homeData.title.iconUrl)
                .into(toolbarIcon)

            viewPager.adapter = HomeBannerAdapter().apply {
                submitList(homeData.topBanners)
            }
            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin

            viewPager.offscreenPageLimit = 3
            viewPager.setPageTransformer { page, position ->
                page.translationX = position * -offset
            }
            TabLayoutMediator(
                viewPagerIndicator, viewPager
            ) { tab, position ->

            }.attach()
        }
    }
}