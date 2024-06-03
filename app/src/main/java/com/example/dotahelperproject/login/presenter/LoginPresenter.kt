package com.example.dotahelperproject.login.presenter

import android.content.Context
import android.util.Log
import com.example.application.AttributeUseCases.firebase.AppFirebaseAttributeRepository
import com.example.application.HeroPickerUseCases.firebase.AppFirebaseHeroPickerRepository
import com.example.application.HeroUseCases.firebase.AppFirebaseHeroRepository
import com.example.application.ItemUseCases.firebase.AppFirebaseItemRepository
import com.example.application.RoleUseCases.firebase.AppFirebaseRoleRepository
import com.example.application.SkillUseCases.firebase.AppFirebaseSkillRepository
import com.example.application.dbInitializer.DbFirebaseInitializer
import com.example.dotahelperproject.framework.util.thread.ThreadUtil
import com.example.dotahelperproject.login.model.ILoginModel
import com.example.dotahelperproject.login.model.UserInfoModel
import com.example.dotahelperproject.login.presenter.controller.LoginController
import com.example.dotahelperproject.login.view.ILoginView
import com.example.servicesapi.DataFetchers.HeroDataFetcher
import com.example.servicesapi.DataFetchers.HeroPickerDataFetcher
import com.example.servicesapi.DataFetchers.ItemDataFetcher
import com.example.servicesapi.DataFetchers.SkillDataFetcher
import com.example.servicesapi.HeroService
import com.example.servicesapi.ItemService
import com.example.servicesapi.MyApiService
import com.example.servicesapi.SkillService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.BufferedReader
import java.io.InputStreamReader

class LoginPresenter(var context: Context, var iLoginView: ILoginView,
    var iLoginModel: ILoginModel): ILoginPresenter, CoroutineScope by MainScope() {
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

            if (login.isEmpty() || password.isEmpty()) {
                iLoginView.onShowEmptyFieldsError()
                return
            }
            showProgress()

            iLoginModel.login(login, password) { task ->
                hideProgress()
                if (task.isSuccessful) {
                    //INITIALIZE
//                val attributeRepository = AppFirebaseAttributeRepository()
//                val roleRepository = AppFirebaseRoleRepository()
//                val heroRepository = AppFirebaseHeroRepository()
//                val initializer = DbFirebaseInitializer(attributeRepository, roleRepository, heroRepository)
//                initializer.initialize()
                    //INITIALIZE
                    //INITIALIZE
                    val attributeRepository = AppFirebaseAttributeRepository()
                    val roleRepository = AppFirebaseRoleRepository()
                    val heroRepository = AppFirebaseHeroRepository()
                    val skillRepository = AppFirebaseSkillRepository()
                    val itemRepository = AppFirebaseItemRepository()
                    heroRepository.clearAllHeroes()
                    skillRepository.clearAllSkills()
                    itemRepository.clearAllItems()
                    var heroService =
                        HeroService(attributeRepository, roleRepository, heroRepository)
                    var skillService = SkillService(skillRepository, heroRepository)
                    var itemService = ItemService(itemRepository)
                    var retrofit = Retrofit.Builder()
                        .baseUrl("https://www.dota2.com/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build()
                    var apiService = retrofit.create(MyApiService::class.java)

                    val heroFetcher = HeroDataFetcher(heroService, apiService)
                    val skillFetcher = SkillDataFetcher(skillService, apiService)
                    DbFirebaseInitializer.initializeAttributesAndRoles()
                    for (id in 1..138) {
                        if(id == 24 || id in 115..118 || id == 122 || id in 124..125 ||
                            id == 127 || id in 130..134) continue
                        heroFetcher.fetchHeroData(id)
                    }
                    for (id in 1..138) {
                        if(id == 24 || id in 115..118 || id == 122 || id in 124..125 ||
                            id == 127 || id in 130..134) continue
                        skillFetcher.fetchSkillData(id)
                    }


                    val json = loadJsonFromAsset(context, "items.json")
                    if (json != null) {
                        val itemDataFetcher = ItemDataFetcher(itemService)
                        itemDataFetcher.fetchItemDataFromJson(json)
                    }

                    val heroPickerRepository = AppFirebaseHeroPickerRepository()
                    retrofit = Retrofit.Builder()
                        .baseUrl("https://dota-counter-picker.onrender.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    apiService = retrofit.create(MyApiService::class.java)
                    val heroPickerFetcher = HeroPickerDataFetcher(apiService, heroPickerRepository)
                    heroPickerFetcher.fetchPickerHeroData()

                    //INITIALIZE
                    iLoginView.onStartMainPageActivity(task.result?.user!!)
                } else {
                    iLoginView.onShowFirebaseAuthError()
                }
            }


            LoginController.requestLogin(login = login, password = password,
                object : LoginController.LoginControllerDelegate {
                    override fun onSuccess(response: String) {
                        Log.d("???", "onSuccess $response")

                        // parsing data here
                        val userInfoModel = UserInfoModel()
                        userInfoModel.login = "login"
                        userInfoModel.steamId = "steamid"

                        // back to UI thread
                        ThreadUtil.startUIThread(0) {
                            hideProgress()

                            iLoginView.onUpdateLoginResultUserInfo(
                                login = userInfoModel.login,
                                steamid = userInfoModel.steamId
                            )
                        }
                    }

                    override fun onFailed() {
                        Log.d("???", "onFailed")

                        hideProgress()
                    }

                    private fun initializeData() {

                    }
                })

    }
    fun loadJsonFromAsset(context: Context, filename: String): String? {
        val identifier = "[ReadJSON]"
        try {
            val file = context.assets.open("$filename")
            val bufferedReader = BufferedReader(InputStreamReader(file))
            val stringBuilder = StringBuilder()
            bufferedReader.useLines { lines ->
                lines.forEach {
                    stringBuilder.append(it)
                }
            }
            val jsonString = stringBuilder.toString()
            return jsonString
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
//        return try {
//            val inputStream: InputStream = context.assets.open(filename)
//            val size: Int = inputStream.available()
//            val buffer = ByteArray(size)
//            inputStream.read(buffer)
//            inputStream.close()
//            String(buffer, Charsets.UTF_8)
//        } catch (ex: IOException) {
//            ex.printStackTrace()
//            null
//        }
    }
}