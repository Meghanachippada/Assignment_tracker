package com.example.assignment_tracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SchoolClassViewModel : ViewModel() {
    private val _schoolClasses = MutableLiveData<List<SchoolClass>>()
    val schoolClasses: LiveData<List<SchoolClass>> get() = _schoolClasses

    fun fetchSchoolClasses(email: String) {
        println(RetrofitInstance.api.getSchoolClass("\"userEmail\"","\"$email\"").request().url())
        RetrofitInstance.api.getSchoolClass("\"userEmail\"","\"$email\"").enqueue(object :
            Callback<Map<String, SchoolClass>> {
            override fun onResponse(
                call: Call<Map<String, SchoolClass>>,
                response: Response<Map<String, SchoolClass>>
            ) {
                if (response.isSuccessful) {
                    val fetchedClasses = response.body()?.values?.toList() ?: emptyList()
                    _schoolClasses.postValue(fetchedClasses)
                }
            }

            override fun onFailure(call: Call<Map<String, SchoolClass>>, t: Throwable) {
                _schoolClasses.postValue(emptyList()) // Notify the UI of an empty list in case of failure
            }
        })
    }
}