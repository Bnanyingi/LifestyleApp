package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentViewPoemBinding
import com.example.lifestyleapplication.ui.adapters.LinesAdapter
import com.example.lifestyleapplication.ui.models.poem

class ViewPoemFragment : Fragment() {

    private lateinit var binding: FragmentViewPoemBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var linesAdapter: LinesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPoemBinding.inflate(inflater, container, false)
        binding.imgBackPoem.setOnClickListener {
            findNavController().navigate(R.id.action_viewPoemFragment_to_selectedTitleFragment)
        }

        linearLayoutManager = LinearLayoutManager(activity)
        var activity = activity as Context
        linesAdapter = LinesAdapter(activity)

        binding.recyclerLines.adapter = linesAdapter
        binding.recyclerLines.layoutManager = linearLayoutManager

        val poet: poem = arguments?.getParcelable<poem>("POEM")!!
        binding.titlePoem.text = poet.title
        binding.poet.text = poet.author
        poet.lines?.let { linesAdapter.addAll(it) }
        return binding.root
    }
}