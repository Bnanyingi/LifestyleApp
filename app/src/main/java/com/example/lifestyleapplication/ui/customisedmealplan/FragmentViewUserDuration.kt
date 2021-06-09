package com.example.lifestyleapplication.ui.customisedmealplan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentViewUserDurationBinding
import com.example.lifestyleapplication.ui.adapters.CustomisedDurationAdapter
import com.example.lifestyleapplication.ui.interfaces.CustomisedDurationInterface
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.example.lifestyleapplication.ui.models.AllSelectedDays
import com.example.lifestyleapplication.ui.models.selectedday
import com.example.lifestyleapplication.ui.retrofit.CustomisedDurationRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentViewUserDuration : Fragment() {
    private lateinit var general: generalinterface
    private lateinit var customisedDurationAdapter: CustomisedDurationAdapter
    private lateinit var binding: FragmentViewUserDurationBinding
    private lateinit var customisedDurationInterface: CustomisedDurationInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewUserDurationBinding.inflate(inflater, container, false)
        binding.selectedBack.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentViewUserDuration_to_fragmentViewUserDays)
        }
        getData()
        return binding.root
    }

    private fun getData() {
        val day: String = arguments?.getString("DAY").toString()
        customisedDurationInterface = CustomisedDurationRetrofit.getRetrofit().create(CustomisedDurationInterface::class.java)
        val call: Call<AllSelectedDays> = customisedDurationInterface.getDuration(day)
        call.enqueue(object: Callback<AllSelectedDays>{
            override fun onResponse(
                call: Call<AllSelectedDays>,
                response: Response<AllSelectedDays>
            ) {
                if (response.isSuccessful){
                    sendData(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<AllSelectedDays>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun sendData(data: ArrayList<selectedday>) {
        val brk: String = arguments?.getString("BREAKFAST").toString()
        val lnc: String = arguments?.getString("LUNCH").toString()
        val din: String = arguments?.getString("DINNER").toString()
        val dst: String = arguments?.getString("DESSERT").toString()
        val plan: String = arguments?.getString("PLAN").toString()
        customisedDurationAdapter.getDuration(data, plan)
        binding.recyclerSelectedDay.adapter = customisedDurationAdapter
        binding.recyclerSelectedDay.layoutManager = LinearLayoutManager(activity)
    }


}