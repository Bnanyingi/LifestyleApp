package com.example.lifestyleapplication.ui.customisedmealplan

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentViewUserMealBinding
import com.example.lifestyleapplication.ui.constants.constants
import com.example.lifestyleapplication.ui.interfaces.CustomisedViewMealInterface
import com.example.lifestyleapplication.ui.models.allMealDetails
import com.example.lifestyleapplication.ui.models.mealdetails
import com.example.lifestyleapplication.ui.models.selectedday
import com.example.lifestyleapplication.ui.retrofit.CustomisedViewMealRetrofit
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentViewUserMeal : Fragment() {
    private lateinit var binding: FragmentViewUserMealBinding
    private lateinit var customisedViewMealInterface: CustomisedViewMealInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewUserMealBinding.inflate(inflater, container, false)
        binding.imgBackSelected.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentViewUserMeal_to_fragmentViewUserDuration)
        }
        val animation = AnimationUtils.loadAnimation(activity, android.R.anim.fade_out)
        binding.cardDet.startAnimation(animation)

        binding.progressDet.visibility = View.VISIBLE
        binding.linearDet.visibility = View.GONE
        val select: selectedday = arguments?.getParcelable("SELECTEDDAY")!!
        binding.txtMealSelected.text = select.meal
        val plan: String = arguments?.getString("PLAN").toString()

        getData(plan, select.meal, select.day, arguments?.getString("CONDITION").toString())
        return binding.root
        return binding.root
    }

    private fun getData(plan: String, meal: String?, day: String?, toString: String) {
        customisedViewMealInterface = CustomisedViewMealRetrofit.getRetrofit().create(CustomisedViewMealInterface::class.java)
        val call: Call<allMealDetails> = customisedViewMealInterface.getData(plan, meal!!, day!!)
        call.enqueue(object: Callback<allMealDetails>{
            override fun onResponse(
                call: Call<allMealDetails>,
                response: Response<allMealDetails>
            ) {
                if (response.isSuccessful){
                    showData(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<allMealDetails>, t: Throwable) {

            }

        })
    }

    private fun showData(data: ArrayList<mealdetails>) {
        val activity = activity as Context
        val picasso = Picasso.Builder(activity)
        picasso.downloader(OkHttp3Downloader(context))
        picasso.build().load(constants.DEVOTIONALS + data[0].image).into(binding.imgSelected)
        binding.mealSelected.text = data[0].name
        binding.typeSelected.text = data[0].bodyType
        binding.benefitSelected.text = data[0].bodyGoals
    }
}