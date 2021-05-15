package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentPoemsTitlesBinding
import com.example.lifestyleapplication.ui.adapters.TitleAdapter
import com.example.lifestyleapplication.ui.interfaces.titlesInterface
import com.example.lifestyleapplication.ui.models.titles
import com.example.lifestyleapplication.ui.retrofit.titlesRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PoemsTitlesFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var titleAdapter: TitleAdapter
    private lateinit var binding: FragmentPoemsTitlesBinding
    private lateinit var titles: titlesInterface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPoemsTitlesBinding.inflate(inflater, container, false)
        binding.backTitles.setOnClickListener {
            findNavController().navigate(R.id.action_poemsTitlesFragment_to_poemsFragment)
        }
        binding.progressTitle.visibility = View.VISIBLE
        binding.linearTitle.visibility = View.GONE

        binding.txtTitleAuthor.text = arguments?.getString("Title")
        linearLayoutManager = LinearLayoutManager(activity)
        val activity = activity as Context
        titleAdapter = TitleAdapter(activity)

        binding.recyclerTitles.layoutManager = linearLayoutManager
        binding.recyclerTitles.adapter = titleAdapter

        titles = titlesRetrofit.getTitles().create(titlesInterface::class.java)
        val call: Call<List<titles>> = titles.getTitles(arguments?.getString("Title")!!)
        call.enqueue(object: Callback<List<titles>>{
            override fun onResponse(call: Call<List<titles>>, response: Response<List<titles>>) {
                if (response.isSuccessful){
                    binding.progressTitle.visibility = View.GONE
                    binding.linearTitle.visibility = View.VISIBLE
                    showTitles(response.body())
                }
            }

            override fun onFailure(call: Call<List<titles>>, t: Throwable) {
                binding.progressTitle.visibility = View.VISIBLE
                binding.linearTitle.visibility = View.GONE
            }

        })
        return binding.root
    }

    private fun showTitles(body: List<titles>?) {
        titleAdapter.getData(body!!, arguments?.getString("Title")!!)
    }

}