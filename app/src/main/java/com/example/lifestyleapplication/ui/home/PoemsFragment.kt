package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentPoemsBinding
import com.example.lifestyleapplication.ui.adapters.authorAdapter
import com.example.lifestyleapplication.ui.interfaces.authorInterface
import com.example.lifestyleapplication.ui.models.author
import com.example.lifestyleapplication.ui.retrofit.authorRetrofit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PoemsFragment : Fragment() {
    private lateinit var binding: FragmentPoemsBinding
    private lateinit var authorAdapter: authorAdapter
    private lateinit var author: authorInterface
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPoemsBinding.inflate(inflater, container, false)
        val animation: Animation = AnimationUtils.loadAnimation(activity, android.R.anim.slide_out_right)
        val activity = activity as Context
        authorAdapter = authorAdapter(activity)
        linearLayoutManager = LinearLayoutManager(activity)
        binding.recyclerAuthor.adapter = authorAdapter
        binding.recyclerAuthor.layoutManager = linearLayoutManager
        binding.backAuthor.setOnClickListener {
            findNavController().navigate(R.id.action_poemsFragment_to_homeFragment2)
        }
        binding.userNameAuthor.startAnimation(animation)
        binding.chooseText.startAnimation(animation)

        binding.progressPoems.visibility = View.VISIBLE
        binding.layoutPoems.visibility = View.GONE
        author = authorRetrofit.getRetrofit().create(authorInterface::class.java)
        val authorCall: Call<author> = author.getAuthors()
        authorCall.enqueue(object: Callback<author>{
            override fun onResponse(call: Call<author>, response: Response<author>) {
                if (response.isSuccessful){
                    binding.progressPoems.visibility = View.GONE
                    binding.layoutPoems.visibility = View.VISIBLE
                    sendData(response.body())
                }
            }

            override fun onFailure(call: Call<author>, t: Throwable) {
                Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
                binding.progressPoems.visibility = View.VISIBLE
                binding.layoutPoems.visibility = View.GONE
            }

        })
        setUsername()
        return binding.root
    }

    private fun sendData(body: author?) {
        authorAdapter.getData(body!!.author)
    }

    private fun setUsername() {
        sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
        val username = sharedPreferences.getString("USERNAME", "")
        if (username != null){
            binding.userNameAuthor.text = "Hello " + username.toString()
        }
    }

}