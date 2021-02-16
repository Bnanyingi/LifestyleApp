package com.example.lifestyleapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //variables
     private val SPLASH_TIME_OUT :Long =3000//3 sec


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_main)
        //animation variables declared, initialized and called
        val topAnim :Animation = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        val bottomAnim : Animation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)
        //set our animation to our image and text view
        val image : ImageView = findViewById(R.id.imageView)
        val logo : TextView = findViewById(R.id.textView)


        image.animation = topAnim
        logo.animation = bottomAnim

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this,DashBoard::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)


    }
}