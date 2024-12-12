package com.example.assignment_tracker

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
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

class ClassFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_class, container, false)
    }

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser!!.email
        val submitButton : Button = view.findViewById(R.id.classSubmitButton)
        val professorEditText : EditText = view.findViewById(R.id.professorEditText)
        val classNameEditText : EditText = view.findViewById(R.id.classNameEditText)
        val startTimeButton : Button = view.findViewById(R.id.classStartButton)
        val endTimeButton : Button = view.findViewById(R.id.classEndButton)
        val classDaysButton : Button = view.findViewById(R.id.classSelectDaysButton)
        var startTime = ""
        var endTime = ""
        val daysSelected = mutableListOf<String>()
        val selectedDay: BooleanArray
        val daysList = mutableListOf<Int>()
        val dayArray = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

        startTimeButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(view.context, { _, selectedHour, selectedMinute ->
                var newHour: String
                var newMinute: String
                if (selectedHour < 10) {newHour = (selectedHour.toString() + "0")} else {newHour = selectedHour.toString()}
                if (selectedMinute < 10) {newMinute = (selectedMinute.toString() + "0")} else {newMinute = selectedMinute.toString()}
                startTime = " $newHour:$newMinute"
            }, hour, minute, false)
            timePickerDialog.show()
        }

        endTimeButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(view.context, { _, selectedHour, selectedMinute ->
                var newHour: String
                var newMinute: String
                if (selectedHour < 10) {newHour = (selectedHour.toString() + "0")} else {newHour = selectedHour.toString()}
                if (selectedMinute < 10) {newMinute = (selectedMinute.toString() + "0")} else {newMinute = selectedMinute.toString()}
                endTime = " $newHour:$newMinute"
            }, hour, minute, false)
            timePickerDialog.show()
        }

        selectedDay = BooleanArray(dayArray.size)
        classDaysButton.setOnClickListener {
            val builder = AlertDialog.Builder(view.context)
            builder.setTitle("Select Day(s)")
            builder.setCancelable(false)
            builder.setMultiChoiceItems(dayArray, selectedDay) {
                _, i, b ->
                if (b) {
                    daysList.add(i)
                    daysList.sort()
                } else {
                    daysList.remove(i)
                }
            }
            builder.setPositiveButton("OK") {
                _, _ ->
                for (j in daysList.indices) {
                    daysSelected.add(dayArray[daysList[j]])
                }
                Log.i("class", daysSelected.toString())
            }
            builder.setNegativeButton("Cancel") {
                dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            builder.setNeutralButton("Clear All") {
                _, _ ->
                for (j in selectedDay.indices) {
                    selectedDay[j] = false
                }
                daysList.clear()
                daysSelected.clear()
            }
            builder.show()
        }

        submitButton.setOnClickListener {
            val professorName = professorEditText.text.toString()
            val className = classNameEditText.text.toString()
            if (professorName.isNotEmpty() && className.isNotEmpty() && startTime != "" && endTime != "" && daysSelected.isNotEmpty()) {
                val schoolClass = SchoolClass(currentUser!!, professorName, className, startTime, endTime, daysSelected)
                postClass(schoolClass)
            } else {
                Toast.makeText(view.context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun postClass(schoolClass: SchoolClass) {
        RetrofitInstance.api.postClass(schoolClass).enqueue(object : Callback<SchoolClass> {
            override fun onResponse(call: Call<SchoolClass>, response: Response<SchoolClass>) {
                if (response.isSuccessful) {
                    Toast.makeText(view?.context, "Class posted successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(view?.context, "Failed to post class", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SchoolClass>, t: Throwable) {
                Toast.makeText(view?.context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}