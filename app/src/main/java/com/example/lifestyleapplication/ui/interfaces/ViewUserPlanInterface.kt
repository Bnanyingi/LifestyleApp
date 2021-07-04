package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.allUserPlan
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ViewUserPlanInterface {
    @POST("mealplan/viewuserplan.php")
    fun getUserPlan(
        @Query("email") email: String
    ): Call<allUserPlan>
}