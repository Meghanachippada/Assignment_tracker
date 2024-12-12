package com.example.assignment_tracker

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class AddFragment : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser?.email
        val submitButton : Button = view.findViewById(R.id.submitButton)
        val assignmentNameEditText : EditText = view.findViewById(R.id.assignmentNameEditText)
        val startDateButton : Button = view.findViewById(R.id.startButton)
        val endDateButton : Button = view.findViewById(R.id.endDateButton)
        var startDate : String = ""
        var endDate : String = ""

        startDateButton.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                view.context,
                { view, year, monthOfYear, dayOfMonth ->
                    startDate =
                        ((monthOfYear + 1).toString() + "-" + dayOfMonth.toString() + "-" + year)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
        endDateButton.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                view.context,
                { view, year, monthOfYear, dayOfMonth ->
                    endDate =
                        ((monthOfYear + 1).toString() + "-" + dayOfMonth.toString() + "-" + year)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        submitButton.setOnClickListener {
            val assignmentName = assignmentNameEditText.text.toString()

            if (startDate != "" && endDate != "" && assignmentName.isNotEmpty()) {
                val assignment = Assignment(currentUser, assignmentName, startDate, endDate)
                postAssignment(assignment)
            } else {
                Toast.makeText(view.context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun postAssignment(assignment: Assignment) {
        RetrofitInstance.api.postAssignment(assignment).enqueue(object : Callback<Assignment> {
            override fun onResponse(call: Call<Assignment>, response: Response<Assignment>) {
                if (response.isSuccessful) {
                    Toast.makeText(view?.context, "Assignment posted successfuly", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(view?.context, "Failed to post assignment", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Assignment>, t: Throwable) {
                Toast.makeText(view?.context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}