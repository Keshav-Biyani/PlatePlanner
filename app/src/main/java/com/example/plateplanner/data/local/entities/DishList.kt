package com.example.plateplanner.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("DishTable")
data class Dish(

    val dishList: String,@PrimaryKey
        (autoGenerate = true)val id : Int =0

)
