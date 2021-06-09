package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentQuotesBinding
import com.example.lifestyleapplication.ui.interfaces.quotesInterface
import com.example.lifestyleapplication.ui.models.allQuotes
import com.example.lifestyleapplication.ui.retrofit.quotesRetrofit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class QuotesFragment : Fragment() {
    private lateinit var binding: FragmentQuotesBinding
    private lateinit var quotes: quotesInterface
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuotesBinding.inflate(inflater, container, false)
        val animation: Animation = AnimationUtils.loadAnimation(activity, android.R.anim.slide_out_right)
        binding.backQuote.setOnClickListener {
            findNavController().navigate(R.id.action_quotesFragment_to_homeFragment2)
        }
        binding.progressQuotes.visibility = View.VISIBLE
        binding.layoutQuote.visibility = View.GONE
        quotes = quotesRetrofit.getQuote().create(quotesInterface::class.java)
        val quotesCall: Call<allQuotes> = quotes.getQuotes()
        quotesCall.enqueue(object: Callback<allQuotes>{
            override fun onResponse(call: Call<allQuotes>, response: Response<allQuotes>) {
                if (response.isSuccessful){
                    binding.progressQuotes.visibility = View.GONE
                    binding.layoutQuote.visibility = View.VISIBLE

                    binding.quote.text = response.body()!!.quote!!.body
                    binding.author.text = response.body()!!.quote!!.author
                }
            }

            override fun onFailure(call: Call<allQuotes>, t: Throwable) {
                Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
                binding.progressQuotes.visibility = View.VISIBLE
                binding.layoutQuote.visibility = View.GONE
            }

        })

        binding.backQuote.startAnimation(animation)
        binding.author.startAnimation(animation)
        binding.imgQ.startAnimation(animation)
        binding.txtIntroQuote.startAnimation(animation)

        setUsername()
        return binding.root
    }

    private fun setUsername() {
        sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
        val username = sharedPreferences.getString("USERNAME", "")
        if (username != null){
            binding.userNameQuote.text = "Hello " + username.toString()
        }
    }

}