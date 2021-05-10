package com.example.lifestyleapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

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

        }
        binding.mealPlan.setOnClickListener {

        }
        binding.bookClub.setOnClickListener {

        }
        binding.dailyQuotes.setOnClickListener {

        }
        binding.naturalRemedies.setOnClickListener {

        }
        binding.poems.setOnClickListener {

        }
        binding.workOutPLan.setOnClickListener {

        }
        return binding.root
    }

}