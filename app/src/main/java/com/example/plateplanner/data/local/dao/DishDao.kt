package com.example.plateplanner.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.plateplanner.data.local.entities.Dish
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {

    @Upsert
    suspend fun InsertDish(dish : Dish)


    @Query("""Select * From  DishTable""")
    fun GetListData() : Flow<List<Dish>>
}