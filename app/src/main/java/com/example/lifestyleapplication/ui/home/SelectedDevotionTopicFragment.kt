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
import com.example.lifestyleapplication.databinding.FragmentSelectedDevotionTopicBinding
import com.example.lifestyleapplication.ui.adapters.SelectedDevotionTopicAdapter
import com.example.lifestyleapplication.ui.interfaces.devotionalTopicTitlesInterface
import com.example.lifestyleapplication.ui.models.allDevotionalTopicTitles
import com.example.lifestyleapplication.ui.models.devotionalTopicTitles
import com.example.lifestyleapplication.ui.retrofit.devotionalTopicTitlesRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectedDevotionTopicFragment : Fragment() {
    private lateinit var binding: FragmentSelectedDevotionTopicBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var selectedDevotionTopicAdapter: SelectedDevotionTopicAdapter
    private lateinit var devotionalTopicTitles: devotionalTopicTitlesInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSelectedDevotionTopicBinding.inflate(inflater, container, false)
        linearLayoutManager = LinearLayoutManager(activity)
        val activity = activity as Context
        selectedDevotionTopicAdapter = SelectedDevotionTopicAdapter(activity)
        binding.devBack.setOnClickListener {
            findNavController().navigate(R.id.action_selectedDevotionTopicFragment_to_devotionalsFragment)
        }
        binding.devTopic.text = arguments?.getString("TOPIC")
        binding.recyclerSelectedTopic.adapter = selectedDevotionTopicAdapter
        binding.recyclerSelectedTopic.layoutManager = linearLayoutManager

        binding.progressDevotionTitle.visibility = View.VISIBLE
        binding.recyclerSelectedTopic.visibility = View.GONE

        val string: String = arguments?.getString("TOPIC").toString()
        devotionalTopicTitles = devotionalTopicTitlesRetrofit.getRetrofit().create(devotionalTopicTitlesInterface::class.java)
        val call: Call<allDevotionalTopicTitles> = devotionalTopicTitles.getTitles(string)
        call.enqueue(object: Callback<allDevotionalTopicTitles>{
            override fun onResponse(
                call: Call<allDevotionalTopicTitles>,
                response: Response<allDevotionalTopicTitles>
            ) {
                if (response.isSuccessful){
                    binding.progressDevotionTitle.visibility = View.GONE
                    binding.recyclerSelectedTopic.visibility = View.VISIBLE
                    getData(response.body())
                }
            }

            override fun onFailure(call: Call<allDevotionalTopicTitles>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
                binding.progressDevotionTitle.visibility = View.VISIBLE
                binding.recyclerSelectedTopic.visibility = View.GONE
            }

        })
        return binding.root
    }

    private fun getData(body: allDevotionalTopicTitles?) {
        selectedDevotionTopicAdapter.addAll(body!!.data)

    }
}