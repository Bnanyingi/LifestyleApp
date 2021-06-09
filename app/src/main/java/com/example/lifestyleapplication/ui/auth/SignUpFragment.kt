package com.example.lifestyleapplication.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentSignUpBinding
import com.example.lifestyleapplication.ui.interfaces.SignUpInterface
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.example.lifestyleapplication.ui.models.allRegister
import com.example.lifestyleapplication.ui.retrofit.RegisterRetrofit
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var signUpInterface: SignUpInterface
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var general: generalinterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.registerBtn.setOnClickListener {
            registerUser()
        }
        //navigate to sign in
        binding.signInTV.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        return binding.root
    }

    private fun registerUser() {
        val username = binding.usernameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (validateInput(username, binding.usernameInputLayout) && validateInput(
                email,
                binding.emailInputLayout
            )
            && validateInput(password, binding.passwordInputLayout) && validateEmail(
                email,
                binding.emailInputLayout
            )
        ) {
            signUpInterface = RegisterRetrofit.getRetrofit().create(SignUpInterface::class.java)
            val call: Call<allRegister> = signUpInterface.register(username, email, password)
            call.enqueue(object: Callback<allRegister>{
                override fun onResponse(call: Call<allRegister>, response: Response<allRegister>) {
                    if (response.isSuccessful){
                        val id = response.body()!!.data[0].id
                        val us = response.body()!!.data[0].username
                        val em = response.body()!!.data[0].email

                        general.sendUser(id!!, us!!, em!!)
                    }
                }

                override fun onFailure(call: Call<allRegister>, t: Throwable) {
                    Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
                }

            })
        }
    }

    private fun validateInput(inputText: String, textInputLayout: TextInputLayout): Boolean {
        return if (inputText.length <= 5) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = "* Minimum 6 characters allowed"
            false
        } else {
            textInputLayout.isErrorEnabled = false
            textInputLayout.error = null
            true
        }
    }

    private fun validateEmail(email: String, textInputLayout: TextInputLayout): Boolean {
        return if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = "* Enter valid email please"
            false
        } else {
            textInputLayout.isErrorEnabled = false
            textInputLayout.error = null
            true
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        general = context as generalinterface
    }
}