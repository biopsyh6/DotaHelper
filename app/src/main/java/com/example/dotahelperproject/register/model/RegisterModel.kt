package com.example.dotahelperproject.register.model

import com.example.domain.entities.User
import com.example.dotahelperproject.user.model.firebase.AppFirebaseUserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterModel: IRegisterModel {
    val userRepository = AppFirebaseUserRepository()
    override fun register(
        login: String,
        email: String,
        pass: String,
        steamid: String,
        onComplete: (task: Task<AuthResult>) -> Unit
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
//                    val userInfo: HashMap<String, String> = HashMap()
//                    userInfo.put("email", email)
//                    userInfo.put("login", login)
//                    userInfo.put("steamid", steamid)
                    val user = User(
                        login = login,
                        email = email,
                        steamid = steamid
                    )
                    userRepository.saveUser(user)
                }
                onComplete(task)
            }
    }
}