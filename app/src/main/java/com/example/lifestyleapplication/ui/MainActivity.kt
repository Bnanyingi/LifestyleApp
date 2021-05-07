package com.example.lifestyleapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lifestyleapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickBibleVerse(view: View) {}
    fun onClickMeanPlan(view: View) {}
    fun onClickQuote(view: View) {}
}