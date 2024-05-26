package com.example.domain.abstractions.hero

import androidx.lifecycle.LiveData
import com.example.domain.entities.Hero
import com.google.firebase.database.DatabaseReference

interface HeroRepository {
    fun saveHero(hero: Hero)
    fun getAllHeroes(): LiveData<List<Hero>>
    fun clearAllHeroes()
    fun getHeroById(id: Int) : LiveData<Hero>
    fun getHeroesByAttributeFirebaseId(attributeId: LiveData<String?>) : LiveData<List<Hero>>
    fun getHeroByName(name: String) : LiveData<Hero?>
    fun getHeroReferenceByName(name: String): DatabaseReference
//    fun getHeroesByRoleId(roleId: Int) : LiveData<List<Hero>>
    suspend fun create(hero: Hero, onSuccess: () -> Unit)
}