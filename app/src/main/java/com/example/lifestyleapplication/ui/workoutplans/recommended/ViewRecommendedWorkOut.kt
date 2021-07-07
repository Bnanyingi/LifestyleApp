package com.example.lifestyleapplication.ui.workoutplans.recommended

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentViewRecommendedWorkOutBinding
import com.example.lifestyleapplication.ui.workoutplans.model.workoutmodel
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class ViewRecommendedWorkOut : Fragment() {
    private lateinit var binding: FragmentViewRecommendedWorkOutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewRecommendedWorkOutBinding.inflate(inflater, container, false)
        binding.backViewWorkOut.setOnClickListener {
            findNavController().navigate(R.id.action_viewRecommendedWorkOut_to_recommendedWorkOutPlanFragment)
        }
        setData()
        return binding.root
    }

    private fun setData() {
        val exercise = arguments?.getParcelable<workoutmodel>("EXERCISE")
        val name = exercise!!.exercise
        val procedure = exercise.exercisedescription
        val img = exercise.exerciseimg

        binding.exercise.text = name
        binding.procedure.text = procedure

        val activity = activity as Context
        val picasso: Picasso.Builder = Picasso.Builder(activity)
        picasso.downloader(OkHttp3Downloader(activity))
        picasso.build().load(img).into(binding.imgExercise)
    }
}