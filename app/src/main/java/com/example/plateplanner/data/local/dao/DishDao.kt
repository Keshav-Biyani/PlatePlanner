package com.example.plateplanner.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.plateplanner.data.local.entities.Dish
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {

    @Upsert
    suspend fun InsertDish(dish : Dish)

    @Query("""Delete From DishTable Where id=:id""")
    suspend fun deleteDish(id : Int)


    @Query("""Select * From  DishTable""")
    fun getListData() : Flow<List<Dish>>
}