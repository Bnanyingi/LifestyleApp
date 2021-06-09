package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentMealPlanBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface

class MealPlanFragment : Fragment() {
    private lateinit var binding: FragmentMealPlanBinding
    private lateinit var general: generalinterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealPlanBinding.inflate(inflater, container, false)
        val animation = AnimationUtils.loadAnimation(activity, android.R.anim.slide_in_left)
        binding.imgBackPlans.setOnClickListener {
            findNavController().navigate(R.id.action_mealPlanFragment_to_homeFragment2)
        }
        binding.relFullVegan.setOnClickListener {
            general.goToCategories(getString(R.string.fullvegan))
        }
        binding.relLactoOvo.setOnClickListener {
            general.goToCategories(getString(R.string.lactoovovegetarian))
        }
        binding.relLactoVegetarian.setOnClickListener {
            general.goToCategories(getString(R.string.lactovegeterian))
        }
        binding.relNonVegan.setOnClickListener {
            general.goToCategories(getString(R.string.nonvegan))
        }
        binding.relOvoVegetarian.setOnClickListener {
            general.goToCategories(getString(R.string.ovovegetarian))
        }
        binding.relPescatarian.setOnClickListener {
            general.goToCategories(getString(R.string.pescatarian))
        }

        //animation
        binding.relPescatarian.startAnimation(animation)
        binding.relOvoVegetarian.startAnimation(animation)
        binding.relNonVegan.startAnimation(animation)
        binding.relLactoVegetarian.startAnimation(animation)
        binding.relFullVegan.startAnimation(animation)
        binding.relLactoOvo.startAnimation(animation)
        return binding.root

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        general = context as generalinterface
    }
}