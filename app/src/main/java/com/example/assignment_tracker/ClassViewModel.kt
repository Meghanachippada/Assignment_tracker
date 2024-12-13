package com.example.assignment_tracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ClassViewModel : ViewModel() {
//    private val _assignmentName = MutableLiveData<String>()
//    val assignmentName : LiveData<String> get() = _assignmentName
    private val _professorName = MutableLiveData<String>()
    val professorName : LiveData<String> get() = _professorName
    private val _className = MutableLiveData<String>()
    val className : LiveData<String> get() = _className
    private val _startTime = MutableLiveData<String>()
    val startTime : LiveData<String> get() = _startTime
    private val _endTime = MutableLiveData<String>()
    val endTime : LiveData<String> get() = _endTime

    fun setProfessorName(name: String) {
        _professorName.value = name
    }

    fun setClassName(name: String) {
        _className.value = name
    }

    fun setStartTime(time: String) {
        _startTime.value = time
    }

    fun setEndTime(time: String) {
        _endTime.value = time
    }
}