package com.example.assignment_tracker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddViewModel : ViewModel() {
    private val _assignmentName = MutableLiveData<String>()
    val assignmentName : LiveData<String> get() = _assignmentName
    private var _dueDate = MutableLiveData<String>()
    val dueDate : LiveData<String> get() = _dueDate

    fun setDueDate(date: String) {
        _dueDate.value = date
    }

    fun setAssignmentName(name: String) {
        _assignmentName.value = name
        Log.i("Assignment", "Name Changed To: ${assignmentName}")
    }
}