package com.example.assignment_tracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.assignment_tracker.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        // Initialize FirebaseAuth3
        firebaseAuth = FirebaseAuth.getInstance()

        // Check if the user is logged in
        val currentUser = firebaseAuth.currentUser
        if (currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            binding.bottomNav.setOnItemSelectedListener {
                when(it.itemId) {
                    R.id.home -> replaceFragment(HomeFragment())
                    R.id.add_menu -> replaceFragment(AddFragment())
                    R.id.view_menu -> replaceFragment(ViewAssignmentFragment())
                    else -> {}
                }
                true
            }
        }

        // Logout Button Setup
        val logoutButton: Button = findViewById(R.id.logoutButton) // Ensure this is correct
        logoutButton.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}
