package com.example.randomfood.data.database.dbmapper

import com.example.randomfood.data.database.model.FoodDbModel
import com.example.randomfood.domain.model.FoodModel
import com.example.randomfood.domain.model.NEW_FOOD_ID

class DbMapperImpl : DbMapper {

    override fun mapFoods(foodDbModels: List<FoodDbModel>): List<FoodModel> = foodDbModels.map {
        mapFood(it)
    }

    override fun mapFood(foodDbModel: FoodDbModel): FoodModel {
        return with(foodDbModel) { FoodModel(id, name, image) }
    }

    override fun mapDbFood(food: FoodModel): FoodDbModel =
        with(food) {
            if (id == NEW_FOOD_ID) {
                FoodDbModel(
                    name = name, image = image
                )
            } else {
                FoodDbModel(id, name, image)
            }
        }

}