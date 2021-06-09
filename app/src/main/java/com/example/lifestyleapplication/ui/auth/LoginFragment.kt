package com.example.lifestyleapplication.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentLoginBinding
import com.example.lifestyleapplication.ui.interfaces.LoginInterface
import com.example.lifestyleapplication.ui.models.allUser
import com.example.lifestyleapplication.ui.models.user
import com.example.lifestyleapplication.ui.retrofit.LoginRetrofit
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginInterface: LoginInterface
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        //Login
        binding.loginBtn.setOnClickListener {
            authenticateUser()
        }

        //navigate to signup
        binding.signupTV.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        return binding.root
    }

    private fun authenticateUser() {
        val email = binding.emailLoginET.text.toString()
        val password = binding.passwordLoginET.text.toString()

        if (validateInput(email, binding.emailLoginInputLayout) && validateInput(
                password,
                binding.passwordLoginInputLayout
            ) && validateEmail(email, binding.emailLoginInputLayout)
        ) {
            loginInterface = LoginRetrofit.getRetrofit().create(LoginInterface::class.java)
            val call: Call<allUser> = loginInterface.login(email, password)
            call.enqueue(object: Callback<allUser>{
                override fun onResponse(call: Call<allUser>, response: Response<allUser>) {
                    if (response.isSuccessful){
                        if (response.body()!!.message == "Login Successful"){
                            setPreferences(response.body()!!.data)
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
                        }
                        else{
                            Toast.makeText(activity, response.body()!!.message.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                }

                override fun onFailure(call: Call<allUser>, t: Throwable) {
                    Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
                }

            })
        }

    }

    private fun setPreferences(data: ArrayList<user>) {
        sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("ID", data[0].id.toString())
        editor.putString("USERNAME", data[0].username)
        editor.putString("EMAIL", data[0].email)
        editor.putString("AGE", data[0].age)
        editor.putString("CITY", data[0].city)
        editor.putString("STATE", data[0].state)
        editor.putString("COUNTRY", data[0].country)
        editor.putString("DIET", data[0].diet)
        editor.putString("CONDITION", data[0].healthcondition)
        editor.putString("DISABILITY", data[0].disability)
        editor.putString("EATTIMES", data[0].timesyoueat)
        editor.putString("MEALTAKEN", data[0].meal)
        editor.putString("GENDER", data[0].gender)
        editor.apply()

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
}