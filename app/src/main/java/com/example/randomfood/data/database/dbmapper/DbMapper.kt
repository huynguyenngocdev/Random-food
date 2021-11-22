package com.example.randomfood.data.database.dbmapper

import com.example.randomfood.data.database.model.FoodDbModel
import com.example.randomfood.domain.model.FoodModel

interface DbMapper {
    //FoodDbModel ->FoodModel
    fun mapFoods(foodDbModels: List<FoodDbModel>): List<FoodModel>

    fun mapFood(foodDbModel: FoodDbModel): FoodModel
    //FoodModel -> FoodDbModel
    fun mapDbFood(food: FoodModel): FoodDbModel
}