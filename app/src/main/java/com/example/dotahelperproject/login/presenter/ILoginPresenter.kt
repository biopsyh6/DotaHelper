package com.example.dotahelperproject.login.presenter

interface ILoginPresenter {
    fun clear()
    fun showProgress()
    fun hideProgress()
    fun login(login: String, password: String)
}