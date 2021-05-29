package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentRemediesIllnessBinding
import com.example.lifestyleapplication.ui.adapters.IllnessAdapter
import com.example.lifestyleapplication.ui.interfaces.IllnessInterface
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.example.lifestyleapplication.ui.models.allIllness
import com.example.lifestyleapplication.ui.models.illness
import com.example.lifestyleapplication.ui.retrofit.IllnessRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemediesIllness : Fragment() {

    private lateinit var binding: FragmentRemediesIllnessBinding
    private lateinit var general: generalinterface
    private lateinit var illnessAdapter: IllnessAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var illnessInterface: IllnessInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRemediesIllnessBinding.inflate(inflater, container, false)
        binding.backRemedies.setOnClickListener {
            findNavController().navigate(R.id.action_remediesIllness_to_homeFragment2)
        }

        binding.progressIllness.visibility = View.VISIBLE
        binding.linearIllness.visibility = View.GONE
        linearLayoutManager = LinearLayoutManager(activity)
        var activity = activity as Context
        illnessAdapter = IllnessAdapter(activity)
        binding.recyclerIllness.adapter = illnessAdapter
        binding.recyclerIllness.layoutManager = linearLayoutManager


        illnessInterface = IllnessRetrofit.getRetrofit().create(IllnessInterface::class.java)
        val call: Call<allIllness> = illnessInterface.getIllnesses()
        call.enqueue(object: Callback<allIllness>{
            override fun onResponse(call: Call<allIllness>, response: Response<allIllness>) {
                if (response.isSuccessful){
                    binding.progressIllness.visibility = View.GONE
                    binding.linearIllness.visibility = View.VISIBLE
                    getIllnesses(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<allIllness>, t: Throwable) {
                binding.progressIllness.visibility = View.VISIBLE
                binding.linearIllness.visibility = View.GONE
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })

        binding.editIllness.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                illnessAdapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.nextRemedies.setOnClickListener {
            val ill = binding.editIllness.text.toString().trim()
            if (TextUtils.isEmpty(ill)){
                binding.editIllness.error = "Required"
            }
            else{
                general.getRemedy(ill)
            }
        }
        return binding.root
    }

    private fun getIllnesses(data: ArrayList<illness>) {
        illnessAdapter.addAll(data)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        general = context as generalinterface
    }
}