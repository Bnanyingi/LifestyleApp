package com.example.lifestyleapplication.ui.home

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentNaturalRemediesBinding

class NaturalRemediesFragment : Fragment() {

    private lateinit var binding: FragmentNaturalRemediesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNaturalRemediesBinding.inflate(inflater, container, false)
        binding.backRemedies.setOnClickListener {
            findNavController().navigate(R.id.action_naturalRemediesFragment_to_homeFragment2)
        }
        binding.nextRemedies.setOnClickListener {
            val name = binding.txtUserRemedies.text.toString().trim()
            if (TextUtils.isEmpty(name)){
                binding.txtUserRemedies.error = "Required"
            }
            else{
                findNavController().navigate(R.id.action_naturalRemediesFragment_to_remediesIllness)
            }
        }
        return binding.root
    }
}