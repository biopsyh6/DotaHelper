package com.example.dotahelperproject.mainpage.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dotahelperproject.R
import com.example.dotahelperproject.databinding.ActivityMainpageBinding
import com.example.dotahelperproject.heroespage.view.HeroespageActivity
import com.example.dotahelperproject.itemspage.view.ItemspageActivity
import com.example.dotahelperproject.login.model.ILoginModel
import com.example.dotahelperproject.login.model.LoginModel
import com.example.dotahelperproject.mainpage.presenter.IMainpagePresenter
import com.example.dotahelperproject.pickerpage.PickerpageActivity
import com.example.dotahelperproject.profilepage.view.ProfilepageActivity
import com.example.dotahelperproject.runespage.model.room.RuneRoomRepository

class MainpageActivity : AppCompatActivity(), IMainpageView {

    val runeRepository = RuneRoomRepository()

    private lateinit var binding: ActivityMainpageBinding
    private lateinit var iMainpagePresenter: IMainpagePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainpageBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setListenerHeroesImageButton()
        setListenerItemsImageButton()
        setListenerProfileImageButton()
        setListenerPickerImageButton()
        setListenerLogoutImageButton()
    }

    override fun onStartHeroesPageActivity() {
        val intent = Intent(this@MainpageActivity, HeroespageActivity::class.java)
        startActivity(intent)
    }

    private fun setListenerItemsImageButton() {
        binding.itemsImageButton.setOnClickListener {
            val intent = Intent(this@MainpageActivity, ItemspageActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setListenerProfileImageButton(){
        binding.profileImageButton.setOnClickListener {
            val intent = Intent(this@MainpageActivity, ProfilepageActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setListenerHeroesImageButton(){
        binding.heroesImageButton.setOnClickListener {
            val intent = Intent(this@MainpageActivity, HeroespageActivity::class.java)
            startActivity(intent)


        }
    }

    private fun setListenerPickerImageButton(){
        binding.pickerImageButton.setOnClickListener {
            val intent = Intent(this@MainpageActivity, PickerpageActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setListenerLogoutImageButton(){
        binding.logoutImageButton.setOnClickListener {
            val loginModel: ILoginModel = LoginModel()
            loginModel.logout()

            val intent = Intent(this@MainpageActivity, com.example.dotahelperproject.login.view.LoginActivity::class.java)
            startActivity(intent)
        }
    }

}