package com.example.servicesapi

import com.example.application.HeroPickerUseCases.firebase.AppFirebaseHeroPickerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeroPickerService(
    private val heroPickerRepository: AppFirebaseHeroPickerRepository
) {
    fun parseHeroPickerData(data: String){
        CoroutineScope(Dispatchers.IO).launch {

        }
    }
}