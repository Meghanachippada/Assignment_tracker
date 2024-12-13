package com.example.assignment_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AssignmentAdapter(
    private val assignments: List<Assignment>,
    private val onAssignmentDeleteListener: OnAssignmentDeleteListener // Pass the listener to the adapter
) : RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder>() {

    // Define the interface for the delete action
    interface OnAssignmentDeleteListener {
        fun onAssignmentDelete(assignment: Assignment, position: Int)
    }

    class AssignmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val assignmentNameTextView: TextView = itemView.findViewById(R.id.assignmentNameTextView)
        val endDateTextView: TextView = itemView.findViewById(R.id.endDateTextView)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.assignment_item, parent, false)
        return AssignmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        val assignment = assignments[position]

        // Format the due date
        val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm")
        val dateTimeEnd = LocalDateTime.parse(assignment.dueDate, formatter)
        val newFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy   HH:mm")
        val formattedEnd = dateTimeEnd.format(newFormatter)

        holder.assignmentNameTextView.text = assignment.assignmentName
        holder.endDateTextView.text = "Due: $formattedEnd"

        // Handle delete button click
        holder.deleteButton.setOnClickListener {
            onAssignmentDeleteListener.onAssignmentDelete(assignment, position)
        }
    }

    override fun getItemCount() = assignments.size
}
