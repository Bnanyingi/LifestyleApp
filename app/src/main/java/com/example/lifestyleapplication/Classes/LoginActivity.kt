package com.example.lifestyleapplication.Classes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.example.lifestyleapplication.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var login: Button
    private lateinit var register: Button

    //firebase init
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        login= findViewById(R.id.btnLogin)
        register = findViewById(R.id.newAccount)

        firebaseAuth = FirebaseAuth.getInstance()

        login.setOnClickListener {
            checkUser()
        }
        register.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }

    private fun checkUser() {
        val uEmail = email.text.toString().trim()
        val uPassword = password.text.toString().trim()

        if (TextUtils.isEmpty(uEmail)){
            email.error = "Enter Email"
        }
        else if(TextUtils.isEmpty(uPassword)){
            password.error = "Enter Password"
        }
        else{
            firebaseAuth.signInWithEmailAndPassword(uEmail, uPassword)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        moveToHome()
                    }
                    else{
                        Toast.makeText(this, "Check Email and Pasword", Toast.LENGTH_LONG).show()
                    }
                }

        }
    }

    fun moveToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null){
            moveToHome()
        }
    }
}