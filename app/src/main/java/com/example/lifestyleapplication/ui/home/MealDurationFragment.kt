package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentMealDurationBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MealDurationFragment : Fragment() {
    private lateinit var binding: FragmentMealDurationBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var general: generalinterface
    private var brk: String? = ""
    private var lnch: String = ""
    private var dnr: String = ""
    private var dsrt: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealDurationBinding.inflate(inflater, container, false)
        binding.floatingDuration.setOnClickListener {
            if (binding.breakfast.isChecked){
                brk = binding.breakfast.text.toString()
            }
            if (binding.Lunch.isChecked){
                lnch = binding.Lunch.text.toString()
            }
            if (binding.Dinner.isChecked){
                dnr = binding.Dinner.text.toString()
            }
            if (binding.Dessert.isChecked){
                dsrt = binding.Dessert.text.toString()
            }

            general.sendDurations(brk!!, lnch, dnr, dsrt, arguments?.getString("DAY").toString(), arguments?.getString("MEALPLAN").toString(), arguments?.getString("CONDITION").toString())

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
                    binding.introDuration.text = "You want a meal plan for how many days" + snapshot.child("username").value.toString() + "?"
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