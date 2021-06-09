package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentHealthConditionBinding
import com.example.lifestyleapplication.ui.adapters.HealthConditionAdapter
import com.example.lifestyleapplication.ui.interfaces.HealthConditionsInterface
import com.example.lifestyleapplication.ui.models.AllHealthConditions
import com.example.lifestyleapplication.ui.retrofit.HealthConditionsRetrofit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HealthConditionFragment : Fragment() {
    private lateinit var binding: FragmentHealthConditionBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var healthConditionAdapter: HealthConditionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var healthConditionsInterface: HealthConditionsInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHealthConditionBinding.inflate(inflater, container, false)
        val activity  = activity as Context
        linearLayoutManager = LinearLayoutManager(activity)
        healthConditionAdapter = HealthConditionAdapter(activity)

        binding.progressCondition.visibility = View.VISIBLE
        binding.linearCondition.visibility = View.GONE
        showOPtions()
        setUsername()
        automation()
        return binding.root
    }

    private fun automation() {
        binding.editCondition.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                healthConditionAdapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun showOPtions() {
        healthConditionsInterface = HealthConditionsRetrofit.getRetrofit().create(HealthConditionsInterface::class.java)
        var call: Call<AllHealthConditions> = healthConditionsInterface.getConditions()
        call.enqueue(object: Callback<AllHealthConditions>{
            override fun onResponse(
                call: Call<AllHealthConditions>,
                response: Response<AllHealthConditions>
            ) {
                if (response.isSuccessful){
                    binding.progressCondition.visibility = View.GONE
                    binding.linearCondition.visibility = View.VISIBLE
                    val plan = arguments?.getString("MEALPLAN").toString()
                    healthConditionAdapter.addAll(response.body()!!.data, plan)
                    binding.recyclerCondition.adapter = healthConditionAdapter
                    binding.recyclerCondition.layoutManager = linearLayoutManager
                }
            }

            override fun onFailure(call: Call<AllHealthConditions>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun setUsername() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users").child(firebaseAuth.uid!!)
        databaseReference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    binding.txtConditionIntro.text = snapshot.child("username").value.toString() + ", enter Current Health Condition"
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