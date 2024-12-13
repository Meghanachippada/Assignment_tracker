package com.example.assignment_tracker.ui.theme

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.example.assignment_tracker.R

// Set up the dark and light themes using traditional Android styles.

class ThemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the theme dynamically depending on the system theme (dark or light mode)
        if (isSystemInDarkMode()) {
            setTheme(R.style.AppTheme_Dark)
        } else {
            setTheme(R.style.AppTheme_Light)
        }

        setContentView(R.layout.activity_main)
    }

    // Function to check if system is in dark mode
    private fun isSystemInDarkMode(): Boolean {
        return (resources.configuration.uiMode and
                android.content.res.Configuration.UI_MODE_NIGHT_MASK) == android.content.res.Configuration.UI_MODE_NIGHT_YES
    }
}
