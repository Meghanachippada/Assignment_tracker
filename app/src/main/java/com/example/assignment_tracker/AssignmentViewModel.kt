package com.example.assignment_tracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AssignmentViewModel : ViewModel() {
    private val _assignments = MutableLiveData<List<Assignment>>()
    val assignments: LiveData<List<Assignment>> get() = _assignments

    fun fetchAssignments(email: String) {
        RetrofitInstance.api.getAssignmentsByUserEmail(email).enqueue(object :
            Callback<Map<String, Assignment>> {
            override fun onResponse(
                call: Call<Map<String, Assignment>>,
                response: Response<Map<String, Assignment>>
            ) {
                if (response.isSuccessful) {
                    val fetchedAssignments = response.body()?.values?.toList() ?: emptyList()
                    // Sort assignments by start date
                    val sortedAssignments = fetchedAssignments.sortedBy { assignment ->
                        LocalDateTime.parse(assignment.dueDate, DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm"))
                    }
                    _assignments.postValue(sortedAssignments)
                }
            }

            override fun onFailure(call: Call<Map<String, Assignment>>, t: Throwable) {
                _assignments.postValue(emptyList()) // Notify the UI of an empty list in case of failure
            }
        })
    }
}