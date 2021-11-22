package com.example.randomfood.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomfood.data.repository.Repository
import com.example.randomfood.domain.model.FoodModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
        val allFoods: LiveData<List<FoodModel>> by lazy {
            repository.getAllFoods()
        }
//    val allFoods by lazy { repository.getAllFoods() }
//    fun getAllFoods(context: Context): LiveData<List<FoodModel>>? {
//        allFoods = Repository.getAllFoods(context)
//        return allFoods
//    }

//    val newFoodName by lazy { MutableLiveData<String>() }
//
//    val newFoodImage by lazy { MutableLiveData<Int>() }

//    fun saveFood(food: FoodModel) {
//        viewModelScope.launch(Dispatchers.Default) {
//            repository.insert(
//                food.copy(name = newFoodName.value ?:"", image = newFoodImage.value ?: 0)
//            )
//        }
//    }
}