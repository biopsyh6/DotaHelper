package com.example.dotahelperproject.login.presenter

import android.util.Log
import com.example.application.AttributeUseCases.firebase.AppFirebaseAttributeRepository
import com.example.dotahelperproject.framework.util.thread.ThreadUtil
import com.example.dotahelperproject.login.model.ILoginModel
import com.example.dotahelperproject.login.model.UserInfoModel
import com.example.dotahelperproject.login.presenter.controller.LoginController
import com.example.dotahelperproject.login.view.ILoginView
import com.example.application.RoleUseCases.firebase.AppFirebaseRoleRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.example.dotahelperproject.InsertsDb.DbFirebaseInitializer
import com.example.application.HeroUseCases.firebase.AppFirebaseHeroRepository

class LoginPresenter(var iLoginView: ILoginView,
    var iLoginModel: ILoginModel): ILoginPresenter {
    override fun clear() {
        iLoginView.onClear()
    }

    override fun showProgress() {
        iLoginView.onShowProgress()
    }

    override fun hideProgress() {
        iLoginView.onHideProgress()
    }

    override fun login(login: String, password: String) {
        if(login.isEmpty() || password.isEmpty()){
            iLoginView.onShowEmptyFieldsError()
            return
        }
        showProgress()

        iLoginModel.login(login, password){
            task -> hideProgress()
            if(task.isSuccessful){
                //INITIALIZE
                val attributeRepository = AppFirebaseAttributeRepository()
                val roleRepository = AppFirebaseRoleRepository()
                val heroRepository = AppFirebaseHeroRepository()
                val initializer = DbFirebaseInitializer(attributeRepository, roleRepository, heroRepository)
                initializer.initialize()
                //INITIALIZE
                iLoginView.onStartMainPageActivity(task.result?.user!!)
            }
            else{
                iLoginView.onShowFirebaseAuthError()
            }
        }


        LoginController.requestLogin(login = login, password = password,
            object: LoginController.LoginControllerDelegate{
            override fun onSuccess(response: String) {
                Log.d("???", "onSuccess $response")

                // parsing data here
                val userInfoModel = UserInfoModel()
                userInfoModel.login = "login"
                userInfoModel.steamId = "steamid"

                // back to UI thread
                ThreadUtil.startUIThread(0){
                    hideProgress()

                    iLoginView.onUpdateLoginResultUserInfo(login = userInfoModel.login,
                        steamid = userInfoModel.steamId)
                }
            }

            override fun onFailed() {
                Log.d("???", "onFailed")

                hideProgress()
            }

            private fun initializeData(){

            }
        })
    }
}