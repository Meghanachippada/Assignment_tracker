package com.example.assignment_tracker

import android.os.Bundle
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_assignment, container, false)
    }

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var viewModel: AssignmentViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        viewModel = ViewModelProvider(this)[AssignmentViewModel::class.java]
        viewModel.fetchAssignments(firebaseAuth.currentUser!!.email!!)
        viewModel.assignments.observe(viewLifecycleOwner) {
            assignments -> recyclerView.adapter = AssignmentAdapter(assignments)
        }
    }
}