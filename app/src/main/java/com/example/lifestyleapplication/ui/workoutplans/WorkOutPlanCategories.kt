package com.example.lifestyleapplication.ui.workoutplans

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentWorkOutPlanCategoriesBinding

class WorkOutPlanCategories : Fragment() {
    private lateinit var binding: FragmentWorkOutPlanCategoriesBinding
    private var type: String = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences: SharedPreferences = activity?.getSharedPreferences("WORKOUTTYPE", Context.MODE_PRIVATE)!!
        type = sharedPreferences.getString("TYPE", "").toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWorkOutPlanCategoriesBinding.inflate(inflater, container, false)
        binding.backWorkOutType.setOnClickListener {
            findNavController().navigate(R.id.action_workOutPlanCategories_to_workOutPlanFragment)
        }

        binding.relCustomWorkOut.setOnClickListener {

        }

        binding.relRecommendedWorkOut.setOnClickListener {
            findNavController().navigate(R.id.action_workOutPlanCategories_to_workOutPlanDaysFragment)
            val sharedPreferences: SharedPreferences = activity?.getSharedPreferences("RECOMMENDED", Context.MODE_PRIVATE)!!
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("TYPE", type)
            editor.apply()
        }

        binding.workoutType.text = type
        return binding.root
    }
}