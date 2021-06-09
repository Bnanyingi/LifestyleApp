package com.example.lifestyleapplication.ui.customisedmealplan

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentUserCurrentWeightBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserCurrentWeightFragment : Fragment() {
    private lateinit var binding: FragmentUserCurrentWeightBinding
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
        binding = FragmentUserCurrentWeightBinding.inflate(inflater, container, false)
        binding.imgBackWeight.setOnClickListener {
            findNavController().navigate(R.id.action_userCurrentWeightFragment_to_userGenderFragment)
        }
        binding.floatingWeight.setOnClickListener {
            val plan: String = arguments?.getString("MEALPLAN").toString()
            val age: String = arguments?.getString("AGE").toString()
            val gender: String = arguments?.getString("GENDER").toString()
            val weight = binding.editWeight.text.toString().trim()
            if (TextUtils.isEmpty(weight)){
                binding.editWeight.error = "Weight Needed"
            }
            else{
                val id = binding.radioGroupWeight.checkedRadioButtonId
                when(id){
                    R.id.radioKg -> {
                        general.sendCustomWeight(weight, gender, plan, age)
                    }
                    R.id.radioPounds -> {
                        val res = weight.toInt() / 2.20462
                        general.sendCustomWeight(res.toString(), gender, plan, age)
                    }
                    else -> {
                        general.sendCustomWeight(weight, gender, plan, age)
                    }
                }
            }
        }
        setUsername()
        return binding.root
    }

    private fun setUsername() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users").child(firebaseAuth.uid!!)
        databaseReference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    binding.introWeight.text = "What is your current weight " + snapshot.child("username").value.toString() + "?"
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        general = context as generalinterface
    }
}