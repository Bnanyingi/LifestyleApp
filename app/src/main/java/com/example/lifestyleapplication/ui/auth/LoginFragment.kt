package com.example.lifestyleapplication.ui.auth

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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()

        //Login
        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
            //authenticateUser()
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
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), OnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(context, "Successfully Logged In", Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
                    }else{
                        Toast.makeText(context, "Login fail", Toast.LENGTH_LONG).show()
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