package com.example.dotahelperproject.register.presenter

interface IRegisterPresenter {
    fun clear()
    fun showProgress()
    fun hideProgress()
    fun register(login: String, email: String, pass: String, steamid: String)
}