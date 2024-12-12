package com.example.assignment_tracker

data class SchoolClass(
    val userEmail : String = "",
    val professorName : String = "",
    val className: String = "",
    val classStartTime: String = "",
    val classEndTime: String = "",
    val classDays: MutableList<String> = mutableListOf(),
)
