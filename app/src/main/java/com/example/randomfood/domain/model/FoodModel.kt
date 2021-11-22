package com.example.randomfood.domain.model

const val NEW_FOOD_ID = -1L

data class FoodModel(
    val id: Long = NEW_FOOD_ID,
    val name: String = "",
    val image: Int = 0
)