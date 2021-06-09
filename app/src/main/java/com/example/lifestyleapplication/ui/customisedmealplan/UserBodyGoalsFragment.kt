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
import com.example.lifestyleapplication.databinding.FragmentUserBodyGoalsBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class UserBodyGoalsFragment : Fragment() {
    private lateinit var binding: FragmentUserBodyGoalsBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var general: generalinterface

    private var gain: String? = ""
    private var lose: String? = ""
    private var maintain: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBodyGoalsBinding.inflate(inflater, container, false)
        binding.imgBackBodyGoals.setOnClickListener {
            findNavController().navigate(R.id.action_userBodyGoalsFragment_to_userBodyTypeFragment)
        }
        binding.floatingBodyGoals.setOnClickListener {
            val plan: String = arguments?.getString("MEALPLAN").toString()
            val age: String = arguments?.getString("AGE").toString()
            val gender: String = arguments?.getString("GENDER").toString()
            val weight: String = arguments?.getString("WEIGHT").toString()
            val height = arguments?.getString("HEIGHT").toString()
            val type = arguments?.getString("BODYTYPE").toString()
            if (binding.optOne.isChecked){
                lose = binding.optOne.text.toString()
            }
            if (binding.optTwo.isChecked){
                gain = binding.optTwo.text.toString()
            }
            if (binding.optThree.isChecked){
                maintain = binding.optThree.text.toString()
            }

            general.sendCustomBodyGoals(lose!!, gain!!, maintain!!, type, height, weight, gender, plan, age)
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
                    binding.introBodyGoals.text = "What are your body goals " + snapshot.child("username").value.toString() + "?"
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