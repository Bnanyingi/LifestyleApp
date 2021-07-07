package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentWorkOutPlanBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface

class WorkOutPlanFragment : Fragment() {

    private lateinit var binding: FragmentWorkOutPlanBinding
    private lateinit var general: generalinterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWorkOutPlanBinding.inflate(inflater, container, false)
        binding.backWorkOut.setOnClickListener {
            findNavController().navigate(R.id.action_workOutPlanFragment_to_homeFragment2)
        }

        binding.relGymWorkOut.setOnClickListener {

            general.sendWorkOut("Gym WorkOut Plan")
        }

        binding.relHomeWorkOutWithEquipment.setOnClickListener {
            general.sendWorkOut("Home WorkOut Plan With Equipment")
        }

        binding.relHomeWorkOutWithoutEquipment.setOnClickListener {
            general.sendWorkOut("Home WorkOut Plan Without Equipment")
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        general = context as generalinterface
    }
}