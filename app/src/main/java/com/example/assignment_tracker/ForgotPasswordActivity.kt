package com.example.assignment_tracker

import com.example.assignment_tracker.R
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val submitButton = findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {
            val email = emailEditText.text.toString()
            if (email.isNotEmpty()) {
                // Simulate password reset logic (e.g., send reset link via email)
                resetPassword(email)
            } else {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun resetPassword(email: String) {
        // Logic to send a password reset email or code to the user (API call, etc.)
        // You can integrate with a backend or use Firebase Auth for real implementation
        Toast.makeText(this, "Password reset link sent to $email", Toast.LENGTH_SHORT).show()

        // After the reset, navigate the user back to the LoginActivity
        finish() // or startActivity(Intent(this, LoginActivity::class.java))
    }
}
