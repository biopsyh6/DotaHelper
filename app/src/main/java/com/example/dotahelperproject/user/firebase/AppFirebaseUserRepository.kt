package com.example.dotahelperproject.user.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.abstractions.user.UserRepository
import com.example.domain.entities.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AppFirebaseUserRepository: UserRepository {
    private val mAuth = FirebaseAuth.getInstance()
//    private val database = FirebaseDatabase.getInstance("https://dotahelperproject-default-rtdb.europe-west1.firebasedatabase.app/")
//        .reference.child(mAuth.currentUser?.uid.toString())
    private val database = FirebaseDatabase.getInstance("https://dotahelperproject-default-rtdb.europe-west1.firebasedatabase.app/")
    .reference.child("users")

    override fun saveUser(user: User) {
//        val currentUser = mAuth.currentUser
//        if (currentUser != null) {
//            val userUid = currentUser.uid
//            user.firebaseId = userUid
//            database.child(userUid).setValue(user)
//        }
//        val currentUser = mAuth.currentUser
//        val userUid = currentUser?.uid


//        val id = database.child("users").push().key
//        if(id != null){
//            user.firebaseId = id
//            database.child("users").child(id).setValue(user)
//        }

//        val usersRef = database.child("users")
//        usersRef.orderByChild("email").equalTo(user.email).addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    // Пользователь уже существует, обновляем данные
//                    for (snapshot in dataSnapshot.children) {
//                        snapshot.ref.setValue(user)
//                    }
//                } else {
//                    // Пользователь не существует, создаём нового
//                    val id = usersRef.push().key
//                    if (id != null) {
//                        user.firebaseId = id
//                        usersRef.child(id).setValue(user)
//                    }
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Обработка ошибки
//            }
//        })

        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            val userRef = database.child(userId)

            userRef.setValue(user)
                .addOnSuccessListener {
                    // Данные успешно сохранены
                    Log.d("AppFirebaseUserRepository", "Данные пользователя успешно сохранены.")
                }
                .addOnFailureListener { error ->
                    // Обработка ошибки
                    Log.e("AppFirebaseUserRepository", "Ошибка при сохранении данных: ${error.message}")
                }
        } else {
            // Пользователь не авторизован
            Log.e("AppFirebaseUserRepository", "Пользователь не авторизован.")
        }

    }

    override fun saveUsers(users: List<User>) {
        users.forEach {saveUser(it)}
    }

    override fun getAllUsers(): LiveData<List<User>> = AllUsersLiveData()

    override fun clearAllUsers() {
        database.child("users").removeValue()
    }

    override fun getUserById(id: Int): MutableLiveData<User?> {
        val liveData = MutableLiveData<User?>()
        database.child("users").child(id.toString()).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                liveData.value = user
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return liveData
    }

    override suspend fun create(user: User, onSuccess: () -> Unit) {
        val userId = database.child("users").push().key
        if(userId != null){
            user.firebaseId = userId
            database.child("users").child(userId).setValue(user).addOnSuccessListener {
                onSuccess()
            }
        }
    }

    override fun getEmail(): MutableLiveData<String?> {
        val liveData = MutableLiveData<String?>()
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            database.child(userId).child("email").addListenerForSingleValueEvent(object :
            ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val email = snapshot.getValue(String::class.java)
                    liveData.value = email
                }

                override fun onCancelled(error: DatabaseError) {
                    liveData.value = null
                }

            })
        } else {
            liveData.value = null
        }
        return liveData
    }

    override fun getSteamId(): MutableLiveData<String?> {
        val liveData = MutableLiveData<String?>()
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            database.child(userId).child("steamid").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val steamid = snapshot.getValue(String::class.java)
                    liveData.value = steamid
                }

                override fun onCancelled(error: DatabaseError) {
                    liveData.value = null
                }

            })
        } else {
            liveData.value = null
        }
        return liveData
    }

}