package com.example.randomfood.data.repository

import androidx.lifecycle.LiveData
import com.example.randomfood.domain.model.FoodModel

interface Repository {

  fun getAllFoods(): LiveData<List<FoodModel>>

  fun getFood(id: Long): LiveData<FoodModel>

  fun insert(food: FoodModel)

  fun deleteAll()

  fun deleteFood(id: Long)


}