package com.example.assignment_tracker

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class assignmentAdapter(private val assignmentList: MutableList<assignment>) :
    RecyclerView.Adapter<assignmentAdapter.assignmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): assignmentViewHolder {
        val assignmentView = LayoutInflater.from(parent.context)
             .inflate(R.layout.assignment_layout, parent, false)
        return assignmentViewHolder(assignmentView)
    }

    override fun onBindViewHolder(holder: assignmentViewHolder, position: Int) {
        Log.d("RecycleView","Binding Data")
        Log.d("Adapter Debug","Position: $position")
        val assignment = assignmentList[position]
        holder.assignmentTitle.text = assignment.title
        Log.d("Adapter Debug","Bound: {${assignment.title}}")
        holder.assignmentPoints.text = assignment.points.toString()
        Log.d("Adapter Debug","Bound: {${assignment.points}}")
        holder.assignmentDuedate.text = assignment.dueDate
        Log.d("Adapter Debug","Bound: {${assignment.dueDate}}")
    }

    override fun getItemCount(): Int {
        return assignmentList.size
    }

    class assignmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val assignmentTitle: TextView = itemView.findViewById(R.id.assignmentTview)
        val assignmentPoints: TextView = itemView.findViewById(R.id.pointsTview)
        val assignmentDuedate: TextView = itemView.findViewById(R.id.duedateTview)
    }
    }