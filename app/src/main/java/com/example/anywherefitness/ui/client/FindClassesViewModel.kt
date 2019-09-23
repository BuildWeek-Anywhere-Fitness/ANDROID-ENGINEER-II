package com.example.anywherefitness.ui.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FindClassesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Find Classes Fragment"
    }
    val text: LiveData<String> = _text
}