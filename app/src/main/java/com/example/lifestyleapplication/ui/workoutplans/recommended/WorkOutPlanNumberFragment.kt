package com.example.lifestyleapplication.ui.workoutplans.recommended

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentWorkOutPlanNumberBinding
import com.example.lifestyleapplication.ui.workoutplans.adapters.RecommendedNumberAdapter
import com.example.lifestyleapplication.ui.workoutplans.interfaces.RecommendedNumberInterface
import com.example.lifestyleapplication.ui.workoutplans.model.allworkoutnumbermodel
import com.example.lifestyleapplication.ui.workoutplans.model.workoutnumbermodel
import com.example.lifestyleapplication.ui.workoutplans.retrofitclasses.RecommendedNumberRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkOutPlanNumberFragment : Fragment() {
    private lateinit var binding: FragmentWorkOutPlanNumberBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recommendedNumberAdapter: RecommendedNumberAdapter
    private lateinit var recommendedNumberInterface: RecommendedNumberInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWorkOutPlanNumberBinding.inflate(inflater, container, false)
        binding.imgBackWorkNumber.setOnClickListener {
            findNavController().navigate(R.id.action_workOutPlanNumberFragment_to_workOutPlanDaysFragment)
        }

        binding.progressWorkOutNumber.visibility = View.VISIBLE
        binding.recyclerWorkOutNumber.visibility = View.GONE

        val activity = activity as Context
        linearLayoutManager = LinearLayoutManager(activity)
        recommendedNumberAdapter = RecommendedNumberAdapter(activity)

        setTitle()
        setData()
        return binding.root
    }

    private fun setTitle() {
        val sharedPreferences: SharedPreferences = activity?.getSharedPreferences("RECOMMENDED", Context.MODE_PRIVATE)!!
        binding.workOutNumberDay.text = sharedPreferences.getString("DAY", "")
    }

    private fun setData() {
        val sharedPreferences: SharedPreferences = activity?.getSharedPreferences("RECOMMENDED", Context.MODE_PRIVATE)!!
        val day = sharedPreferences.getString("DAY", "7").toString()
        val section = sharedPreferences.getString("WORKOUTNUMBER", "").toString()

        recommendedNumberInterface = RecommendedNumberRetrofit.getRetrofit().create(RecommendedNumberInterface::class.java)
        var call: Call<allworkoutnumbermodel> = recommendedNumberInterface.getNumbers(day, section)
        call.enqueue(object : Callback<allworkoutnumbermodel>{
            override fun onResponse(
                call: Call<allworkoutnumbermodel>,
                response: Response<allworkoutnumbermodel>
            ) {
                if (response.isSuccessful){
                    binding.progressWorkOutNumber.visibility = View.GONE
                    binding.recyclerWorkOutNumber.visibility = View.VISIBLE
                    getData(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<allworkoutnumbermodel>, t: Throwable) {
                Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
                binding.progressWorkOutNumber.visibility = View.VISIBLE
                binding.recyclerWorkOutNumber.visibility = View.GONE
            }

        })
    }

    private fun getData(data: ArrayList<workoutnumbermodel>) {
        recommendedNumberAdapter.getData(data)
        binding.recyclerWorkOutNumber.adapter = recommendedNumberAdapter
        binding.recyclerWorkOutNumber.layoutManager = linearLayoutManager

    }

}