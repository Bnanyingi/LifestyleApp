package com.example.lifestyleapplication.ui.home

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
import com.example.lifestyleapplication.databinding.FragmentDevotionalsBinding
import com.example.lifestyleapplication.ui.adapters.DevotionalsAdapter
import com.example.lifestyleapplication.ui.interfaces.devotionalTopicsInterface
import com.example.lifestyleapplication.ui.models.allDevotionalTopics
import com.example.lifestyleapplication.ui.models.devotionalTopics
import com.example.lifestyleapplication.ui.models.devotions
import com.example.lifestyleapplication.ui.retrofit.devotionalTopicsRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DevotionalsFragment : Fragment() {

    private lateinit var binding: FragmentDevotionalsBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var devotionalsAdapter: DevotionalsAdapter
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var devotionalTopics: devotionalTopicsInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDevotionalsBinding.inflate(inflater, container, false)
        binding.imgBackDevotionals.setOnClickListener {
            findNavController().navigate(R.id.action_devotionalsFragment_to_homeFragment2)
        }

        val activity = activity as Context
        devotionalsAdapter = DevotionalsAdapter(activity)
        linearLayoutManager = LinearLayoutManager(activity)
        binding.recyclerDevotionals.adapter = devotionalsAdapter
        binding.recyclerDevotionals.layoutManager = linearLayoutManager
        binding.progressDevTopics.visibility = View.VISIBLE
        binding.linearDevTopics.visibility = View.GONE

        devotionalTopics = devotionalTopicsRetrofit.getRetrofit().create(devotionalTopicsInterface::class.java)
        val call: Call<allDevotionalTopics> = devotionalTopics.getTopics()
        call.enqueue(object: Callback<allDevotionalTopics>{
            override fun onResponse(
                call: Call<allDevotionalTopics>,
                response: Response<allDevotionalTopics>
            ) {
                if (response.isSuccessful){
                    binding.progressDevTopics.visibility = View.GONE
                    binding.linearDevTopics.visibility = View.VISIBLE
                    setData(response.body())
                }

            }

            override fun onFailure(call: Call<allDevotionalTopics>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
                binding.progressDevTopics.visibility = View.VISIBLE
                binding.linearDevTopics.visibility = View.GONE
            }
        })
        setUsername()
        return binding.root
    }

    private fun setUsername() {
        sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
        val username = sharedPreferences.getString("USERNAME", "")
        if (username != null){
            binding.introDevotionals.text = username.toString() + ", which Bible topic would you want to study on today?"
        }
    }

    private fun setData(body: allDevotionalTopics?) {
        devotionalsAdapter.addAll(body!!.data)

    }

}