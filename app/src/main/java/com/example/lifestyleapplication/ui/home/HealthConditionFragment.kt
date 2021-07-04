package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentHealthConditionBinding
import com.example.lifestyleapplication.ui.adapters.HealthConditionAdapter
import com.example.lifestyleapplication.ui.interfaces.HealthConditionsInterface
import com.example.lifestyleapplication.ui.models.AllHealthConditions
import com.example.lifestyleapplication.ui.retrofit.HealthConditionsRetrofit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HealthConditionFragment : Fragment() {
    private lateinit var binding: FragmentHealthConditionBinding
    private lateinit var healthConditionAdapter: HealthConditionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var healthConditionsInterface: HealthConditionsInterface
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHealthConditionBinding.inflate(inflater, container, false)
        binding.imgBackCond.setOnClickListener {
            findNavController().navigate(R.id.action_healthConditionFragment_to_mealPlanCategoriesFragment)
        }
        val activity  = activity as Context
        linearLayoutManager = LinearLayoutManager(activity)
        healthConditionAdapter = HealthConditionAdapter(activity)

        binding.progressCondition.visibility = View.VISIBLE
        binding.linearCondition.visibility = View.GONE
        showOPtions()
        setUsername()
        automation()
        return binding.root
    }

    private fun automation() {
        binding.editCondition.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                healthConditionAdapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun showOPtions() {
        healthConditionsInterface = HealthConditionsRetrofit.getRetrofit().create(HealthConditionsInterface::class.java)
        val call: Call<AllHealthConditions> = healthConditionsInterface.getConditions()
        call.enqueue(object: Callback<AllHealthConditions>{
            override fun onResponse(
                call: Call<AllHealthConditions>,
                response: Response<AllHealthConditions>
            ) {
                if (response.isSuccessful){
                    binding.progressCondition.visibility = View.GONE
                    binding.linearCondition.visibility = View.VISIBLE
                    val plan = arguments?.getString("MEALPLAN").toString()
                    if (response.body()!!.data.size > 0){
                        healthConditionAdapter.addAll(response.body()!!.data, plan)
                        binding.recyclerCondition.adapter = healthConditionAdapter
                        binding.recyclerCondition.layoutManager = linearLayoutManager
                    }
                    else{
                        Toast.makeText(activity, "No Data", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<AllHealthConditions>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun setUsername() {
        sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
        val username = sharedPreferences.getString("USERNAME", "")
        if (username != null){
            binding.txtConditionIntro.text = username.toString() + ", enter Current Health Condition"
        }

    }
}