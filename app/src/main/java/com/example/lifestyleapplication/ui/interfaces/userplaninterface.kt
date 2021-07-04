package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.allUserPlan
import com.example.lifestyleapplication.ui.models.userreturn
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface userplaninterface {
    @POST("mealplan/userplan.php")
    fun getPLans(
        @Query("email") email: String,
        @Query("days") days: String,
        @Query("id") id: String,
        @Query("day") day: String,
        @Query("breakfast") breakfast: String,
        @Query("lunch") lunch: String,
        @Query("dinner") dinner: String,
        @Query("dessert") dessert: String
    ): Call<userreturn>
}