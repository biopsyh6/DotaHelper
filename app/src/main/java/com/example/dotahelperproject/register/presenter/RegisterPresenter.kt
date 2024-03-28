package com.example.dotahelperproject.register.presenter

import com.example.dotahelperproject.register.model.IRegisterModel
import com.example.dotahelperproject.register.view.IRegisterView
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegisterPresenter(var iRegisterView: IRegisterView, var iRegisterModel: IRegisterModel)
    : IRegisterPresenter {
    override fun clear() {
        iRegisterView.onClear()
    }

    override fun showProgress() {
        iRegisterView.onShowProgress()
    }

    override fun hideProgress() {
        iRegisterView.onHideProgress()
    }

    override fun register(login: String, email: String, pass: String, steamid: String) {
        if(login.isEmpty() || email.isEmpty() || pass.isEmpty() || steamid.isEmpty()){
            iRegisterView.onShowEmptyFieldsError()
            return
        }
        showProgress()
        iRegisterModel.register(login, email, pass, steamid){
            task -> hideProgress()
            if(task.isSuccessful){
                iRegisterView.onStartLoginActivity()
            }
            else{
                iRegisterView.onShowFirebaseRegistrError()
            }
        }

    }
}