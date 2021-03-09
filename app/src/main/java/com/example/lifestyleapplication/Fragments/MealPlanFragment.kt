package com.example.lifestyleapplication.Fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifestyleapplication.Adapters.MealPlanCategoryAdapter
import com.example.lifestyleapplication.Model.MealCategory
import com.example.lifestyleapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MealPlanFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mealPlanCategoryAdapter: MealPlanCategoryAdapter

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    private val arrayList: ArrayList<MealCategory> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_meal_plan, container, false)
        recyclerView = view.findViewById(R.id.mealPlanCat)
        linearLayoutManager = LinearLayoutManager(activity)
        val context = activity as Context
        mealPlanCategoryAdapter = MealPlanCategoryAdapter(context, arrayList)
        recyclerView.adapter = mealPlanCategoryAdapter
        recyclerView.layoutManager = linearLayoutManager

        firebaseAuth= FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()

        getMealCategories()
        return view
    }

    private fun getMealCategories() {
        val userId = firebaseAuth.currentUser

        databaseReference = firebaseDatabase.reference.child("mealplans")
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (dataSnap: DataSnapshot in snapshot.children){
                        val mealCategory: MealCategory = MealCategory()
                        mealCategory.name = dataSnap.child("name").value.toString()
                        mealCategory.calories = dataSnap.child("calories").value.toString()
                        mealCategory.days = dataSnap.child("days").value.toString()
                        mealCategory.img = dataSnap.child("img").value.toString()

                        arrayList.add(mealCategory)

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}