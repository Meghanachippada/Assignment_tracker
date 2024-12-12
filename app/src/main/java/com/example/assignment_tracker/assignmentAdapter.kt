package com.example.assignment_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class assignmentAdapter(private val assignmentList: MutableList<assignment>) :
    RecyclerView.Adapter<assignmentAdapter.assignmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): assignmentViewHolder {
        TODO("Not yet implemented")
        val assignmentView = LayoutInflater.from(parent.context).inflate(R.layout.assignment_layout, parent, false)
        return assignmentViewHolder(assignmentView)
    }

    override fun onBindViewHolder(holder: assignmentViewHolder, position: Int) {
        TODO("Not yet implemented")
        val assignment = assignmentList[position]
        holder.assignmentTitle.text = assignment.title
        holder.assignmentPoints.text = assignment.points.toString()
        holder.assignmentDuedate.text = assignment.dueDate
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
        return assignmentList.size
    }

    class assignmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val assignmentTitle: TextView = itemView.findViewById(R.id.assignmentTview)
        val assignmentPoints: TextView = itemView.findViewById(R.id.pointsTview)
        val assignmentDuedate: TextView = itemView.findViewById(R.id.duedateTview)
    }
    }