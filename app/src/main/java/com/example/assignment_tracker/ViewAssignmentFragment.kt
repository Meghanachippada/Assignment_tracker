package com.example.assignment_tracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewAssignmentFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var viewModel: AssignmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_assignment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        viewModel = ViewModelProvider(this)[AssignmentViewModel::class.java]

        // Fetch assignments
        val userEmail = firebaseAuth.currentUser?.email
        if (userEmail != null) {
            viewModel.fetchAssignments(userEmail)
        }

        // Observe assignments and set up the adapter
        viewModel.assignments.observe(viewLifecycleOwner) { assignments ->
            recyclerView.adapter = AssignmentAdapter(assignments, object : AssignmentAdapter.OnAssignmentDeleteListener {
                override fun onAssignmentDelete(assignment: Assignment, position: Int) {
                    findAssignment(assignment)
                    Toast.makeText(requireContext(), "${assignment.assignmentName} deleted", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun findAssignment(inputAssignment: Assignment) {
        println(RetrofitInstance.api.getAssignmentsByUserEmail("\"assignmentName\"","\"${inputAssignment.assignmentName}\"").request().url())
        RetrofitInstance.api.getAssignmentsByUserEmail("\"assignmentName\"","\"${inputAssignment.assignmentName}\"").enqueue(object :
            Callback<Map<String, Assignment>> {
            override fun onResponse(
                call: Call<Map<String, Assignment>>,
                response: Response<Map<String, Assignment>>
            ) {
                if (response.isSuccessful) {
                    val assignments = response.body()
                    if (assignments != null) {
                        for ((id, assignment) in assignments) {
                            deleteAssignment(id)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Map<String, Assignment>>, t: Throwable) {
                Log.e("ViewAssignment","Failed to find assignment to delete")
            }
        })
    }

    private fun deleteAssignment(id: String) {
        RetrofitInstance.api.deleteAssignment(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.i("ViewAssignment","Deleted assignment $id")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("ViewAssignment","Failed to delete assignment")
            }
        })
    }
}
