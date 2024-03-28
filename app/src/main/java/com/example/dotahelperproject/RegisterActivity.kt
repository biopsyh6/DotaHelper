package com.example.dotahelperproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.dotahelperproject.databinding.ActivityLoginBinding
import com.example.dotahelperproject.databinding.ActivityRegisterBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(getLayoutInflater())
        setContentView(binding.getRoot())

        binding.buttonRegistration.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View){
                if(binding.userLoginRegistration.text.toString().isEmpty() || binding.userPasswordRegistration.text.toString().isEmpty()
                    || binding.userEmailRegistration.text.toString().isEmpty() || binding.userIdSteamRegistration.text.toString().isEmpty()){
                    Toast.makeText(applicationContext, "Не все поля заполнены", Toast.LENGTH_LONG).show()
                }
                else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding.userEmailRegistration.text.toString(),
                        binding.userPasswordRegistration.text.toString()
                    ).addOnCompleteListener(){ task: Task<AuthResult> ->
                        if(task.isSuccessful){
                            val userInfo: HashMap<String, String> = HashMap()
                            var steamid: String = findViewById<View?>(R.id.user_id_steam_registration).toString()
                            userInfo.put("email", binding.userEmailRegistration.text.toString())
                            userInfo.put("login", binding.userLoginRegistration.text.toString())
                            val currentUser = FirebaseAuth.getInstance().currentUser
                            if (currentUser != null) {
                                FirebaseDatabase.getInstance().reference.child("Users")
                                    .child(currentUser.uid).setValue(userInfo)
                                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }.addOnFailureListener {exception ->
                        Toast.makeText(applicationContext, "Ошибка: " + exception.message, Toast.LENGTH_LONG).show()
                    }

                }
            }

        })

        val linkToAuth: TextView = findViewById(R.id.link_to_authorization)
        linkToAuth.setOnClickListener{
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}