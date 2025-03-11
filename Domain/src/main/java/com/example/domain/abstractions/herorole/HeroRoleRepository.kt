package com.example.domain.abstractions.herorole

import androidx.lifecycle.LiveData
import com.example.domain.entities.HeroRole

interface HeroRoleRepository {
    fun saveHeroRole(heroRole: HeroRole)
    fun saveHeroRoles(heroRoles: List<HeroRole>)
    fun getAllHeroRoles(): LiveData<List<HeroRole>>
    fun clearAllHeroRoles()
    suspend fun create(heroRole: HeroRole, onSuccess: () -> Unit)
}