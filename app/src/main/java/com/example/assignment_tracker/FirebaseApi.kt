package com.example.assignment_tracker
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FirebaseApi {
    // Get data from specific path
    @GET("assignments.json")
    fun getAssignmentsByUserEmail(
        @Query("orderBy") orderBy: String,
        @Query("equalTo") userEmail: String
    ) : Call<Map<String, Assignment>>
    fun getAssignmentByName(
        @Query("orderBy") orderBy: String,
        @Query("equalTo") userEmail: String
    ) : Call<Map<String, Assignment>>

    @GET("classes.json")
    fun getSchoolClass(
        @Query("orderBy") orderBy: String,
        @Query("equalTo") userEmail: String
    ) : Call<Map<String, SchoolClass>>

    // Post data to specific path
    @POST("assignments.json")
    fun postAssignment(@Body assignment: Assignment): Call<Assignment>

    @POST("classes.json")
    fun postClass(@Body schoolClass: SchoolClass) : Call<SchoolClass>

    @DELETE("assignments/{id}.json")
    fun deleteAssignment(@Path("id") assignmentId: String): Call<Void>
}