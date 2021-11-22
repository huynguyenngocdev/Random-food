package com.example.randomfood.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomfood.data.database.model.FoodDbModel
import com.example.randomfood.domain.model.FoodModel

/**
 * Dao for managing Food table in the database.
 */
@Dao
interface FoodDao {

  @Query("SELECT * FROM FoodDbModel")
  fun getAllFoods(): List<FoodDbModel>

  @Query("SELECT * FROM FoodDbModel WHERE id LIKE :id")
  fun findById(id: Long): LiveData<FoodDbModel>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(foodDbModel: FoodDbModel)

  @Query("DELETE FROM FoodDbModel")
  fun deleteAll()

  @Query("DELETE FROM FoodDbModel WHERE id LIKE :id")
  fun delete(id: Long)

  @Insert
  fun insertAll(vararg foodDbModel: FoodDbModel)
}