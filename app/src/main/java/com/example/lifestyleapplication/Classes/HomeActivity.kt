package com.example.lifestyleapplication.Classes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.example.lifestyleapplication.Fragments.DevotionalsFragment
import com.example.lifestyleapplication.Fragments.MainFragment
import com.example.lifestyleapplication.Fragments.MealPlanFragment
import com.example.lifestyleapplication.Model.FragmentClass
import com.example.lifestyleapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var userName: TextView
    private lateinit var imageView: ImageView
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    //fragments
    private var mainFragment: MainFragment? = null
    private var devotionalsFragment: DevotionalsFragment? = null
    private var mealPlanFragment: MealPlanFragment? = null

    //firebase Init
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    private var tags: ArrayList<String> = ArrayList()
    private var fragments: ArrayList<FragmentClass> = ArrayList()
    private var mCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()
        initialFragment()
        initDrawer()
        initNavigationView()
        initBottomView()
    }

    private fun init() {
        drawerLayout = findViewById(R.id.drawer)
        bottomNavigationView = findViewById(R.id.bottomView)
        navigationView = findViewById(R.id.navigation)
        toolbar = findViewById(R.id.toolbar)
        imageView = findViewById(R.id.imgUser)
        userName = findViewById(R.id.username)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("User").child(firebaseAuth.currentUser!!.uid)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.child("username").value.toString()
                userName.text = user
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun initialFragment() {
        if (mainFragment == null) {
            mainFragment = MainFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.frame, mainFragment!!, getString(R.string.home))
            fragmentTransaction.commit()
            tags.add(getString(R.string.home))
            fragments.add(FragmentClass(getString(R.string.home), mainFragment))
        } else {
            tags.remove(getString(R.string.home))
            tags.add(getString(R.string.home))
        }
        setVisibility(getString(R.string.home))
    }

    private fun initDrawer() {
        setSupportActionBar(toolbar)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    private fun initNavigationView() {
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){

            }
        }
    }

    private fun initBottomView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.homeBottom -> {
                    if (mainFragment == null) {
                        mainFragment = MainFragment()
                        fragmentTransaction.add(R.id.frame, mainFragment!!, getString(R.string.home))
                        fragmentTransaction.commit()
                        tags.add(getString(R.string.home))
                        fragments.add(FragmentClass(getString(R.string.home), mainFragment))
                    } else {
                        tags.remove(getString(R.string.home))
                        tags.add(getString(R.string.home))
                    }
                    setVisibility(getString(R.string.home))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.devotionsBottom -> {
                    if (devotionalsFragment == null) {
                        devotionalsFragment = DevotionalsFragment()
                        fragmentTransaction.add(R.id.frame, devotionalsFragment!!, getString(R.string.devotions))
                        fragmentTransaction.commit()
                        tags.add(getString(R.string.devotions))
                        fragments.add(FragmentClass(getString(R.string.devotions), devotionalsFragment))
                    } else {
                        tags.remove(getString(R.string.devotions))
                        tags.add(getString(R.string.devotions))
                    }
                    setVisibility(getString(R.string.devotions))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.mealBottom -> {
                    if (mealPlanFragment == null) {
                        mealPlanFragment = MealPlanFragment()
                        fragmentTransaction.add(R.id.frame, mealPlanFragment!!, getString(R.string.meal))
                        fragmentTransaction.commit()
                        tags.add(getString(R.string.meal))
                        fragments.add(FragmentClass(getString(R.string.meal), mealPlanFragment))
                    } else {
                        tags.remove(getString(R.string.meal))
                        tags.add(getString(R.string.meal))
                    }
                    setVisibility(getString(R.string.meal))
                    return@OnNavigationItemSelectedListener true
                }
                else -> return@OnNavigationItemSelectedListener false
            }
        })
    }

    private fun setVisibility(tag: String){
        if (tag == getString(R.string.home)) {
            showBottomView()
        }
        else if (tag == getString(R.string.devotions)){
            showBottomView()
        }
        else if (tag == getString(R.string.meal)){
            showBottomView()
        }
        for (i in tags.indices) {
            if (tag == fragments[i].tag) {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.show(fragments[i].fragment!!)
                fragmentTransaction.commit()
            } else {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.hide(fragments[i].fragment!!)
                fragmentTransaction.commit()
            }
        }
    }

    private fun showBottomView() {
        bottomNavigationView.visibility = View.VISIBLE
    }

    private fun hideBottom(){
        bottomNavigationView.visibility = View.GONE
    }

    override fun onBackPressed() {
        val total = tags.size
        if (total > 1) {
            val top = tags[total - 1]
            val bottom = tags[total - 2]
            setVisibility(bottom)
            tags.remove(top)
            mCount = 1
        } else if (total == 1) {
            val top = tags[total - 1]
            if (top == "Home") {
                Toast.makeText(this, "End", Toast.LENGTH_LONG).show()
                mCount++
            } else {
                mCount++
            }
        }
        if (mCount >= 2) {
            super.onBackPressed()
        }
    }
}