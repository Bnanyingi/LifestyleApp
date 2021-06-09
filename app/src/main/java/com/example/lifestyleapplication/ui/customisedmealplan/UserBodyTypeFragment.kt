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
import com.example.lifestyleapplication.databinding.FragmentUserBodyTypeBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserBodyTypeFragment : Fragment() {
    private lateinit var binding: FragmentUserBodyTypeBinding
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
        binding = FragmentUserBodyTypeBinding.inflate(inflater, container, false)
        binding.imgBackBodyType.setOnClickListener {
            findNavController().navigate(R.id.action_userBodyTypeFragment_to_userHeightFragment)
        }
        binding.floatingBodyType.setOnClickListener {
            val plan: String = arguments?.getString("MEALPLAN").toString()
            val age: String = arguments?.getString("AGE").toString()
            val gender: String = arguments?.getString("GENDER").toString()
            val weight: String = arguments?.getString("WEIGHT").toString()
            val height = arguments?.getString("HEIGHT").toString()

            val id = binding.radioBodyTypes.checkedRadioButtonId
            when(id){
                R.id.radioEndomorph -> {
                    general.sendCustomBodyType(binding.radioEndomorph.text.toString(), height, weight, gender, plan, age)
                }
                R.id.radioMesomorph -> {
                    general.sendCustomBodyType(binding.radioMesomorph.text.toString(), height, weight, gender, plan, age)
                }
                R.id.radioEctomorph -> {
                    general.sendCustomBodyType(binding.radioEctomorph.text.toString(), height, weight, gender, plan, age)
                }
                else -> {
                    general.sendCustomHeight(height, weight, gender, plan, age)
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
                    binding.introBodyType.text = "What is your current body type " + snapshot.child("username").value.toString() + "?"
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