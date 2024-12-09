package com.example.plateplanner.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.plateplanner.data.local.entities.ShoppingItem
import kotlinx.coroutines.flow.Flow
@Dao
interface ShoppingItemDao {
    @Upsert
    suspend fun insertShoppingItems(shoppingItems: List<ShoppingItem>)

    @Update
   suspend fun updateShoppingItem(shoppingItem: ShoppingItem)

    @Query("Delete From ShoppingTable")
    suspend fun  deleteAllShoppingListData()

    @Query("""Select * From ShoppingTable""")
    fun getAllShoppingList() : Flow<List<ShoppingItem>>


}