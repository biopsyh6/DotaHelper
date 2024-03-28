package com.example.dotahelperproject.register.model

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface IRegisterModel {
    fun register(login: String, email: String, pass: String, steamid: String,
                 onComplete: (task: Task<AuthResult>) -> Unit)
}