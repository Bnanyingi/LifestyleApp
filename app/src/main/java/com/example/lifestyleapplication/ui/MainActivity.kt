package com.example.lifestyleapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.ui.home.PoemsTitlesFragment
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.example.lifestyleapplication.ui.models.poem
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), generalinterface {

    private lateinit var navController: NavController
    private lateinit var poemsTitlesFragment: PoemsTitlesFragment

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

    override fun sendAuthor(author: String) {
        val bundle = bundleOf("Title" to author.toString())
        navController.navigate(R.id.action_poemsFragment_to_poemsTitlesFragment, bundle)

    }

    override fun sendTitle(title: String?, auth: String) {
        val bundle = bundleOf("SelectedTitle" to title, "SelectedAuthor" to auth)
        navController.navigate(R.id.action_poemsTitlesFragment_to_selectedTitleFragment, bundle)
    }

    override fun sendAllTitles(poem: poem) {
        val bundle = Bundle()
        bundle.putParcelable("POEM", poem)
        navController.navigate(R.id.action_selectedTitleFragment_to_viewPoemFragment, bundle)

    }
}