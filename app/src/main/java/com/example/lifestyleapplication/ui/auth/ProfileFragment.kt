package com.example.lifestyleapplication.ui.auth

import android.content.Context
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
import com.example.lifestyleapplication.ui.models.user
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private var gender: String = ""
    private var typeOfDiet = ""
    private var mealTaken = ""
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()
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
            if (firebaseAuth.currentUser != null){
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
        var state = binding.stateProfile.text.toString().trim()
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
        } else if (TextUtils.isEmpty(email) || firebaseAuth.currentUser.email.toString() != email) {
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

            firebaseDatabase = FirebaseDatabase.getInstance()
            databaseReference = firebaseDatabase.reference.child("users").child(firebaseAuth.uid!!)
            val userAdd: user = user()
            userAdd.username = username
            userAdd.email = firebaseAuth.currentUser.email.toString()
            userAdd.age = age
            userAdd.city = city
            userAdd.country = country
            userAdd.diet = typeOfDiet
            userAdd.disability = disability
            userAdd.healthcondition = healthCondition
            userAdd.gender = gender
            userAdd.meal = mealTaken
            userAdd.state = state
            userAdd.timesyoueat = eatTimes
            databaseReference.setValue(userAdd)

            databaseReference.addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        findNavController().navigate(R.id.action_profileFragment_to_homeFragment2)
                        Toast.makeText(activity, "Successfully added", Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(activity, "Not Added", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(activity, error.message.toString(), Toast.LENGTH_LONG).show()
                }

            })

        }
    }

    private fun checkDetails(view: TextInputLayout){
        view.isErrorEnabled = true
        view.error = "Required"
    }
}
