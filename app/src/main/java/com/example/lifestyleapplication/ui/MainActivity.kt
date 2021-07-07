package com.example.lifestyleapplication.ui

import android.content.Context
import android.content.SharedPreferences
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
import com.example.lifestyleapplication.ui.models.selectedday
import com.example.lifestyleapplication.ui.models.verse
import com.example.lifestyleapplication.ui.workoutplans.model.workoutmodel
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), generalinterface {

    private lateinit var navController: NavController
    private lateinit var poemsTitlesFragment: PoemsTitlesFragment
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.navHostFragment)
//        setupActionBarWithNavController(navController)

        sharedPreferences = this.getSharedPreferences("USER", Context.MODE_PRIVATE)!!

        if (navController.currentDestination?.label.toString().contains("Login")) {
            val user = sharedPreferences.getString("EMAIL", "")
            if (user != "") {
                navController.navigate(R.id.action_loginFragment_to_homeFragment2)
            }
        }
    }

    override fun sendUser(id: Int, username: String, email: String) {
        val bundle = Bundle()
        bundle.putString("USERNAME", username)
        bundle.putString("EMAIL", email)
        bundle.putInt("ID", id)
        navController.navigate(R.id.action_signUpFragment_to_profileFragment, bundle)
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

    override fun sendVerse(v: verse) {
        val bundle = Bundle()
        bundle.putParcelable("VERSE", v)
        Toast.makeText(this, v.chapter.toString(), Toast.LENGTH_LONG).show()
        navController.navigate(R.id.action_dailyVersesFragment_to_verseFragment, bundle)
    }

    override fun getRemedy(string: String) {
        val bundle = Bundle()
        bundle.putString("REMEDY", string)
        navController.navigate(R.id.action_remediesIllness_to_foundRemediesFragment, bundle)
    }

    override fun getDevotionTopics(topic: String) {
        val bundle = Bundle()
        bundle.putString("TOPIC", topic)
        navController.navigate(R.id.action_devotionalsFragment_to_selectedDevotionTopicFragment, bundle)
    }

    override fun getSelectedDevotionalTitle(title: String) {
        val bundle = Bundle()
        bundle.putString("TITLE", title)
        navController.navigate(R.id.action_selectedDevotionTopicFragment_to_viewDevotionsFragment, bundle)
    }

    override fun getIllness(illness: String) {
        val bundle = Bundle()
        bundle.putString("ILLNESS", illness)
        navController.navigate(R.id.action_remediesIllness_to_foundRemediesFragment, bundle)
    }

    override fun goToCategories(name: String) {
        val bundle = Bundle()
        bundle.putString("PLAN", name)
        navController.navigate(R.id.action_mealPlanFragment_to_mealPlanCategoriesFragment, bundle)
    }

    override fun goToMealDaysCustomized(name: String) {
        val bundle = Bundle()
        bundle.putString("MEALPLAN", name)
        navController.navigate(R.id.action_mealPlanCategoriesFragment_to_userAgeFragment, bundle)
    }

    override fun goToMealDaysRecommended(name: String) {
        val bundle = Bundle()
        bundle.putString("MEALPLAN", name)
        navController.navigate(R.id.action_mealPlanCategoriesFragment_to_recommendedMealPlanFragment, bundle)
    }

    override fun goToMealDaysSpecial(name: String) {
        val bundle = Bundle()
        bundle.putString("MEALPLAN", name)
        navController.navigate(R.id.action_mealPlanCategoriesFragment_to_healthConditionFragment, bundle)
    }

    override fun goToMealDaysIntermittent(name: String) {
        val bundle = Bundle()
        bundle.putString("MEALPLAN", name)
        navController.navigate(R.id.action_mealPlanCategoriesFragment_to_intermittentMealPlanFragment, bundle)
    }

    override fun goToMealDaysUserPlans(name: String) {
        TODO("Not yet implemented")
    }

    override fun goToDayMealPlans(day: String, plan: String) {
        val bundle = Bundle()
        bundle.putString("DAY", day)
        bundle.putString("PLAN", plan)
        navController.navigate(R.id.action_recommendedMealPlanFragment_to_selectedDayFragment, bundle)
    }

    override fun goToFastingDayMealPlans(day: String, plan: String) {
        val bundle = Bundle()
        bundle.putString("DAY", day)
        bundle.putString("PLAN", plan)
        navController.navigate(R.id.action_fragmentFastingDays_to_fragmentSelectedDayDurations, bundle)
    }

    override fun goToIntermittentDayMealsPlan(
        day: String,
        plan: String,
        brk: String,
        luch: String,
        dinn: String,
        dest: String
    ) {
        val bundle = Bundle()
        bundle.putString("DAY", day)
        bundle.putString("PLAN", plan)
        navController.navigate(R.id.action_fragmentSelectedDayDurations_to_fragmentIntermittentMeal, bundle)
    }

    override fun goToSpecialDayMealsPlan(
        day: String,
        plan: String,
        brk: String,
        luch: String,
        dinn: String,
        dest: String,
        condition: String
    ) {
        val bundle = Bundle()
        bundle.putString("DAY", day)
        bundle.putString("PLAN", plan)
        bundle.putString("BREAKFAST", brk)
        bundle.putString("LUNCH", luch)
        bundle.putString("DINNER", dinn)
        bundle.putString("DESSERT", dest)
        bundle.putString("CONDITION", condition)
        navController.navigate(R.id.action_fragmentSpecialDays_to_fragmentSpecialDurations, bundle)
    }

    override fun getPlanDetails(duration: selectedday, plan: String) {
        val bundle = Bundle()
        bundle.putParcelable("SELECTEDDAY", duration)
        bundle.putString("PLAN", plan)
        navController.navigate(R.id.action_selectedDayFragment_to_viewSelectedMealDurationFragment, bundle)
    }

    override fun getSpecialPlanDetails(duration: selectedday, plan: String, condition: String) {
        val bundle = Bundle()
        bundle.putParcelable("SELECTEDDAY", duration)
        bundle.putString("PLAN", plan)
        bundle.putString("CONDITION", condition)
        navController.navigate(R.id.action_fragmentSpecialDurations_to_fragmentSpecialMeal, bundle)
    }

    override fun getIntermittentPlanDetails(duration: selectedday, plan: String) {
        val bundle = Bundle()
        bundle.putParcelable("SELECTEDDAY", duration)
        bundle.putString("PLAN", plan)
        navController.navigate(R.id.action_fragmentSelectedDayDurations_to_fragmentIntermittentMeal, bundle)
    }

    override fun selectedMood(mood: String) {
        val bundle = Bundle()
        bundle.putString("MOOD", mood)
        navController.navigate(R.id.action_dailyVersesFragment_to_verseFragment, bundle)
    }

    override fun sendCondition(condition: String, pln: String) {
        val bundle = Bundle()
        bundle.putString("MEALPLAN", pln)
        bundle.putString("CONDITION", condition)
        navController.navigate(R.id.action_healthConditionFragment_to_mealPlanDaysFragment, bundle)
    }

    override fun sendDays(dy: String, pln: String, condition: String) {
        val bundle = Bundle()
        bundle.putString("DAY", dy)
        bundle.putString("MEALPLAN", pln)
        bundle.putString("CONDITION", condition)
        navController.navigate(R.id.action_mealPlanDaysFragment_to_mealDurationFragment, bundle)
    }

    override fun sendDurations(
        brk: String,
        lnc: String,
        din: String,
        dst: String,
        dy: String,
        pln: String,
        condition: String
    ) {
        val bundle = Bundle()
        bundle.putString("BREAKFAST", brk)
        bundle.putString("LUNCH", lnc)
        bundle.putString("DINNER", din)
        bundle.putString("DESSERT", dst)
        bundle.putString("DAY", dy)
        bundle.putString("MEALPLAN", pln)
        bundle.putString("CONDITION", condition)
        navController.navigate(R.id.action_mealDurationFragment_to_fragmentSpecialDays, bundle)
    }

    override fun sendFastingDays(days: String, plan: String) {
        val bundle = Bundle()
        bundle.putString("DAYS", days)
        bundle.putString("MEALPLAN", plan)
        navController.navigate(R.id.action_intermittentMealPlanFragment_to_fragmentFastingDays, bundle)
    }

    override fun sendCustomAge(age: String, plan: String) {
        val bundle = Bundle()
        bundle.putString("AGE", age)
        bundle.putString("MEALPLAN", plan)
        navController.navigate(R.id.action_userAgeFragment_to_userGenderFragment, bundle)
    }

    override fun sendCustomGender(gender: String, plan: String, age: String) {
        val bundle = Bundle()
        bundle.putString("AGE", age)
        bundle.putString("MEALPLAN", plan)
        bundle.putString("GENDER", gender)
        navController.navigate(R.id.action_userGenderFragment_to_userCurrentWeightFragment, bundle)

    }

    override fun sendCustomWeight(weight: String, gender: String, plan: String, age: String) {
        val bundle = Bundle()
        bundle.putString("AGE", age)
        bundle.putString("MEALPLAN", plan)
        bundle.putString("GENDER", gender)
        bundle.putString("WEIGHT", weight)
        navController.navigate(R.id.action_userCurrentWeightFragment_to_userHeightFragment, bundle)
    }

    override fun sendCustomHeight(
        height: String,
        weight: String,
        gender: String,
        plan: String,
        age: String
    ) {
        val bundle = Bundle()
        bundle.putString("AGE", age)
        bundle.putString("MEALPLAN", plan)
        bundle.putString("GENDER", gender)
        bundle.putString("WEIGHT", weight)
        bundle.putString("HEIGHT", height)
        navController.navigate(R.id.action_userHeightFragment_to_userBodyTypeFragment, bundle)
    }

    override fun sendCustomBodyType(
        type: String,
        height: String,
        weight: String,
        gender: String,
        plan: String,
        age: String
    ) {
        val bundle = Bundle()
        bundle.putString("AGE", age)
        bundle.putString("MEALPLAN", plan)
        bundle.putString("GENDER", gender)
        bundle.putString("WEIGHT", weight)
        bundle.putString("HEIGHT", height)
        bundle.putString("BODYTYPE", type)
        navController.navigate(R.id.action_userBodyTypeFragment_to_userBodyGoalsFragment, bundle)
    }

    override fun sendCustomBodyGoals(
        lose: String,
        gain: String,
        maintain: String,
        type: String,
        height: String,
        weight: String,
        gender: String,
        plan: String,
        age: String
    ) {
        val bundle = Bundle()
        bundle.putString("AGE", age)
        bundle.putString("MEALPLAN", plan)
        bundle.putString("GENDER", gender)
        bundle.putString("WEIGHT", weight)
        bundle.putString("HEIGHT", height)
        bundle.putString("BODYTYPE", type)
        bundle.putString("LOSE", lose)
        bundle.putString("GAIN", gain)
        bundle.putString("MAINTAIN", maintain)
        navController.navigate(R.id.action_userBodyGoalsFragment_to_userMealDurationsFragment, bundle)
    }

    override fun sendCustomMealDuration(
        brk: String,
        lnc: String,
        din: String,
        dst: String,
        lose: String,
        gain: String,
        maintain: String,
        type: String,
        height: String,
        weight: String,
        gender: String,
        plan: String,
        age: String
    ) {
        val bundle = Bundle()
        bundle.putString("AGE", age)
        bundle.putString("MEALPLAN", plan)
        bundle.putString("GENDER", gender)
        bundle.putString("WEIGHT", weight)
        bundle.putString("HEIGHT", height)
        bundle.putString("BODYTYPE", type)
        bundle.putString("LOSE", lose)
        bundle.putString("GAIN", gain)
        bundle.putString("MAINTAIN", maintain)
        bundle.putString("BREAKFAST", brk)
        bundle.putString("LUNCH", lnc)
        bundle.putString("DINNER", din)
        bundle.putString("DESSERT", dst)

        navController.navigate(R.id.action_userMealDurationsFragment_to_userDaysFragment, bundle)

    }

    override fun sendCustomMealDays(
        dys: String,
        brk: String,
        lnc: String,
        din: String,
        dst: String,
        lose: String,
        gain: String,
        maintain: String,
        type: String,
        height: String,
        weight: String,
        gender: String,
        plan: String,
        age: String
    ) {
        val bundle = Bundle()
        bundle.putString("AGE", age)
        bundle.putString("MEALPLAN", plan)
        bundle.putString("GENDER", gender)
        bundle.putString("WEIGHT", weight)
        bundle.putString("HEIGHT", height)
        bundle.putString("BODYTYPE", type)
        bundle.putString("LOSE", lose)
        bundle.putString("GAIN", gain)
        bundle.putString("MAINTAIN", maintain)
        bundle.putString("BREAKFAST", brk)
        bundle.putString("LUNCH", lnc)
        bundle.putString("DINNER", din)
        bundle.putString("DESSERT", dst)
        bundle.putString("DAYS", dys)
        navController.navigate(R.id.action_userDaysFragment_to_fragmentViewUserDays, bundle)
    }

    override fun sendToCustomDays(
        day: String,
        plan: String,
        brk: String,
        luch: String,
        dinn: String,
        dest: String
    ) {
        val bundle = Bundle()
        bundle.putString("DAY", day)
        bundle.putString("PLAN", plan)
        bundle.putString("BREAKFAST", brk)
        bundle.putString("LUNCH", luch)
        bundle.putString("DINNER", dinn)
        bundle.putString("DESSERT", dest)
        navController.navigate(R.id.action_fragmentViewUserDays_to_fragmentViewUserDuration, bundle)
    }

    override fun getCustomPlanDetails(duration: selectedday, plan: String) {
        val bundle = Bundle()
        bundle.putParcelable("SELECTEDDAY", duration)
        bundle.putString("PLAN", plan)
        navController.navigate(R.id.action_fragmentViewUserDuration_to_fragmentViewUserMeal, bundle)
    }

    override fun sendWorkOut(string: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("WORKOUTTYPE", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("TYPE", string)
        editor.apply()
        navController.navigate(R.id.action_workOutPlanFragment_to_workOutPlanCategories)
    }

    override fun sendDay(day: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("RECOMMENDED", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("DAY", day)
        editor.apply()
        navController.navigate(R.id.action_workOutPlanDaysFragment_to_workOutPlanNumberFragment)
    }

    override fun sendWorkOutNumber(numb: String, img: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("RECOMMENDED", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("WORKOUTNUMBER", numb)
        editor.putString("WORKOUTIMAGE", img)
        editor.apply()
        navController.navigate(R.id.action_workOutPlanNumberFragment_to_recommendedWorkOutPlanFragment)
    }

    override fun sendExercise(exercise: workoutmodel) {
        val bundle: Bundle = Bundle()
        bundle.putParcelable("EXERCISE", exercise)
        navController.navigate(R.id.action_recommendedWorkOutPlanFragment_to_viewRecommendedWorkOut, bundle)

    }
}