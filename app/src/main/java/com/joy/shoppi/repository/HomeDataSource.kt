package com.joy.shoppi.repository

import com.joy.shoppi.model.HomeData

interface HomeDataSource {

    fun getHomeData(): HomeData?
}