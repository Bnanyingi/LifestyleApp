package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentMealPlanCategoriesBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface

class MealPlanCategoriesFragment : Fragment() {
    private lateinit var binding: FragmentMealPlanCategoriesBinding
    private lateinit var general: generalinterface

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
            general.goToMealDaysCustomized(arguments?.getString("PLAN").toString())
        }
        binding.relIntermittent.setOnClickListener {
            general.goToMealDaysIntermittent(arguments?.getString("PLAN").toString())
        }
        binding.relRecommended.setOnClickListener {
            general.goToMealDaysRecommended(arguments?.getString("PLAN").toString())
        }
        binding.relSpecial.setOnClickListener {
            general.goToMealDaysSpecial(arguments?.getString("PLAN").toString())
        }
        binding.txtMealCategory.text = arguments?.getString("PLAN")
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        general = context as generalinterface
    }
}