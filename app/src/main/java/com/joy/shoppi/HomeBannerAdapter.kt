package com.joy.shoppi

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat
import kotlin.math.roundToInt

class HomeBannerAdapter :
    ListAdapter<Banner, HomeBannerAdapter.HomeBannerViewHolder>(BannerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_banner, parent, false)
        return HomeBannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomeBannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val bannerImageView = view.findViewById<ImageView>(R.id.item_home_banner_iv)
        private val bannerBadgeTextView =
            view.findViewById<TextView>(R.id.item_home_banner_badge_tv)
        private val bannerTitleTextView =
            view.findViewById<TextView>(R.id.item_home_banner_title_tv)
        private val bannerThumbnailImageView =
            view.findViewById<ImageView>(R.id.item_home_banner_thumbnail_iv)
        private val bannerBrandLabelTextView =
            view.findViewById<TextView>(R.id.item_home_banner_brand_label_tv)
        private val bannerProductLabelTextView =
            view.findViewById<TextView>(R.id.item_home_banner_product_label_tv)
        private val bannerDiscountLabelTextView =
            view.findViewById<TextView>(R.id.item_home_banner_product_discount_label_tv)
        private val bannerDiscountPriceLabelTextView =
            view.findViewById<TextView>(R.id.item_home_banner_product_discount_price_label_tv)
        private val bannerPriceTextView =
            view.findViewById<TextView>(R.id.item_home_banner_product_price_label_tv)

        fun bind(banner: Banner) {
            loadImage(banner.backgroundImageUrl, bannerImageView)
            bannerBadgeTextView.text = banner.badge.label
            bannerBadgeTextView.background =
                ColorDrawable(Color.parseColor(banner.badge.backgroundColor))
            bannerTitleTextView.text = banner.label
            loadImage(banner.productDetail.thumbnailImageUrl, bannerThumbnailImageView)
            bannerBrandLabelTextView.text = banner.productDetail.brandName
            bannerProductLabelTextView.text = banner.productDetail.label
            bannerDiscountLabelTextView.text = "${banner.productDetail.discountRate}%"
            applyPriceFormat(
                bannerDiscountPriceLabelTextView,
                calculateDiscountAmount(
                    banner.productDetail.discountRate,
                    banner.productDetail.price
                )
            )
            applyPriceFormat(bannerPriceTextView, banner.productDetail.price)
        }

        private fun calculateDiscountAmount(discountRate: Int, price: Int): Int {
            return (((100 - discountRate) / 100.0) * price).roundToInt()
        }

        private fun applyPriceFormat(view: TextView, price: Int) {
            val decimalFormat = DecimalFormat("#,###")
            view.text = decimalFormat.format(price) + "원"
        }

        private fun loadImage(urlString: String, imageView: ImageView) {
            GlideApp.with(itemView)
                .load(urlString)
                .into(imageView)
        }
    }
}

class BannerDiffCallback : DiffUtil.ItemCallback<Banner>() {

    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem.productDetail.productId == newItem.productDetail.productId
    }

    override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem == newItem
    }
}