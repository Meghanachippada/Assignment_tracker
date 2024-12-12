package com.example.assignment_tracker

import android.widget.ImageView
import com.bumptech.glide.Glide
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var Title: EditText
    private lateinit var Points: EditText
    private lateinit var DueDate: EditText
    private lateinit var addButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var AssignmentAdapter: assignmentAdapter
    private val assignmentList = mutableListOf<assignment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize FirebaseAuth3
        firebaseAuth = FirebaseAuth.getInstance()

        // Check if the user is logged in
        val currentUser = firebaseAuth.currentUser
        if (currentUser == null) {
            // If no user is logged in, redirect to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // Display a Giphy instead of a welcome message
            //displayWelcomeGiphy()
        }

        //section 1
        connectViewPointers()

        //section 2
        recyclerView.layoutManager = LinearLayoutManager(this)

        //section 3
        AssignmentAdapter = assignmentAdapter(assignmentList)

        //section 4
        addButton.setOnClickListener{addAssignment()}
    }

    private fun connectViewPointers() {
        //Intializing views
        Title = findViewById(R.id.assignment_name)
        Points = findViewById(R.id.points)
        DueDate = findViewById(R.id.due_date)
        addButton = findViewById(R.id.Add)
        recyclerView = findViewById(R.id.recyclerView)
        AssignmentAdapter = assignmentAdapter(assignmentList)

    }

    private fun addAssignment() {
        val title = Title.text.toString()
        val points = Points.text.toString().toIntOrNull()
        val duedate = DueDate.text.toString()

        if(title.isNotBlank() && points != null && duedate.isNotBlank()) {
           val newAssignment = assignment(title, points, duedate)
            assignmentList.add(newAssignment)
            AssignmentAdapter.notifyItemInserted(assignmentList.size - 1)

            Title.text.clear()
            Points.text.clear()
            DueDate.text.clear()
        }

    }

}
