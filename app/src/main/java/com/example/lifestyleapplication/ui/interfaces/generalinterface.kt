package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.author
import com.example.lifestyleapplication.ui.models.poem

interface generalinterface {
    fun sendAuthor(author: String)
    fun sendTitle(title: String?, auth: String)
    fun sendAllTitles(poem: poem)
}