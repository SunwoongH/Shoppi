package com.joy.shoppi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joy.shoppi.model.CartSectionItem

@Database(entities = [CartSectionItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartSectionItemDao(): CartSectionItemDao
}