package com.example.servicesapi.DataFetchers

import android.util.Log
import com.example.servicesapi.MyApiService
import com.example.servicesapi.SkillService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SkillDataFetcher(
    private val skillService: SkillService,
    private val apiService: MyApiService
) {
    fun fetchSkillData(heroId: Int) {
        val call = apiService.fetchSkillData("english", heroId)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    val json = response.body()
                    if (json != null) {
                        skillService.parseSkillData(json)
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