package com.example.assignment_tracker

import android.widget.ImageView
import com.bumptech.glide.Glide
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize FirebaseAuth3
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
    }

    private fun displayWelcomeGiphy() {
        val imageView: ImageView = findViewById(R.id.welcomeImageView) // Ensure this ImageView exists in activity_main.xml
        val giphyUrl = "https://gifdb.com/images/high/welcome-colorful-neon-sign-smile-t1zgek1dk43wsbpv.gif" // Direct URL to Giphy
        Glide.with(this)
            .load(giphyUrl)
            .into(imageView)
    }
}
