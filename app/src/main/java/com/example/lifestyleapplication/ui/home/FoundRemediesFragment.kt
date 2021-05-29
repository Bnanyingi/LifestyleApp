package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentFoundRemediesBinding
import com.example.lifestyleapplication.ui.adapters.RemediesAdapter
import com.example.lifestyleapplication.ui.interfaces.RemediesInterface
import com.example.lifestyleapplication.ui.models.allRemedies
import com.example.lifestyleapplication.ui.models.remedy
import com.example.lifestyleapplication.ui.models.user
import com.example.lifestyleapplication.ui.retrofit.RemediesRetrofit
import retrofit2.Call
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import retrofit2.Callback
import retrofit2.Response

class FoundRemediesFragment : Fragment() {

    private lateinit var binding: FragmentFoundRemediesBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var remediesAdapter: RemediesAdapter

    private lateinit var remediesInterface: RemediesInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFoundRemediesBinding.inflate(inflater, container, false)
        binding.backFoundRemedies.setOnClickListener {
            findNavController().navigate(R.id.action_foundRemediesFragment_to_remediesIllness)
        }
        binding.remedyName.text = arguments?.getString("ILLNESS") + " Remedies"

        binding.progressFound.visibility = View.VISIBLE
        binding.recyclerFoundRemedy.visibility = View.GONE
        val activity = activity as Context
        remediesAdapter = RemediesAdapter(activity)
        linearLayoutManager = LinearLayoutManager(activity)
        binding.recyclerFoundRemedy.adapter = remediesAdapter
        binding.recyclerFoundRemedy.layoutManager = linearLayoutManager
        getRemedies()
        return binding.root
    }

    private fun getRemedies() {
        val string: String = arguments?.getString("ILLNESS").toString()
        remediesInterface = RemediesRetrofit.getRetrofit().create(RemediesInterface::class.java)
        val call: Call<allRemedies> = remediesInterface.getData(string)
        call.enqueue(object : Callback<allRemedies>{
            override fun onResponse(call: Call<allRemedies>, response: Response<allRemedies>) {
                if (response.isSuccessful){
                    binding.progressFound.visibility = View.GONE
                    binding.recyclerFoundRemedy.visibility = View.VISIBLE
                    getData(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<allRemedies>, t: Throwable) {
                binding.progressFound.visibility = View.VISIBLE
                binding.recyclerFoundRemedy.visibility = View.GONE
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun getData(data: ArrayList<remedy>) {
        remediesAdapter.addAll(data)
    }
}