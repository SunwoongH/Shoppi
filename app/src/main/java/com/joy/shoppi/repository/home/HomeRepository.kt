package com.joy.shoppi.repository.home

import com.joy.shoppi.model.HomeData

class HomeRepository(private val assetDataSource: HomeAssetDataSource) {

    fun getHomeData(): HomeData? {
        return assetDataSource.getHomeData()
    }
}