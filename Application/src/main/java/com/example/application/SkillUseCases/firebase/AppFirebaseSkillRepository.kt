package com.example.application.SkillUseCases.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.abstractions.skill.SkillRepository
import com.example.domain.entities.Skill
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AppFirebaseSkillRepository: SkillRepository {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance("https://dotahelperproject-default-rtdb.europe-west1.firebasedatabase.app/")
        .reference.child(mAuth.currentUser?.uid.toString())

    override fun saveSkill(skill: Skill) {
        val id = database.child("skills").push().key
        if(id != null) {
            skill.firebaseId = id
            val skillData = skill.copy()
            database.child("skills").child(id).setValue(skillData)
        }
    }

    override fun getAllSkills(): LiveData<List<Skill>> = AllSkillsLiveData()

    override fun clearAllSkills() {
        database.child("skills").removeValue()
    }

    override fun getSkillById(id: Int): LiveData<Skill> {
        TODO("Not yet implemented")
    }

    override fun getSkillsByHeroId(heroId: String): LiveData<List<Skill>> {
        val skillsLiveData = MutableLiveData<List<Skill>>()
        database.child("skills").orderByChild("heroId").equalTo(heroId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val skills = snapshot.children.mapNotNull { it.getValue(Skill::class.java) }
                    skillsLiveData.value = skills
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        return skillsLiveData
    }

    override fun getSkillsByHeroName(heroName: String): LiveData<List<Skill>> {
        val skillsLiveData = MutableLiveData<List<Skill>>()
        database.child("skills").orderByChild("heroName").equalTo(heroName)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val skills = snapshot.children.mapNotNull { it.getValue(Skill::class.java) }
                    skillsLiveData.value = skills
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        return skillsLiveData
    }

    override suspend fun create(skill: Skill, onSuccess: () -> Unit) {
        val skillId = database.child("skills").push().key
        if(skillId != null) {
            skill.firebaseId = skillId
            database.child("skills").child(skillId).setValue(skill).addOnSuccessListener {
                onSuccess()
            }
        }
    }

}