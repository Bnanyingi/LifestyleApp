package com.example.lifestyleapplication.Classes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.example.lifestyleapplication.Model.User
import com.example.lifestyleapplication.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var username: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var confirm: TextInputEditText
    private lateinit var btnBack: Button
    private lateinit var btnRegister: Button

    //firebase init
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_registration)

        email = findViewById(R.id.emailRegister)
        password = findViewById(R.id.passwordRegister)
        confirm = findViewById(R.id.passwordConfirm)
        btnBack = findViewById(R.id.backToLogin)
        btnRegister = findViewById(R.id.btnSignUp)
        username = findViewById(R.id.usernameRegister)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()

        btnRegister.setOnClickListener {
            createUser()
        }

        btnBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }

    private fun createUser() {
        val uEmail = email.text.toString().trim()
        val uPassword = password.text.toString().trim()
        val uConfirm = confirm.text.toString().trim()
        val uUsername = username.text.toString().trim()

        if (TextUtils.isEmpty(uEmail)) {
            email.error = "Enter Email"
        } else if (TextUtils.isEmpty(uUsername)) {
            username.error = "Enter Username"
        }
        else if (TextUtils.isEmpty(uPassword)) {
            password.error = "Enter Password"
        } else if (TextUtils.isEmpty(uConfirm)) {
            confirm.error = "Confirm Password"
        } else if (uPassword != uConfirm) {
            confirm.error = "Passwords not matching"
        } else {
            firebaseAuth.createUserWithEmailAndPassword(uEmail, uPassword)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        goToHome()
                        addToDatabase(uUsername)
                    }
                }
        }
    }

    private fun addToDatabase(uUsername: String) {
        databaseReference = firebaseDatabase.reference.child("User").child(firebaseAuth.currentUser!!.uid)
        var user: User = User(uUsername)
        databaseReference.setValue(user).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun goToHome(){
        startActivity(Intent(this, HomeActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    override fun onStart() {
        if (firebaseAuth.currentUser != null){
            goToHome()
        }
        super.onStart()
    }
}