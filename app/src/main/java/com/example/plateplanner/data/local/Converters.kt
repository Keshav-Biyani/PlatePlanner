package com.example.plateplanner.data.local


import androidx.room.TypeConverter
import com.example.plateplanner.data.local.entities.RecipeData
import com.example.plateplanner.data.remote.Ingredient
import com.google.common.reflect.TypeToken
import com.google.gson.Gson



class Converters {

    @TypeConverter
    fun fromIngredient(ingredient: Ingredient): String{
        val gson = Gson()
        return gson.toJson(ingredient)
    }
    @TypeConverter
    fun toIngredient(data : String): Ingredient{
        val gson = Gson()
        val datatype = object : TypeToken<Ingredient>() {}.type
        return gson.fromJson(data, datatype)
    }

    @TypeConverter
    fun fromRecipeData(reipeData : RecipeData) : String{
        val gson = Gson()
        return gson.toJson(reipeData)
    }

    @TypeConverter
    fun toRecipeData(data : String) : RecipeData {
        val gson = Gson()
        val dataType = object : TypeToken <RecipeData>(){}.type
        return gson.fromJson(data,dataType)
    }


}