package com.example.lifestyleapplication.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentProfileBinding
import com.example.lifestyleapplication.ui.interfaces.ProfileInterface
import com.example.lifestyleapplication.ui.models.allUser
import com.example.lifestyleapplication.ui.models.user
import com.example.lifestyleapplication.ui.retrofit.ProfileRetrofit
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileInterface: ProfileInterface
    private lateinit var sharedPreferences: SharedPreferences
    private var gender: String = ""
    private var typeOfDiet = ""
    private var mealTaken = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val diets = resources.getStringArray(R.array.data_types)
        val meals = resources.getStringArray(R.array.meals)
        val activity = activity as Context
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, diets)
        val mealsAdapter: ArrayAdapter<String> = ArrayAdapter(activity, android.R.layout.simple_spinner_item, meals)
        mealsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDiet.adapter = arrayAdapter
        binding.spinnerMeals.adapter = mealsAdapter
        binding.spinnerMeals.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mealTaken = meals[position].toString()
                Toast.makeText(activity, meals[position], Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(activity, "Nothing selected", Toast.LENGTH_LONG).show()
            }

        }

        binding.spinnerDiet.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                typeOfDiet = diets[position].toString()
                Toast.makeText(activity, diets[position], Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(activity, "Nothing selected", Toast.LENGTH_LONG).show()
            }

        }

        binding.btnProfile.setOnClickListener {
            val id = arguments?.getInt("ID")
            if (id != null){
                saveDetails()
            }
            else{
                findNavController().navigate(R.id.action_profileFragment_to_signUpFragment)
            }

        }
        return binding.root
    }

    private fun saveDetails() {
        val username = binding.usernameProfile.text.toString().trim()
        val email = binding.emailProfile.text.toString().trim()
        val age = binding.ageProfile.text.toString().trim()
        val city = binding.cityProfile.text.toString().trim()
        val state = binding.stateProfile.text.toString().trim()
        val country = binding.countryProfile.text.toString().trim()
        val healthCondition = binding.healthConditionProfile.text.toString().trim()
        val disability = binding.disabilityProfile.text.toString().trim()
        val eatTimes = binding.eatTimesProfile.text.toString().trim()

        val id = binding.groupProfile.checkedRadioButtonId
        when (id) {
            R.id.radioFemale -> {
                gender = binding.radioFemale.text.toString()
                Toast.makeText(activity, gender.toString(), Toast.LENGTH_LONG).show()
            }
            R.id.radioMale -> {
                gender = binding.radioMale.text.toString()
                Toast.makeText(activity, gender.toString(), Toast.LENGTH_LONG).show()
            }
        }


        if (TextUtils.isEmpty(username)) {
            checkDetails(binding.usernameProfileInputLayout)
        } else if (TextUtils.isEmpty(email) || arguments?.getString("EMAIL").toString() != email) {
            checkDetails(binding.emailProfileInputLayout)
        } else if (TextUtils.isEmpty(age)) {
            checkDetails(binding.ageProfileInputLayout)
        } else if (TextUtils.isEmpty(city)) {
            checkDetails(binding.cityProfileInputLayout)
        } else if (TextUtils.isEmpty(country)) {
            checkDetails(binding.countryProfileInputLayout)
        } else if (TextUtils.isEmpty(healthCondition)) {
            checkDetails(binding.healthConditionProfileInputLayout)
        } else if (TextUtils.isEmpty(disability)) {
            checkDetails(binding.disabilityProfileInputLayout)
        } else if (TextUtils.isEmpty(eatTimes)) {
            checkDetails(binding.eatTimesProfileInputLayout)
        } else if (typeOfDiet == "") {
            Toast.makeText(activity, "Select Diet Type", Toast.LENGTH_LONG).show()
        } else if (mealTaken == "") {
            Toast.makeText(activity, "Select Meal", Toast.LENGTH_LONG).show()
        } else if (gender == "") {
            Toast.makeText(activity, "Gender Required", Toast.LENGTH_LONG).show()
        } else {

            profileInterface = ProfileRetrofit.getRetrofit().create(ProfileInterface::class.java)
            val call: Call<allUser> = profileInterface.register(
                arguments?.getInt("ID")!!,
                username,
                arguments?.getString("EMAIL").toString(),
                age,
                city,
                state,
                country,
                typeOfDiet,
                healthCondition,
                disability,
                eatTimes,
                mealTaken,
                gender
            )
            call.enqueue(object: Callback<allUser>{
                override fun onResponse(call: Call<allUser>, response: Response<allUser>) {
                    if (response.isSuccessful){
                        sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("ID", id.toString())
                        editor.putString("USERNAME", username)
                        editor.putString("EMAIL", email)
                        editor.putString("AGE", age)
                        editor.putString("CITY", city)
                        editor.putString("STATE", state)
                        editor.putString("COUNTRY", country)
                        editor.putString("DIET", typeOfDiet)
                        editor.putString("CONDITION", healthCondition)
                        editor.putString("DISABILITY", disability)
                        editor.putString("EATTIMES", eatTimes)
                        editor.putString("MEALTAKEN", mealTaken)
                        editor.putString("GENDER", gender)
                        editor.apply()

                        findNavController().navigate(R.id.action_profileFragment_to_homeFragment2)
                    }
                }

                override fun onFailure(call: Call<allUser>, t: Throwable) {
                    Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
                }

            })

        }
    }

    private fun checkDetails(view: TextInputLayout){
        view.isErrorEnabled = true
        view.error = "Required"
    }
}
