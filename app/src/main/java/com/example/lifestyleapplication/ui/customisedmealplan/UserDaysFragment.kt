package com.example.lifestyleapplication.ui.customisedmealplan

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentUserDaysBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserDaysFragment : Fragment() {
    private lateinit var binding: FragmentUserDaysBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var general: generalinterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserDaysBinding.inflate(inflater, container, false)
        binding.imgBackDays.setOnClickListener {
            findNavController().navigate(R.id.action_userDaysFragment_to_userMealDurationsFragment)
        }
        binding.floatingAge.setOnClickListener {
            val plan: String = arguments?.getString("MEALPLAN").toString()
            val age: String = arguments?.getString("AGE").toString()
            val gender: String = arguments?.getString("GENDER").toString()
            val weight: String = arguments?.getString("WEIGHT").toString()
            val height = arguments?.getString("HEIGHT").toString()
            val type = arguments?.getString("BODYTYPE").toString()
            val lose = arguments?.getString("LOSE").toString()
            val gain = arguments?.getString("GAIN").toString()
            val maintain = arguments?.getString("MAINTAIN").toString()
            val breakfast = arguments?.getString("BREAKFAST").toString()
            val lunch = arguments?.getString("LUNCH").toString()
            val dinner = arguments?.getString("DINNER").toString()
            val dessert = arguments?.getString("DESSERT").toString()

            val dys = binding.editDays.text.toString().trim()
            if (TextUtils.isEmpty(dys)){
                binding.editDays.error = "Enter Days"
            }
            else{
                general.sendCustomMealDays(dys, breakfast, lunch, dinner, dessert, lose, gain, maintain, type, height, weight, gender, plan, age)
            }
        }
        setUsername()
        return binding.root
    }

    private fun setUsername() {
        sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
        val username = sharedPreferences.getString("USERNAME", "")
        if (username != null){
            binding.introDays.text = "You want a meal plan for how many days " + username.toString() + "?"
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        general = context as generalinterface
    }
}