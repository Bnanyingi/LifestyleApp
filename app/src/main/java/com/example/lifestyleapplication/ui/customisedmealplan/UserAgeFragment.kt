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
import com.example.lifestyleapplication.databinding.FragmentUserAgeBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserAgeFragment : Fragment() {
    private lateinit var binding: FragmentUserAgeBinding
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
        binding = FragmentUserAgeBinding.inflate(inflater, container, false)
        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_userAgeFragment_to_mealPlanCategoriesFragment)
        }
        binding.floatingAge.setOnClickListener {
            val age = binding.editAge.text.toString().trim()
            if (TextUtils.isEmpty(age)){
                binding.editAge.error = "Age Needed"
            }
            else{
                general.sendCustomAge(age, arguments?.getString("MEALPLAN").toString())
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
                    binding.introAge.text = "How old are you " + snapshot.child("username").value.toString() + "?"
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