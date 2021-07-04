package com.example.lifestyleapplication.ui.customisedmealplan

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentUserMealDurationsBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.google.android.material.shadow.ShadowRenderer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserMealDurationsFragment : Fragment() {
    private lateinit var binding: FragmentUserMealDurationsBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var general: generalinterface

    private var brk: String? = ""
    private var lnch: String = ""
    private var dnr: String = ""
    private var dsrt: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserMealDurationsBinding.inflate(inflater, container, false)
        binding.imgDurationBack.setOnClickListener {
            findNavController().navigate(R.id.action_userMealDurationsFragment_to_userBodyGoalsFragment)
        }
        binding.floatingDuration.setOnClickListener {
            val plan: String = arguments?.getString("MEALPLAN").toString()
            val age: String = arguments?.getString("AGE").toString()
            val gender: String = arguments?.getString("GENDER").toString()
            val weight: String = arguments?.getString("WEIGHT").toString()
            val height = arguments?.getString("HEIGHT").toString()
            val type = arguments?.getString("BODYTYPE").toString()
            val lose = arguments?.getString("LOSE").toString()
            val gain = arguments?.getString("GAIN").toString()
            val maintain = arguments?.getString("MAINTAIN").toString()

            if (binding.breakfast.isChecked){
                brk = binding.breakfast.text.toString()
            }
            if (binding.Lunch.isChecked){
                lnch = binding.Lunch.text.toString()
            }
            if (binding.Dinner.isChecked){
                dnr = binding.Dinner.text.toString()
            }
            if (binding.Dessert.isChecked){
                dsrt = binding.Dessert.text.toString()
            }
            general.sendCustomMealDuration(brk!!, lnch, dnr, dsrt, lose, gain, maintain, type, height, weight, gender, plan, age)
        }
        setUsername()
        return binding.root
    }

    private fun setUsername() {
        sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
        val username = sharedPreferences.getString("USERNAME", "")
        if (username != null){
            binding.introDuration.text = "Which of the meal durations would you want " + username.toString() + "?"
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        general = context as generalinterface
    }
}