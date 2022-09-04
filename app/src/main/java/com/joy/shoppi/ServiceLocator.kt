package com.joy.shoppi

import android.content.Context
import androidx.room.Room
import com.joy.shoppi.database.AppDatabase
import com.joy.shoppi.network.ApiClient

object ServiceLocator {

    private var apiClient: ApiClient? = null
    private var database: AppDatabase? = null

    fun provideApiClient(): ApiClient {
        return apiClient ?: kotlin.run {
            ApiClient.create().also {
                apiClient = it
            }
        }
    }

    fun provideDatabase(applicationContext: Context): AppDatabase {
        return database ?: kotlin.run {
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "shoppi-database"
            ).build().also {
                database = it
            }
        }
    }
}