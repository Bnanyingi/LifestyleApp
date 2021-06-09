package com.example.lifestyleapplication.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentVerseBinding
import com.example.lifestyleapplication.ui.interfaces.MoodVersesInterface
import com.example.lifestyleapplication.ui.models.allMoodVerses
import com.example.lifestyleapplication.ui.models.moodVerse
import com.example.lifestyleapplication.ui.models.verse
import com.example.lifestyleapplication.ui.retrofit.MoodVersesRetrofit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class VerseFragment : Fragment() {
    private lateinit var binding: FragmentVerseBinding
    private lateinit var moodVersesInterface: MoodVersesInterface

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseDatabase: FirebaseDatabase


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

        getVerseData()
        setUsername()

        return binding.root
    }

    private fun getVerseData() {
        val v = arguments?.getString("MOOD")
        moodVersesInterface = MoodVersesRetrofit.getRetrofit().create(MoodVersesInterface::class.java)
        val call: Call<allMoodVerses> = moodVersesInterface.getVerse(v.toString())
        call.enqueue(object: Callback<allMoodVerses>{
            override fun onResponse(call: Call<allMoodVerses>, response: Response<allMoodVerses>) {
                if (response.isSuccessful){
                    binding.progressVerse.visibility = View.GONE
                    binding.linearVerse.visibility = View.VISIBLE
                    getData(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<allMoodVerses>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getData(data: ArrayList<moodVerse>) {
        var vrse = ""
        var chpter = ""
        for (i in data.indices){

            vrse = data[i].verse!!
            chpter = data[i].chapter!!
        }
        binding.verse.text = vrse
        binding.chapter.text = chpter
    }

    private fun setUsername() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users").child(firebaseAuth.uid!!)
        databaseReference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    binding.txtIntroVerse.text = snapshot.child("username").value.toString() + " , this is your Bible Verse of the day"
                }
                else{
                    Toast.makeText(activity, "No data", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, error.message.toString(), Toast.LENGTH_LONG).show()
            }

        })

    }
}