package com.example.dotahelperproject.register.view

interface IRegisterView {
    fun onClear()
    fun onShowProgress()
    fun onHideProgress()

    fun onShowEmptyFieldsError()
    fun onShowFirebaseRegistrError()
    fun onStartLoginActivity()
}