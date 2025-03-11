package com.example.application.HeroRoleUseCases.firebase

import androidx.lifecycle.LiveData
import com.example.domain.abstractions.herorole.HeroRoleRepository
import com.example.domain.entities.HeroRole
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AppFirebaseHeroRoleRepository: HeroRoleRepository {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance("https://dotahelperproject-default-rtdb.europe-west1.firebasedatabase.app/")
        .reference.child(mAuth.currentUser?.uid.toString())

    override fun saveHeroRole(heroRole: HeroRole) {
        TODO("Not yet implemented")
    }

    override fun saveHeroRoles(heroRoles: List<HeroRole>) {
        TODO("Not yet implemented")
    }

    override fun getAllHeroRoles(): LiveData<List<HeroRole>> {
        TODO("Not yet implemented")
    }

    override fun clearAllHeroRoles() {
        TODO("Not yet implemented")
    }

    override suspend fun create(heroRole: HeroRole, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

}