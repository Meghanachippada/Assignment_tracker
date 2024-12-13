package com.example.assignment_tracker

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : AndroidViewModel(application) {
    private val _assignmentName = MutableLiveData<String>()
    val assignmentName : LiveData<String> get() = _assignmentName
    private var _dueDate = MutableLiveData<String>()
    val dueDate : LiveData<String> get() = _dueDate
    private val dataStoreAssignment = DataStoreAssignment(application)

    init {
        viewModelScope.launch {
            dataStoreAssignment.assignmentName.collect { name ->
                Log.d("ViewModel", "Loaded name: $name")
                _assignmentName.value = name ?: ""
            }
            dataStoreAssignment.dueDate.collect { date ->
                Log.d("ViewModel", "Loaded due date: $date")
                _dueDate.value = date ?: ""
            }
        }
    }

    fun setDueDate(date: String) {
        _dueDate.value = date
        viewModelScope.launch {
            dataStoreAssignment.saveDueDate(date)
        }
    }

    fun setAssignmentName(name: String) {
        _assignmentName.value = name
        viewModelScope.launch {
            dataStoreAssignment.saveAssignmentName(name)
        }
        Log.i("Assignment", "Name Changed To: ${assignmentName}")
    }
}