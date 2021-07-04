package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentDailyVersesBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.example.lifestyleapplication.ui.models.verse
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList


class DailyVersesFragment : Fragment() {
    private lateinit var binding: FragmentDailyVersesBinding
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
        binding = FragmentDailyVersesBinding.inflate(inflater, container, false)
        val animation: Animation = AnimationUtils.loadAnimation(activity, android.R.anim.slide_out_right)
        binding.backVerse.setOnClickListener {
            findNavController().navigate(R.id.action_dailyVersesFragment_to_homeFragment2)
        }

        binding.progressVerses.visibility = View.GONE
        //binding.linVerse.visibility = View.GONE

        binding.relNext.setOnClickListener {
            val id = binding.radioGroupMood.checkedRadioButtonId
            when(id){
                R.id.radioHappy -> {
                    general.selectedMood("Thankful")
                }
                R.id.radioSad -> {
                    general.selectedMood("Stressed")
                }
                R.id.radioSkeptical -> {
                    general.selectedMood("Skeptical")
                }
                R.id.radioFarFromGed -> {
                    general.selectedMood("Far From God")
                }
                R.id.radioDoubting -> {
                    general.selectedMood("Doubting")
                }
                else -> {

                }
            }
        }
        binding.txtIntro.startAnimation(animation)
        setUsername()
        return binding.root
    }

    private fun setUsername() {
        sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
        val username = sharedPreferences.getString("USERNAME", "")
        if (username != null){
            binding.txtIntro.text = username.toString() + ", How are you feeling today?"
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        general = context as generalinterface
    }
}