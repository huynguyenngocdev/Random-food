package com.example.randomfood.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.randomfood.data.database.dao.FoodDao
import com.example.randomfood.data.database.model.FoodDbModel

/**
 * App's database.
 *
 * It contains a table for foods
 */
@Database(entities = [FoodDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

  companion object {
    const val DATABASE_NAME = "random-food-database"
  }

  abstract fun foodDao(): FoodDao
}