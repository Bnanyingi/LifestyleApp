package com.example.lifestyleapplication.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.lifestyleapplication.Constants.ProjectConstants
import com.example.lifestyleapplication.Interfaces.ProjectInterface
import com.example.lifestyleapplication.R
import org.json.JSONException

class MainFragment : Fragment() {

    private lateinit var quote: TextView
    private lateinit var authorText: TextView
    private lateinit var verse: TextView
    private lateinit var referenceText: TextView
    private lateinit var projectInterface: ProjectInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_main, container, false)

        quote = view.findViewById(R.id.quote)
        authorText = view.findViewById(R.id.author)
        verse = view.findViewById(R.id.verse)
        referenceText = view.findViewById(R.id.reference)
        getQuotes()
        getVerses()
        return view
    }

    private fun getVerses() {
        val requestQueue = Volley.newRequestQueue(activity)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, ProjectConstants.VERSE_URL, null,
            { response ->
                try {
                    val jsonObject = response.getJSONObject("verse")
                    val jsonObject1 = jsonObject.getJSONObject("details")
                    val text = jsonObject1.getString("text")
                    val reference = jsonObject1.getString("reference")
                    val version = jsonObject1.getString("version")
                    val verseUrl = jsonObject1.getString("verseurl")

                    //assign
                    verse.text = text
                    referenceText.text = reference
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { error ->
            Toast.makeText(
                context,
                "Error is : " + error.message.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }

        requestQueue.add(jsonObjectRequest)
    }

    private fun getQuotes() {
        //fetching data from api using volley library
        val requestQueue2 = Volley.newRequestQueue(activity)

        val jsonObjectRequest2 = JsonObjectRequest(
            Request.Method.GET, ProjectConstants.QUOTE_URL, null,
            { response ->
                try {
                    val qt = response.getString("content")
                    val author = response.getString("author")
                    quote.text = qt
                    authorText.text = author
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { error ->
            Log.d("new", error.message)
            Toast.makeText(activity, "Error is : " + error.message.toString(), Toast.LENGTH_SHORT)
                .show()
        }
        requestQueue2.add(jsonObjectRequest2)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //projectInterface = context as ProjectInterface
    }
}