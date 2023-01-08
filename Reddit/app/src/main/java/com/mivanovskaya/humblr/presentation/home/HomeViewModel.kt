package com.mivanovskaya.humblr.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mivanovskaya.humblr.data.api.ApiToken
import com.mivanovskaya.humblr.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiToken: ApiToken) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}