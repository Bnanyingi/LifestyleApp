package com.example.lifestyleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickBibleVerse(view: View) {}
    fun onClickMeanPlan(view: View) {}
    fun onClickQuote(view: View) {}
}