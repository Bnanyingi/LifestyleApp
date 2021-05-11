package com.example.lifestyleapplication.ui.auth

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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()

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
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), OnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(context, "Successfully Registered", Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_signUpFragment_to_profileFragment)
                    }else{
                        Toast.makeText(context, "Registration failed!", Toast.LENGTH_LONG).show()
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
}