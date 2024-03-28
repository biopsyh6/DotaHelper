package com.example.dotahelperproject.login.presenter.controller

import com.example.dotahelperproject.framework.util.thread.ThreadUtil

object LoginController {
    interface LoginControllerDelegate{
        fun onSuccess(response: String)
        fun onFailed()
    }
    fun requestLogin(login: String, password:String, delegate: LoginControllerDelegate){
        //send id and password to server and waiting response

        // start fetching data in thread
        ThreadUtil.startThread{
            //fetching data
            Thread.sleep(3000)

            //get response
            delegate.onSuccess("response from server, user info is a jsonObjectString, we need parsing it")
        }
    }
}