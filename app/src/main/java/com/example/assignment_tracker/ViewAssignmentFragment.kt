package com.example.assignment_tracker

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewAssignmentFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_assignment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        fetchAssignments()
    }

    private fun fetchAssignments() {
        RetrofitInstance.api.getAssignments().enqueue(object : Callback<Map<String, Assignment>> {
            override fun onResponse(
                call: Call<Map<String, Assignment>>,
                response: Response<Map<String, Assignment>>
            ) {
                if (response.isSuccessful) {
                    val assignments = response.body()?.values?.toList() ?: emptyList()
                    recyclerView.adapter = AssignmentAdapter(assignments)
                } else {
                    Toast.makeText(view?.context , "Failed to fetch assignments", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Map<String, Assignment>>, t: Throwable) {
                Toast.makeText(view?.context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}