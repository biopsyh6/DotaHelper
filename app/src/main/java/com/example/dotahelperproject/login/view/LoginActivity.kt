package com.example.dotahelperproject.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dotahelperproject.R
import com.example.dotahelperproject.databinding.ActivityLogin3Binding
import com.example.dotahelperproject.login.model.LoginModel
import com.example.dotahelperproject.login.presenter.ILoginPresenter
import com.example.dotahelperproject.login.presenter.LoginPresenter
import com.example.dotahelperproject.mainpage.view.MainpageActivity
import com.example.dotahelperproject.register.view.RegisterActivity
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity(), ILoginView {


    private lateinit var enterLoginTextView: EditText
    private lateinit var enterPasswordTextView: EditText
    private lateinit var authorizationButton: Button
    private lateinit var linkToRegistrationTextView: TextView
    private lateinit var frameLayoutProgress: FrameLayout
    private lateinit var binding: ActivityLogin3Binding
    private lateinit var iLoginPresenter: ILoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin3Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        initPresenter()
        setListenerAuthButton()
        setListenerLinkToRegistration()
    }

    private fun initPresenter(){
        iLoginPresenter = LoginPresenter(iLoginView = this, iLoginModel = LoginModel(), context = this)
    }
    private fun setListenerAuthButton(){
        binding.authorizationButton.setOnClickListener {
            val login = binding.emailLoginEditText.text.toString()
            val password = binding.passwordLoginEditText.text.toString()
            iLoginPresenter.login(login, password)
        }
    }
    private fun setListenerLinkToRegistration(){
        binding.linkToRegistrationTextView.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onClear() {
        binding.emailLoginEditText.setText("")
        binding.passwordLoginEditText.setText("")
    }

    override fun onShowProgress() {
        binding.frameLayoutProgressLogin.visibility = View.VISIBLE
    }

    override fun onHideProgress() {
        binding.frameLayoutProgressLogin.visibility = View.GONE
    }

    override fun onUpdateLoginResultUserInfo(login: String, steamid: String) {

    }

    override fun onShowEmptyFieldsError() {
        Toast.makeText(applicationContext, "Не все поля заполнены", Toast.LENGTH_LONG).show()
    }

    override fun onShowFirebaseAuthError() {
        Toast.makeText(applicationContext, "Ошибка авторизации Firebase", Toast.LENGTH_LONG).show()
    }

    override fun onStartMainPageActivity(user: FirebaseUser) {
        val email = user.email
        // transition to MainActivity with information transfer
        val intent = Intent(this@LoginActivity, MainpageActivity::class.java)
        intent.putExtra("email", email )
        startActivity(intent)
    }
}