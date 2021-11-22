package com.example.randomfood.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.randomfood.data.database.dao.FoodDao
import com.example.randomfood.data.database.dbmapper.DbMapper
import com.example.randomfood.data.database.model.FoodDbModel
import com.example.randomfood.domain.model.FoodModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RepositoryImpl(private val foodDao: FoodDao, private val mapper: DbMapper) : Repository {

    private val allFoodsLiveData: MutableLiveData<List<FoodModel>> by lazy {
        MutableLiveData<List<FoodModel>>()
    }

    init {
        initDatabase(this::updateFoodLiveData)
    }

    /**
     * Populates database with foods if it is empty.
     */
    private fun initDatabase(foodInitAction: () -> Unit) {
        GlobalScope.launch {
            val foods = FoodDbModel.DEFAULT_FOODS.toTypedArray()
            val dbFoods = foodDao.getAllFoods()
            if (dbFoods.isNullOrEmpty()) {
                foodDao.insertAll(*foods)
            }

            foodInitAction.invoke()
        }
    }

    override fun getAllFoods(): LiveData<List<FoodModel>> = allFoodsLiveData

    override fun getFood(id: Long): LiveData<FoodModel> =
        Transformations.map(foodDao.findById(id)) {
            mapper.mapFood(it)
        }

    private fun getAllFoodsFromDatabase(): List<FoodModel> {
        val foods: List<FoodDbModel> = foodDao.getAllFoods()
        return mapper.mapFoods(foods)
    }

    override fun insert(food: FoodModel) {
        foodDao.insert(mapper.mapDbFood(food))
        updateFoodLiveData()
    }

    override fun deleteAll() {
        foodDao.deleteAll()

        updateFoodLiveData()
    }

    override fun deleteFood(id: Long) {
        foodDao.delete(id)

        updateFoodLiveData()
    }

    private fun updateFoodLiveData() {
        allFoodsLiveData.postValue(getAllFoodsFromDatabase())
    }
}