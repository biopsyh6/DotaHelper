package com.example.servicesapi.DataFetchers

import android.util.Log
import com.example.servicesapi.HeroService
import com.example.servicesapi.MyApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroDataFetcher(
    private val heroService: HeroService,
    private val apiService: MyApiService
) {
    fun fetchHeroData(heroId: Int) {
        val call = apiService.fetchHeroData("english", heroId)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    val json = response.body()
                    if (json != null) {
                        heroService.parseHeroData(json)
                    }
                }
                else {
                    Log.d("Error", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("Error", "${t.message}")
            }
        })
    }
}