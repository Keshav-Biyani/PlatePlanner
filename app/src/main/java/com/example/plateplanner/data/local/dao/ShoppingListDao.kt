package com.example.plateplanner.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plateplanner.data.local.entities.ShoppingListEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertShoppingList(shoppingListEntity: ShoppingListEntity)

    @Query("Delete From Shopping_Table")
    suspend fun  DeleteAllShoppingListData()

    @Query("""Select * From Shopping_Table""")
    fun GetAllShoppingList() : Flow<ShoppingListEntity>
}