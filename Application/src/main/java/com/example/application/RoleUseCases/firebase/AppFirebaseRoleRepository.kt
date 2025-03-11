package com.example.application.RoleUseCases.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.abstractions.role.RoleRepository
import com.example.domain.entities.Role
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AppFirebaseRoleRepository: RoleRepository {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance("https://dotahelperproject-default-rtdb.europe-west1.firebasedatabase.app/")
        .reference.child(mAuth.currentUser?.uid.toString())

    override fun saveRole(role: Role) {
        val id = database.child("roles").push().key
        if(id != null){
            role.firebaseId = id
            database.child("roles").child(id).setValue(role)
        }
    }

    override fun saveRoles(roles: List<Role>) {
        roles.forEach {saveRole(it)}
    }

    override fun getAllRoles(): LiveData<List<Role>> = AllRolesLiveData()

    override fun clearAllRoles() {
        database.child("roles").removeValue()
    }

    override fun getRoleById(id: Int): LiveData<Role> {
        TODO("Not yet implemented")
    }

    override fun getRolesByRoleIds(roleIds: List<String>): LiveData<List<Role>> {
        val rolesLiveData = MutableLiveData<List<Role>>()
        val roles = mutableListOf<Role>()
        val rolesRef = database.child("roles")

        roleIds.forEach { roleId ->
            rolesRef.child(roleId).addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val role = snapshot.getValue(Role::class.java)
                    role?.let {
                        roles.add(it)
                        rolesLiveData.value = roles
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
        return rolesLiveData
    }

    override suspend fun create(role: Role, onSuccess: () -> Unit) {
        val roleId = database.child("roles").push().key
        if(roleId != null){
            role.firebaseId = roleId
            database.child("roles").child(roleId).setValue(role).addOnSuccessListener {
                onSuccess()
            }
        }
    }


}