package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val animation: Animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
        binding.dailyVerse.animation = animation
        binding.workOutPLan.animation = animation
        binding.poems.animation = animation
        binding.naturalRemedies.animation = animation
        binding.dailyQuotes.animation = animation
        binding.bookClub.animation = animation
        binding.devotionals.animation = animation
        binding.dailyVerse.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_dailyVersesFragment)
        }
        binding.devotionals.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_devotionalsFragment)
        }
        binding.mealPlan.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_mealPlanFragment)

        }
        binding.bookClub.setOnClickListener {

        }
        binding.dailyQuotes.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_quotesFragment)

        }
        binding.naturalRemedies.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_remediesIllness)

        }
        binding.poems.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_poemsFragment)

        }
        binding.workOutPLan.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_workOutPlanFragment)
        }

        binding.signOut.setOnClickListener {
            sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
            if (sharedPreferences.getString("EMAIL", "") != null){
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()
                findNavController().navigate(R.id.action_homeFragment2_to_loginFragment)
            }
        }
        return binding.root
    }
}