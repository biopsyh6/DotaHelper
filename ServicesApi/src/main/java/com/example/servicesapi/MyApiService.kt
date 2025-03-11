package com.example.servicesapi
import com.example.domain.entities.HeroData
import com.example.domain.entities.HeroPickerResponse
import com.example.domain.entities.MatchData
import com.example.domain.entities.UserData
import com.example.domain.entities.UserHeroData
import com.example.domain.entities.WinLossData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApiService {
    @GET("datafeed/herodata")
    fun fetchHeroData(@Query("language") language: String, @Query("hero_id") heroId: Int): Call<String>

    @GET("datafeed/herodata")
    fun fetchSkillData(@Query("language") language: String, @Query("hero_id") heroId: Int): Call<String>

    @GET("players/{account_id}")
    fun fetchUserData(@Path("account_id") accountId: String): Call<UserData>

    @GET("players/{account_id}/matches")
    fun fetchUserMatches(@Path("account_id") accountId: String): Call<List<MatchData>>

    @GET("heroes")
    fun fetchHeroes(): Call<List<HeroData>>

    @GET("players/{account_id}/wl")
    fun fetchUserWinLoss(@Path("account_id") accountId: String,
                         @Query("limit") limit: Int? = null,
                         @Query("offset") offset: Int? = null,
                         @Query("win") win: Int? = null,
                         @Query("patch") patch: Int? = null,
                         @Query("game_mode") gameMode: Int? = null,
                         @Query("lobby_type") lobbyType: Int? = null,
                         @Query("region") region: Int? = null,
                         @Query("date") date: Int? = null,
                         @Query("lane_role") laneRole: Int? = null,
                         @Query("hero_id") heroId: Int? = null,
                         @Query("is_radiant") isRadiant: Int? = null,
                         @Query("included_account_id") includedAccountId: Int? = null,
                         @Query("excluded_account_id") excludedAccountId: Int? = null,
                         @Query("with_hero_id") withHeroId: Int? = null,
                         @Query("against_hero_id") againstHeroId: Int? = null,
                         @Query("significant") significant: Int? = null,
                         @Query("having") having: Int? = null,
                         @Query("sort") sort: String? = null): Call<WinLossData>

    @GET("players/{account_id}/heroes")
    fun fetchUserHeroes(@Path("account_id") accountId: String): Call<List<UserHeroData>>

    @GET("api/heroes")
    fun fetchHeroPicker(): Call<HeroPickerResponse>
}

//    fun fetchUserMatches(@Path("account_id") accountId: String, @Query("limit") limit: Int): Call<List<MatchData>>