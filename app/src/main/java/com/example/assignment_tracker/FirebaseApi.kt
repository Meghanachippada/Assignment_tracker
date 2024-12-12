package com.example.assignment_tracker
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FirebaseApi {
    // Get data from specific path
    @GET("assignments.json")
    fun getAssignmentsByUserEmail(
        @Query("userEmail") userEmail: String
    ) : Call<Map<String, Assignment>>

    // Post data to specific path
    @POST("assignments.json")
    fun postAssignment(@Body assignment: Assignment): Call<Assignment>
}