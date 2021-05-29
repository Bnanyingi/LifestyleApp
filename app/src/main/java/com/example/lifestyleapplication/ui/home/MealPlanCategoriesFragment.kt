package com.example.lifestyleapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentMealPlanCategoriesBinding

class MealPlanCategoriesFragment : Fragment() {
    private lateinit var binding: FragmentMealPlanCategoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealPlanCategoriesBinding.inflate(inflater, container, false)
        binding.imgBackCategory.setOnClickListener {
            findNavController().navigate(R.id.action_mealPlanCategoriesFragment_to_mealPlanFragment)
        }
        binding.relCustom.setOnClickListener {

        }
        binding.relIntermittent.setOnClickListener {

        }
        binding.relRecommended.setOnClickListener {
            findNavController().navigate(R.id.action_mealPlanCategoriesFragment_to_recommendedMealPlanFragment)
        }
        binding.relSpecial.setOnClickListener { 

        }
        binding.txtMealCategory.text = arguments?.getString("PLAN")
        return binding.root
    }

}