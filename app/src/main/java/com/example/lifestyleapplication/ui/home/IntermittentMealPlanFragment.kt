package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentIntermittentMealPlanBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface

class IntermittentMealPlanFragment : Fragment() {
   private lateinit var binding: FragmentIntermittentMealPlanBinding
   private lateinit var general: generalinterface
   private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIntermittentMealPlanBinding.inflate(inflater, container, false)
        binding.floatingFasting.setOnClickListener {
            val dys = binding.editFasting.text.toString().trim()
            if (TextUtils.isEmpty(dys)){
                binding.editFasting.error = "Enter Days"
            }
            else{
                val plan = arguments?.getString("MEALPLAN").toString()
                general.sendFastingDays(dys, plan)
            }
        }
        setUsername()
        return binding.root
    }

    private fun setUsername() {
        sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
        val username = sharedPreferences.getString("USERNAME", "")
        if (username != null){
            binding.introFasting.text = "Enter number of fasting days " + username.toString()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        general = context as generalinterface
    }
}