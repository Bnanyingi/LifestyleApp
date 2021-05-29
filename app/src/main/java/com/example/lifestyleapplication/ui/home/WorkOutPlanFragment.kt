package com.example.lifestyleapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentWorkOutPlanBinding

class WorkOutPlanFragment : Fragment() {

    private lateinit var binding: FragmentWorkOutPlanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //binding = FragmentWorkOutPlanBinding.inflate(R.id.)
        return inflater.inflate(R.layout.fragment_work_out_plan, container, false)
    }
}