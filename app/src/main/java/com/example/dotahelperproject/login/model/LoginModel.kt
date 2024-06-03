package com.example.dotahelperproject.login.model

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginModel: ILoginModel {
    private val mAuth = FirebaseAuth.getInstance()
    override fun login(
        login: String,
        password: String,
        onComplete: (task: Task<AuthResult>) -> Unit
    ) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(login, password)
            .addOnCompleteListener { task ->
                onComplete(task)
            }
    }

    override fun logout() {
        mAuth.signOut()
    }
}