package com.example.dotahelperproject.login.model

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface ILoginModel {
    fun login(login: String, password: String, onComplete: (task: Task<AuthResult>) -> Unit)
}
