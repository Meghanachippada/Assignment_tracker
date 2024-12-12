package com.example.assignment_tracker

data class Assignment(
    val userEmail: String? = "",
    val assignmentName: String = "",
    val startDate: String = "",
    val endDate: String = ""
)
