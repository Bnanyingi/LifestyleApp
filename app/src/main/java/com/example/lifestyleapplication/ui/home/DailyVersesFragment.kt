package com.example.lifestyleapplication.ui.home

import android.content.Context
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
import com.example.lifestyleapplication.databinding.FragmentDailyVersesBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.example.lifestyleapplication.ui.models.verse
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList


class DailyVersesFragment : Fragment() {
    private lateinit var binding: FragmentDailyVersesBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var general: generalinterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDailyVersesBinding.inflate(inflater, container, false)
        val animation: Animation = AnimationUtils.loadAnimation(activity, android.R.anim.slide_out_right)
        binding.backVerse.setOnClickListener {
            findNavController().navigate(R.id.action_dailyVersesFragment_to_homeFragment2)
        }

        //firebase
        firebaseDatabase = FirebaseDatabase.getInstance()

        binding.progressVerses.visibility = View.VISIBLE
        binding.linVerse.visibility = View.GONE

        binding.relNext.setOnClickListener {
            val id = binding.radioGroupMood.checkedRadioButtonId
            when(id){
                R.id.radioHappy -> {
                    databaseReference = firebaseDatabase.reference.child("bibleverses").child("thankful")
                    databaseReference.addValueEventListener(object: ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.exists()){
                                val list: ArrayList<verse> = ArrayList()
                                for(dataSnapshot in snapshot.children){
                                    val verse = verse()
                                    verse.verse = dataSnapshot.child("verse").value.toString()
                                    verse.chapter = dataSnapshot.child("chapter").value.toString()

                                    list.add(verse)
                                }
                                val random = Random().nextInt(list.size - 1)
                                Toast.makeText(activity, "found", Toast.LENGTH_LONG).show()
                                general.sendVerse(list[1])
                            }
                            else{
                                Toast.makeText(activity, "No Data", Toast.LENGTH_LONG).show()
                            }

                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(activity, "Not Successful", Toast.LENGTH_LONG).show()
                        }

                    })
                }
                R.id.radioSad -> {
                    databaseReference = firebaseDatabase.reference.child("bibleverses").child("stressed")
                    databaseReference.addValueEventListener(object: ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val list: ArrayList<verse> = ArrayList()
                            for(dataSnapshot in snapshot.children){
                                val verse = verse()
                                verse.verse = dataSnapshot.child("verse").value.toString()
                                verse.chapter = dataSnapshot.child("chapter").value.toString()

                                list.add(verse)
                            }
                            val random = Random().nextInt(list.size - 1)
                            general.sendVerse(list[0])
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(activity, "Not Successful", Toast.LENGTH_LONG).show()
                        }

                    })
                }
                R.id.radioNervous -> {
                    databaseReference = firebaseDatabase.reference.child("biblical").child("skeptical")
                    databaseReference.addValueEventListener(object: ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val list: ArrayList<verse> = ArrayList()
                            for(dataSnapshot in snapshot.children){
                                val verse = verse()
                                verse.verse = dataSnapshot.child("verse").value.toString()
                                verse.chapter = dataSnapshot.child("chapter").value.toString()

                                list.add(verse)
                            }
                            val random = Random().nextInt(list.size - 1)
                            general.sendVerse(list[0])

                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(activity, "Not Successful", Toast.LENGTH_LONG).show()
                        }

                    })
                }
                R.id.radioFarFromGed -> {
                    val list: ArrayList<verse> = ArrayList()
                    databaseReference = firebaseDatabase.reference.child("bibleverses").child("farFromGod")
                    databaseReference.addValueEventListener(object: ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.exists()){
                                for(dataSnapshot in snapshot.children){
                                    val verse = verse()
                                    verse.verse = dataSnapshot.child("verse").value.toString()
                                    verse.chapter = dataSnapshot.child("chapter").value.toString()

                                    list.add(verse)
                                }
                                val random = Random().nextInt(list.size - 1)
                                general.sendVerse(list[0])

                            }
                            else{
                                Toast.makeText(activity, "No Data", Toast.LENGTH_LONG).show()
                            }

                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(activity, "error", Toast.LENGTH_LONG).show()
                        }

                    })
                }
                else -> {

                }
            }
        }
        binding.txtIntro.startAnimation(animation)
        setUsername()
        return binding.root
    }

    private fun setUsername() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users").child(firebaseAuth.uid!!)
        databaseReference.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    binding.progressVerses.visibility = View.GONE
                    binding.linVerse.visibility = View.VISIBLE
                    binding.txtIntro.text = snapshot.child("username").value.toString() + ", How are you feeling today?"
                }
                else{
                    Toast.makeText(activity, "No data", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                binding.progressVerses.visibility = View.VISIBLE
                binding.linVerse.visibility = View.GONE
                Toast.makeText(activity, error.message.toString(), Toast.LENGTH_LONG).show()
            }

        })

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        general = context as generalinterface
    }
}