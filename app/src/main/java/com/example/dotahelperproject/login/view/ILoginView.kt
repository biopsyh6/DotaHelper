package com.example.dotahelperproject.login.view

import com.google.firebase.auth.FirebaseUser

interface ILoginView {
    fun onClear()
    fun onShowProgress()
    fun onHideProgress()
    fun onUpdateLoginResultUserInfo(login: String, steamid: String)

    fun onShowEmptyFieldsError()
    fun onShowFirebaseAuthError()
    fun onStartMainPageActivity(user: FirebaseUser)
}