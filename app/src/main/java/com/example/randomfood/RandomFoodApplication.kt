package com.example.randomfood

import android.app.Application
import com.example.randomfood.dependencyinjection.DependencyInjector

class RandomFoodApplication: Application() {
    lateinit var dependencyInjector: DependencyInjector

    override fun onCreate() {
        super.onCreate()
        initDependencyInjector()
    }

    private fun initDependencyInjector() {
        dependencyInjector = DependencyInjector(this)
    }
}