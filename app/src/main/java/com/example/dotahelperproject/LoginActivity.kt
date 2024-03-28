package com.example.dotahelperproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.dotahelperproject.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAuthorization.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View){
                if(binding.userLoginAuthorization.text.toString().isEmpty() || binding.userPassAuthorization.text.toString().isEmpty()){
                    Toast.makeText(applicationContext, "Не все поля заполнены", Toast.LENGTH_LONG).show()
                }
                else{
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.userLoginAuthorization.text.toString(), binding.userPassAuthorization.text.toString())
                        .addOnCompleteListener { task: Task<AuthResult> ->
                            if(task.isSuccessful){
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            }
                        }
                }
            }

        })

        val linkToReg: TextView = findViewById(R.id.link_to_registration)
        linkToReg.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}