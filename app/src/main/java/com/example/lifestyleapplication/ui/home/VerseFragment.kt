package com.example.lifestyleapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentVerseBinding
import com.example.lifestyleapplication.ui.models.verse

class VerseFragment : Fragment() {
    private lateinit var binding: FragmentVerseBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVerseBinding.inflate(inflater, container, false)
        val animation: Animation = AnimationUtils.loadAnimation(activity, android.R.anim.slide_out_right)
        binding.backBibleVerse.setOnClickListener {
            findNavController().navigate(R.id.action_verseFragment_to_dailyVersesFragment)
        }
        binding.txtIntroVerse.startAnimation(animation)

        binding.progressVerse.visibility = View.VISIBLE
        binding.linearVerse.visibility = View.GONE

        val v = arguments?.getParcelable<verse>("VERSE")!!

        if (v != null){
            binding.progressVerse.visibility = View.GONE
            binding.linearVerse.visibility = View.VISIBLE

            binding.chapter.text = v.chapter
            binding.verse.text = v.verse
        }

        return binding.root
    }
}