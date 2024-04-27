package com.example.domain.abstractions.hero

import androidx.lifecycle.LiveData
import com.example.domain.entities.Hero

interface HeroRepository {
    fun saveHero(hero: Hero)
    fun getAllHeroes(): LiveData<List<Hero>>
    fun clearAllHeroes()
    fun getHeroById(id: Int) : LiveData<Hero>
    fun getHeroesByAttributeId(attributeId: Int) : LiveData<List<Hero>>
    fun getHeroesByRoleId(roleId: Int) : LiveData<List<Hero>>
    suspend fun create(hero: Hero, onSuccess: () -> Unit)
}