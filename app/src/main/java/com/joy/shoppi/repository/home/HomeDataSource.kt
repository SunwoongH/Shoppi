package com.joy.shoppi.repository.home

import com.joy.shoppi.model.HomeData

interface HomeDataSource {

    fun getHomeData(): HomeData?
}