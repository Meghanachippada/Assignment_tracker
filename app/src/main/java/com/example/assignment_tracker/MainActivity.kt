package com.example.assignment_tracker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Check if the user is logged in
        val currentUser = firebaseAuth.currentUser
        if (currentUser == null) {
            // If no user is logged in, redirect to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // Display a Giphy instead of a welcome message
            displayWelcomeGiphy()
        }

        // Logout Button Setup
        val logoutButton: Button = findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun displayWelcomeGiphy() {
        val imageView: ImageView = findViewById(R.id.welcomeImageView)
        val gifUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.welcome)
        Glide.with(this)
            .load(gifUri)
            .into(imageView)
    }
}
