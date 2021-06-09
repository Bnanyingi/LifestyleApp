package com.example.lifestyleapplication.ui.home

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
import com.example.lifestyleapplication.databinding.FragmentMealPlanDaysBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.example.lifestyleapplication.ui.models.day
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MealPlanDaysFragment : Fragment() {
    private lateinit var binding: FragmentMealPlanDaysBinding
    private lateinit var general: generalinterface
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealPlanDaysBinding.inflate(inflater, container, false)
        binding.floatingDays.setOnClickListener {
            var days = binding.editMealDays.text.toString().trim()
            if (TextUtils.isEmpty(days)){
                binding.editMealDays.error = "Enter Number of Days"
            }
            else{
                general.sendDays(days, arguments?.getString("MEALPLAN").toString(), arguments?.getString("CONDITION").toString())
            }
        }

        binding.imgPLanDays.setOnClickListener {
            findNavController().navigate(R.id.action_mealPlanDaysFragment_to_healthConditionFragment)
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
                    binding.introPlan.text = "You want a meal plan for how many days" + snapshot.child("username").value.toString() + "?"
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