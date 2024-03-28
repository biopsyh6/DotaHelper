package com.example.dotahelperproject.register.model

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterModel: IRegisterModel {
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
                    val userInfo: HashMap<String, String> = HashMap()
                    userInfo.put("email", email)
                    userInfo.put("login", login)
                    userInfo.put("steamid", steamid)
                    val currentUser = FirebaseAuth.getInstance().currentUser
                    if (currentUser != null) {
                        FirebaseDatabase.getInstance().reference.child("Users")
                            .child(currentUser.uid).setValue(userInfo)
                    }
                }
                onComplete(task)
            }
    }
}