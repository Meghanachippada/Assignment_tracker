package com.example.assignment_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AssignmentAdapter(private val assignments: List<Assignment>) : RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder>() {
    class AssignmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val assignmentNameTextView: TextView = itemView.findViewById(R.id.assignmentNameTextView)
        val startDateTextView: TextView = itemView.findViewById(R.id.startDateTextView)
        val endDateTextView: TextView = itemView.findViewById(R.id.endDateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.assignment_item, parent, false)
        return AssignmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        val assignment = assignments[position]
        holder.assignmentNameTextView.text = "Name: ${assignment.assignmentName}"
        holder.startDateTextView.text = "Start Date: ${assignment.startDate}"
        holder.endDateTextView.text = "End Date: ${assignment.endDate}"
    }

    override fun getItemCount() = assignments.size
}