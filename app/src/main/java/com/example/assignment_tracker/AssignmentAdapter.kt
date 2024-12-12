package com.example.assignment_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AssignmentAdapter(private val assignments: List<Assignment>) : RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder>() {
    class AssignmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val assignmentNameTextView: TextView = itemView.findViewById(R.id.assignmentNameTextView)
        val endDateTextView: TextView = itemView.findViewById(R.id.endDateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.assignment_item, parent, false)
        return AssignmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        val assignment = assignments[position]
        val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm")
        val dateTimeEnd = LocalDateTime.parse(assignment.dueDate, formatter)
        val newFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy   HH:mm")
        val formattedEnd = dateTimeEnd.format(newFormatter)
        holder.assignmentNameTextView.text = assignment.assignmentName
        holder.endDateTextView.text = "Due: $formattedEnd"
    }

    override fun getItemCount() = assignments.size
}