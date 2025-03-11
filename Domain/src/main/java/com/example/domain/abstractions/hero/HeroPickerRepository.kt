package com.example.domain.abstractions.hero

import androidx.lifecycle.LiveData
import com.example.domain.entities.HeroPicker

interface HeroPickerRepository {
    fun saveHero(hero: HeroPicker)
    fun getAllHeroes(): LiveData<List<HeroPicker>>
    fun clearAllHeroes()
    fun getHeroById(id: Int) : LiveData<HeroPicker>
    fun getHeroes() : LiveData<List<HeroPicker>>
    fun getHeroByName(name: String): LiveData<HeroPicker?>
}