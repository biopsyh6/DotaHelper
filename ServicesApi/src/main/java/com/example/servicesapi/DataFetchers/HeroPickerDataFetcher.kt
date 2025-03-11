package com.example.servicesapi.DataFetchers

import android.util.Log
import com.example.application.HeroPickerUseCases.firebase.AppFirebaseHeroPickerRepository
import com.example.domain.entities.HeroPickerResponse
import com.example.servicesapi.MyApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroPickerDataFetcher(
    private val apiService: MyApiService,
    private val heroPickerRepository: AppFirebaseHeroPickerRepository
) {
    fun fetchPickerHeroData(){
        val call = apiService.fetchHeroPicker()
        call.enqueue(object : Callback<HeroPickerResponse> {
            override fun onResponse(
                call: Call<HeroPickerResponse>,
                response: Response<HeroPickerResponse>
            ) {
                if (response.isSuccessful){
                    val heroesResponse = response.body()
                    if (heroesResponse != null) {
                        val heroes = heroesResponse.data
                        for (hero in heroes) {
                            heroPickerRepository.saveHero(hero)
                            Log.d("SAVE HEROPICKER", hero.name)
                        }
                    }
                }
                else {
                    Log.d("Error", "${response.code()}")
                }

            }

            override fun onFailure(call: Call<HeroPickerResponse>, t: Throwable) {
                Log.d("Error", "${t.message}")
            }

        })
    }
}