package com.example.domain.abstractions.hero

import androidx.lifecycle.LiveData
import com.example.domain.entities.Hero

interface HeroRepository {
    fun saveHero(hero: com.example.domain.entities.Hero)
    fun getAllHeroes(): LiveData<List<com.example.domain.entities.Hero>>
    fun clearAllHeroes()
    fun getHeroById(id: Int) : LiveData<com.example.domain.entities.Hero>
    fun getHeroesByAttributeId(attributeId: Int) : LiveData<List<com.example.domain.entities.Hero>>
    fun getHeroesByRoleId(roleId: Int) : LiveData<List<com.example.domain.entities.Hero>>
}