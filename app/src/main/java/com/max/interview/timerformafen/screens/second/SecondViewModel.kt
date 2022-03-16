package com.max.interview.timerformafen.screens.second

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondViewModel : ViewModel() {

    private var _editText = MutableLiveData("")
    val editText : LiveData<String> = _editText

    fun updateEditText(text: String){
        _editText.postValue(text)
    }

}