package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.author
import com.example.lifestyleapplication.ui.models.devotionalTopics
import com.example.lifestyleapplication.ui.models.poem
import com.example.lifestyleapplication.ui.models.verse

interface generalinterface {
    fun sendAuthor(author: String)
    fun sendTitle(title: String?, auth: String)
    fun sendAllTitles(poem: poem)
    fun sendVerse(v: verse)
    fun getRemedy(string: String)
    fun getDevotionTopics(topic: String)
    fun getSelectedDevotionalTitle(title: String)
    fun getIllness(illness: String)
    fun goToCategories(name: String)
    fun goToDayMealPlans(day: String)
    fun getPlanDetails(duration: String)
}