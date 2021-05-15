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
import com.example.lifestyleapplication.databinding.FragmentSelectedTitleBinding
import com.example.lifestyleapplication.ui.adapters.SelectedTitleAdapter
import com.example.lifestyleapplication.ui.interfaces.SelectedTitleInterface
import com.example.lifestyleapplication.ui.models.poem
import com.example.lifestyleapplication.ui.retrofit.SelectedTitleRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectedTitleFragment : Fragment() {

    private lateinit var binding: FragmentSelectedTitleBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var selectedTitleAdapter: SelectedTitleAdapter
    private lateinit var selectedTitleInterface: SelectedTitleInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSelectedTitleBinding.inflate(inflater, container, false)
        binding.selectTitlesBack.setOnClickListener {
            findNavController().navigate(R.id.action_selectedTitleFragment_to_poemsTitlesFragment)
        }

        linearLayoutManager = LinearLayoutManager(activity)
        val activity = activity as Context
        selectedTitleAdapter = SelectedTitleAdapter(activity)
        binding.txtTitleSelected.text = arguments?.getString("SelectedTitle")
        binding.recyclerTitlesSelect.adapter = selectedTitleAdapter
        binding.recyclerTitlesSelect.layoutManager = linearLayoutManager

        binding.progressTitleSelected.visibility = View.VISIBLE
        binding.linearTitleSelected.visibility = View.GONE

        val string = arguments?.getString("SelectedAuthor")!!.split(" ")
        val authString = string[string.size - 1]

        selectedTitleInterface = SelectedTitleRetrofit.getRetrofit().create(SelectedTitleInterface::class.java)
        val call: Call<List<poem>> = selectedTitleInterface.getSelectedTitle(authString, arguments?.getString("SelectedTitle")!!)
        call.enqueue(object: Callback<List<poem>>{
            override fun onResponse(call: Call<List<poem>>, response: Response<List<poem>>) {
                if (response.isSuccessful){
                    binding.progressTitleSelected.visibility = View.GONE
                    binding.linearTitleSelected.visibility = View.VISIBLE
                    getData(response.body())
                }
            }

            override fun onFailure(call: Call<List<poem>>, t: Throwable) {
                binding.progressTitleSelected.visibility = View.VISIBLE
                binding.linearTitleSelected.visibility = View.GONE
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })
        return binding.root
    }

    private fun getData(body: List<poem>?) {
        selectedTitleAdapter.addAll(body!!)
    }

}