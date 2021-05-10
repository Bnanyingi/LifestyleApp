package com.example.lifestyleapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentDailyVersesBinding


class DailyVersesFragment : Fragment() {
    private lateinit var binding: FragmentDailyVersesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDailyVersesBinding.inflate(inflater, container, false)
        val animation: Animation = AnimationUtils.loadAnimation(activity, android.R.anim.slide_out_right)
        binding.backVerse.setOnClickListener {
            findNavController().navigate(R.id.action_dailyVersesFragment_to_homeFragment2)
        }

        binding.relNext.setOnClickListener {
            var id = binding.radioGroupMood.checkedRadioButtonId
            when(id){
                R.id.radioHappy -> {
                    findNavController().navigate(R.id.action_dailyVersesFragment_to_verseFragment)
                }
                R.id.radioSad -> {
                    findNavController().navigate(R.id.action_dailyVersesFragment_to_verseFragment)
                }
                R.id.radioNervous -> {
                    findNavController().navigate(R.id.action_dailyVersesFragment_to_verseFragment)
                }
                else -> {

                }
            }
        }
        binding.txtIntro.startAnimation(animation)

        return binding.root
    }

}