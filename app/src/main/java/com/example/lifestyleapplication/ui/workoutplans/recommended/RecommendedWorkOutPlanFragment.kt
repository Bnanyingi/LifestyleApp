package com.example.lifestyleapplication.ui.workoutplans.recommended

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentRecommendedWorkOutPlanBinding
import com.example.lifestyleapplication.ui.workoutplans.adapters.SelectedRecommendedWorkOutNumber
import com.example.lifestyleapplication.ui.workoutplans.interfaces.SelectedRecommendedWorkOutNumberInterface
import com.example.lifestyleapplication.ui.workoutplans.model.allworkoutmodel
import com.example.lifestyleapplication.ui.workoutplans.model.workoutmodel
import com.example.lifestyleapplication.ui.workoutplans.retrofitclasses.SelectedRecommendedWorkOutNumberRetrofit
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendedWorkOutPlanFragment : Fragment() {
    private lateinit var binding: FragmentRecommendedWorkOutPlanBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var selectedRecommendedWorkOutNumberInterface: SelectedRecommendedWorkOutNumberInterface
    private lateinit var selectedRecommendedWorkOutNumber: SelectedRecommendedWorkOutNumber
    private var setn: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecommendedWorkOutPlanBinding.inflate(inflater, container, false)
        var activity = activity as Context
        linearLayoutManager = LinearLayoutManager(activity)
        selectedRecommendedWorkOutNumber = SelectedRecommendedWorkOutNumber(activity)
        setTitleAndImage()
        getData()
        setDay()
        return binding.root
    }

    private fun setDay() {
        val sharedPreferences: SharedPreferences = activity?.getSharedPreferences("WORKOUTTYPE", Context.MODE_PRIVATE)!!
        setn = sharedPreferences.getString("TYPE", "").toString()
    }

    private fun getData() {
        val sharedPreferences: SharedPreferences = activity?.getSharedPreferences("RECOMMENDED", Context.MODE_PRIVATE)!!
        val day: String = sharedPreferences.getString("DAY", "").toString()
        val number: String = sharedPreferences.getString("WORKOUTNUMBER", "").toString()

        selectedRecommendedWorkOutNumberInterface = SelectedRecommendedWorkOutNumberRetrofit.getRetrofit().create(SelectedRecommendedWorkOutNumberInterface::class.java)
        var call: Call<allworkoutmodel> = selectedRecommendedWorkOutNumberInterface.getNumbers(day, number, setn)
        call.enqueue(object : Callback<allworkoutmodel>{
            override fun onResponse(
                call: Call<allworkoutmodel>,
                response: Response<allworkoutmodel>
            ) {
                if (response.isSuccessful){
                    getOutput(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<allworkoutmodel>, t: Throwable) {
                Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun getOutput(data: ArrayList<workoutmodel>) {
        selectedRecommendedWorkOutNumber.getData(data)
        binding.recyclerNumber.adapter = selectedRecommendedWorkOutNumber
        binding.recyclerNumber.layoutManager = linearLayoutManager
    }

    private fun setTitleAndImage() {
        val sharedPreferences: SharedPreferences = activity?.getSharedPreferences("RECOMMENDED", Context.MODE_PRIVATE)!!
        val title: String = sharedPreferences.getString("WORKOUTNUMBER", "").toString()
        val img: String = sharedPreferences.getString("WORKOUTIMAGE", "").toString()
        binding.txtHead.text = title

        val activity = activity as Context
        val picasso: Picasso.Builder = Picasso.Builder(activity)
        picasso.downloader(OkHttp3Downloader(activity))
        picasso.build().load(img).into(binding.imgColl)
    }
}