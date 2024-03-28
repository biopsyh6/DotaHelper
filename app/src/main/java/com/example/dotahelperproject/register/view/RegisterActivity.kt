package com.example.dotahelperproject.register.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dotahelperproject.R
import com.example.dotahelperproject.databinding.ActivityRegister3Binding
import com.example.dotahelperproject.login.view.LoginActivity
import com.example.dotahelperproject.register.model.RegisterModel
import com.example.dotahelperproject.register.presenter.IRegisterPresenter
import com.example.dotahelperproject.register.presenter.RegisterPresenter

class RegisterActivity : AppCompatActivity(), IRegisterView {

    private lateinit var binding: ActivityRegister3Binding
    private lateinit var iRegisterPresenter: IRegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegister3Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initPresenter()
        setListener()

    }
    private fun initPresenter(){
        iRegisterPresenter = RegisterPresenter(iRegisterView = this,
            iRegisterModel = RegisterModel())
    }
    private fun setListener(){
        binding.registrationButton.setOnClickListener{
            val login = binding.loginRegistrationEditText.text.toString()
            val email = binding.emailRegistrationEditText.text.toString()
            val pass = binding.passwordRegistrationEditText.text.toString()
            val steamid = binding.steamIdRegistrationEditText.text.toString()
            iRegisterPresenter.register(login, email, pass, steamid)
        }
    }
    override fun onClear() {
        binding.loginRegistrationEditText.setText("")
        binding.emailRegistrationEditText.setText("")
        binding.passwordRegistrationEditText.setText("")
        binding.steamIdRegistrationEditText.setText("")
    }

    override fun onShowProgress() {
        binding.frameLayoutProgressRegister.visibility = View.VISIBLE
    }

    override fun onHideProgress() {
        binding.frameLayoutProgressRegister.visibility = View.GONE
    }

    override fun onShowEmptyFieldsError() {
        Toast.makeText(applicationContext, "Не все поля заполнены", Toast.LENGTH_LONG).show()
    }

    override fun onShowFirebaseRegistrError() {
        Toast.makeText(applicationContext, "Ошибка регистрации Firebase", Toast.LENGTH_LONG).show()
    }

    override fun onStartLoginActivity() {
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}