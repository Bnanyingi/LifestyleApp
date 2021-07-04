package com.example.lifestyleapplication.ui.customisedmealplan

import android.content.Context
import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentViewUserDaysBinding
import com.example.lifestyleapplication.ui.adapters.CustomisedDaysAdapter
import com.example.lifestyleapplication.ui.interfaces.CustomisedDaysInterface
import com.example.lifestyleapplication.ui.models.allDays
import com.example.lifestyleapplication.ui.models.day
import com.example.lifestyleapplication.ui.retrofit.CustomisedDaysRetrofit
import retrofit2.Response
import javax.security.auth.callback.Callback

class FragmentViewUserDays : Fragment() {
    private lateinit var binding: FragmentViewUserDaysBinding
    private lateinit var customisedDaysInterface: CustomisedDaysInterface
    private lateinit var customisedDaysAdapter: CustomisedDaysAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewUserDaysBinding.inflate(inflater, container, false)
        binding.recyclerDays.visibility = View.GONE
        binding.progressUser.visibility = View.VISIBLE
        binding.imgBackUser.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentViewUserDays_to_userDaysFragment)
        }

        val activity = activity as Context
        customisedDaysAdapter = CustomisedDaysAdapter(activity)
        customisedDaysInterface = CustomisedDaysRetrofit.getRetrofit().create(CustomisedDaysInterface::class.java)
        val call: retrofit2.Call<allDays> = customisedDaysInterface.getDays()
        call.enqueue(object: retrofit2.Callback<allDays>{
            override fun onResponse(call: retrofit2.Call<allDays>, response: Response<allDays>) {
                if (response.isSuccessful){
                    binding.recyclerDays.visibility = View.VISIBLE
                    binding.progressUser.visibility = View.GONE
                    setData(response.body()!!.data)
                }
            }

            override fun onFailure(call: retrofit2.Call<allDays>, t: Throwable) {
                binding.recyclerDays.visibility = View.GONE
                binding.progressUser.visibility = View.VISIBLE
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })


        return binding.root
    }

    private fun setData(data: ArrayList<day>) {
        val plan: String = arguments?.getString("MEALPLAN").toString()
        val age: String = arguments?.getString("AGE").toString()
        val gender: String = arguments?.getString("GENDER").toString()
        val weight: String = arguments?.getString("WEIGHT").toString()
        val height = arguments?.getString("HEIGHT").toString()
        val type = arguments?.getString("BODYTYPE").toString()
        val lose = arguments?.getString("LOSE").toString()
        val gain = arguments?.getString("GAIN").toString()
        val maintain = arguments?.getString("MAINTAIN").toString()
        val breakfast = arguments?.getString("BREAKFAST").toString()
        val lunch = arguments?.getString("LUNCH").toString()
        val dinner = arguments?.getString("DINNER").toString()
        val dessert = arguments?.getString("DESSERT").toString()
        val dys = arguments?.getString("DAYS").toString()

        if (data.size >= dys.toInt()){
            val lst: ArrayList<day> = ArrayList()
            for (i in 0 until dys.toInt()) {
                var dy = day()
                dy.day = data[i].day
                dy.id = data[i].id
                dy.url = data[i].url
                lst.add(dy)
            }

            customisedDaysAdapter.getDays(lst, plan, breakfast, lunch, dinner, dessert)
            binding.recyclerDays.adapter = customisedDaysAdapter
            binding.recyclerDays.layoutManager = LinearLayoutManager(activity)
        }
        else{
            //Toast.makeText(activity, "No Data", Toast.LENGTH_LONG).show()
        }
    }
}