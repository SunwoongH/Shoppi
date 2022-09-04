package com.joy.shoppi.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joy.shoppi.model.CartSectionItem

@Dao
interface CartSectionItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartSectionItem: CartSectionItem)

    @Query("SELECT * FROM cart_item")
    suspend fun get(): List<CartSectionItem>
}