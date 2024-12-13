package com.example.assignment_tracker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
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

    private val viewModel: AddViewModel by activityViewModels()
    private val schoolClassViewModel: SchoolClassViewModel by activityViewModels()
    private lateinit var spinnerAdapter: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser!!.email
        val submitButton : Button = view.findViewById(R.id.submitButton)
        val assignmentNameEditText : EditText = view.findViewById(R.id.assignmentNameEditText)
        val dueDateButton : Button = view.findViewById(R.id.dueDateButton)
        val classSpinner : Spinner = view.findViewById(R.id.classSpinner)
        var dueDate = ""
        var schoolClassSelected = ""

        viewModel.dueDate.observe(viewLifecycleOwner) { date ->
            Log.d("Fragment", "Observed due date: $date")
            dueDate = date.ifEmpty { "" }
        }

        viewModel.assignmentName.observe(viewLifecycleOwner) { name ->
            if (assignmentNameEditText.text.toString() != name) {
                Log.i("Assignment","Setting text to: $name")
                assignmentNameEditText.setText(name)
            }
        }

        dueDateButton.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val datePickerDialog = DatePickerDialog(
                view.context,
                { view, year, monthOfYear, dayOfMonth ->
                    val timePickerDialog = TimePickerDialog(view.context, { _, selectedHour, selectedMinute ->
                        var newHour = ""
                        var newMinute = ""
                        if (selectedHour < 10) {newHour = (selectedHour.toString() + "0")} else {newHour = selectedHour.toString()}
                        if (selectedMinute < 10) {newMinute = (selectedMinute.toString() + "0")} else {newMinute = selectedMinute.toString()}
                        dueDate += " $newHour:$newMinute"
                        viewModel.setDueDate(dueDate)
                    }, hour, minute, false)
                    timePickerDialog.show()
                    dueDate = ""
                    var newMonth: String
                    var newDay: String
                    if ((monthOfYear + 1) < 10) {newMonth = "0${(monthOfYear + 1)}"} else {newMonth = (monthOfYear + 1).toString()}
                    if (dayOfMonth < 10) {newDay = "0$dayOfMonth" } else {newDay = dayOfMonth.toString()}
                    dueDate = "$newMonth-$newDay-$year"
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        assignmentNameEditText.addTextChangedListener {
            viewModel.setAssignmentName(it.toString())
        }

        schoolClassViewModel.schoolClasses.observe(viewLifecycleOwner){ classes ->
            val classNames = classes.map {it.className}
            spinnerAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, classNames)
            classSpinner.adapter = spinnerAdapter
        }

        schoolClassViewModel.fetchSchoolClasses(currentUser!!)

        classSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                schoolClassSelected = parent.getItemAtPosition(position) as String
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        submitButton.setOnClickListener {
            val assignmentName = assignmentNameEditText.text.toString()

            if (dueDate != "" && assignmentName.isNotEmpty() && schoolClassSelected != "") {
                val assignment = Assignment(currentUser, assignmentName, dueDate, schoolClassSelected)
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
                    Toast.makeText(view?.context, "Assignment posted successfully", Toast.LENGTH_SHORT).show()
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