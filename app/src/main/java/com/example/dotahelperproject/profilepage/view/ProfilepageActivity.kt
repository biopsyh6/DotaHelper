package com.example.dotahelperproject.profilepage.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.domain.abstractions.user.UserRepository
import com.example.domain.entities.HeroData
import com.example.domain.entities.MatchData
import com.example.domain.entities.UserData
import com.example.domain.entities.UserHeroData
import com.example.domain.entities.WinLossData
import com.example.dotahelperproject.R
import com.example.dotahelperproject.adapters.MatchesAdapter
import com.example.dotahelperproject.databinding.ActivityProfileBinding
import com.example.dotahelperproject.user.firebase.AppFirebaseUserRepository
import com.example.servicesapi.MyApiService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfilepageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var userRepository: UserRepository
    private lateinit var apiService: MyApiService
    private var heroMap: Map<Int, HeroData> = mapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.opendota.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(MyApiService::class.java)

        userRepository = AppFirebaseUserRepository()
        userRepository.getEmail().observe(this, Observer { email ->
            binding.emailTextView.text = email
        })
        var steam: String = ""
        userRepository.getSteamId().observe(this, Observer { steamid ->
            binding.steamTextView.text = steamid
            steam = steamid!!

            apiService.fetchUserData(steamid!!).enqueue(object : Callback<UserData> {
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    val userData = response.body()

                    Picasso.get()
                        .load(userData?.profile?.avatarfull)
                        .into(binding.avatarImageView)

                    binding.nicknameTextView.text = userData?.profile?.personaname
                    binding.locationTextView.text = userData?.profile?.loccountrycode
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
            apiService.fetchHeroes().enqueue(object : Callback<List<HeroData>>{
                override fun onResponse(
                    call: Call<List<HeroData>>,
                    response: Response<List<HeroData>>
                ) {
                    val heroes = response.body() ?: emptyList()
                    heroMap = heroes.associateBy { it.id }
//                    fetchAndDisplayMatches(steamid)
                }

                override fun onFailure(call: Call<List<HeroData>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
            apiService.fetchUserMatches(steamid).enqueue(object : Callback<List<MatchData>>{
                override fun onResponse(
                    call: Call<List<MatchData>>,
                    response: Response<List<MatchData>>
                ) {
                    val matches = response.body() ?: emptyList()
                    val adapter = MatchesAdapter(this@ProfilepageActivity, matches, heroMap)
//                    binding.matchesListView.adapter = adapter
                }

                override fun onFailure(call: Call<List<MatchData>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

            apiService.fetchUserWinLoss(steamid, 10, 0, 1, null, null,
                null, null, null, null, null, null, null,
                null, null, null, 1, null, null).enqueue(object : Callback<WinLossData>{
                override fun onResponse(call: Call<WinLossData>, response: Response<WinLossData>) {
                    val winLossData = response.body()
                    binding.winRateTextView.text = "${winLossData?.win.toString()}/${winLossData?.lose.toString()}"
                }

                override fun onFailure(call: Call<WinLossData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

            apiService.fetchUserHeroes(steamid).enqueue(object : Callback<List<UserHeroData>>{
                override fun onResponse(
                    call: Call<List<UserHeroData>>,
                    response: Response<List<UserHeroData>>
                ) {
                    val userHeroDataList = response.body()
                    Log.d("USERHEROES", userHeroDataList.toString())
                }

                override fun onFailure(call: Call<List<UserHeroData>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })


        })


    }

}

