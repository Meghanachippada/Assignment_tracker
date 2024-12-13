package com.example.assignment_tracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ClassViewModel(application: Application) : AndroidViewModel(application) {
    private val _professorName = MutableLiveData<String>()
    val professorName : LiveData<String> get() = _professorName
    private val _className = MutableLiveData<String>()
    val className : LiveData<String> get() = _className
    private val _startTime = MutableLiveData<String>()
    val startTime : LiveData<String> get() = _startTime
    private val _endTime = MutableLiveData<String>()
    val endTime : LiveData<String> get() = _endTime
    private val dataStoreClass = DataStoreClass(application)

    init {
        viewModelScope.launch {
            dataStoreClass.professorName.collect { name ->
                _professorName.value = name ?: ""
            }
            dataStoreClass.className.collect { name ->
                _className.value = name ?: ""
            }
            dataStoreClass.classStart.collect { time ->
                _startTime.value = time ?: ""
            }
            dataStoreClass.classEnd.collect { time ->
                _endTime.value = time ?: ""
            }
        }
    }

    fun setProfessorName(name: String) {
        _professorName.value = name
        viewModelScope.launch {
            dataStoreClass.saveProfessorName(name)
        }
    }

    fun setClassName(name: String) {
        _className.value = name
        viewModelScope.launch {
            dataStoreClass.saveClassName(name)
        }
    }

    fun setStartTime(time: String) {
        _startTime.value = time
        viewModelScope.launch {
            dataStoreClass.saveClassStart(time)
        }
    }

    fun setEndTime(time: String) {
        _endTime.value = time
        viewModelScope.launch {
            dataStoreClass.saveClassEnd(time)
        }
    }
}