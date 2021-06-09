package com.example.lifestyleapplication.ui.customisedmealplan

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentUserGenderBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserGenderFragment : Fragment() {
    private lateinit var binding: FragmentUserGenderBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var firebaseAuth: FirebaseAuth
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
        binding = FragmentUserGenderBinding.inflate(inflater, container, false)
        binding.imgBackGender.setOnClickListener {
            findNavController().navigate(R.id.action_userGenderFragment_to_userAgeFragment)
        }
        binding.floatingGender.setOnClickListener {
            val plan: String = arguments?.getString("MEALPLAN").toString()
            val age: String = arguments?.getString("AGE").toString()
            val id = binding.radioGroup.checkedRadioButtonId
            when(id){
                R.id.radioMale -> {
                    general.sendCustomGender(binding.radioMale.text.toString(), plan, age)
                }
                R.id.radioFemale -> {
                    general.sendCustomGender(binding.radioFemale.text.toString(), plan, age)
                }
                else -> {

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
                    binding.introGender.text = "What is your gender " + snapshot.child("username").value.toString() + "?"
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