package com.example.lifestyleapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.lifestyleapplication.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.navHostFragment)
//        setupActionBarWithNavController(navController)

        if (navController.currentDestination?.label.toString().contains("Login")) {
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                navController.navigate(R.id.action_loginFragment_to_homeFragment2)
            }
        }
    }
}