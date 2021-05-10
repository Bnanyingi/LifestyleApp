package com.example.lifestyleapplication.ui.quote

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.lifestyleapplication.databinding.FragmentQuoteBinding
import com.example.lifestyleapplication.utils.Status


class QuoteFragment : Fragment() {

    private lateinit var binding: FragmentQuoteBinding
    private lateinit var viewModel: QuotesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuoteBinding.inflate(inflater, container, false)


        viewModel = ViewModelProviders.of(this).get(QuotesViewModel::class.java)
        viewModel.quote.observe(viewLifecycleOwner, Observer {

            when(it.status){
                Status.LOADING ->{
                    binding.quoteProgresBar.visibility = View.VISIBLE
                }
                Status.SUCCESS ->{
                    binding.quoteProgresBar.visibility = View.GONE
                    binding.quoteContainer.visibility = View.VISIBLE
                    binding.quote.text = "\"${it.data?.get(0)?.q}\""
                    binding.author.text = "By: ${it.data?.get(0)?.a}"
                }
                Status.ERROR ->{
                    binding.quoteProgresBar.visibility = View.GONE
                    binding.quoteError.visibility = View.VISIBLE
                    binding.quoteError.text = it.message
                }
            }
        })
        return binding.root
    }

}