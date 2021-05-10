package com.example.lifestyleapplication.ui.quote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifestyleapplication.data.remote.quote.QuotesClient
import com.example.lifestyleapplication.data.remote.quote.models.Quote
import com.example.lifestyleapplication.utils.Resource
import kotlinx.coroutines.launch

class QuotesViewModel : ViewModel() {

    private val _quote = MutableLiveData<Resource<Quote>>()
    val quote = _quote

    init {
        fetchQuote()
    }

    private fun fetchQuote() {
        _quote.postValue(Resource.loading(null))
        viewModelScope.launch {
            val client = QuotesClient.quotesClient()
            client.getQuote().let {
                if (it.isSuccessful) {
                    _quote.postValue(Resource.success(it.body()))
                }else{
                    Log.d("QuotesViewModel", "${it.errorBody()}")
                    _quote.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }
}