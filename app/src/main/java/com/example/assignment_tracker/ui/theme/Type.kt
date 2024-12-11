package com.example.assignment_tracker.ui.theme

import android.graphics.Typeface
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.assignment_tracker.R

// Set of standard Android typography styles to start with

// For example, setting the body text style
fun applyBodyTextStyle(textView: TextView) {
    // Set a default font family (e.g., sans-serif)
    textView.typeface = Typeface.DEFAULT

    // Set the font size in sp
    textView.textSize = 16f

    // Set the line height (using the lineSpacingExtra property)
    textView.setLineSpacing(4f, 1f) // Line height in dp

    // Set letter spacing (in em units)
    textView.letterSpacing = 0.05f
}

// You can define more styles here similarly, for example, title text style or label text style:
fun applyTitleTextStyle(textView: TextView) {
    // Set a title style with larger font size
    textView.typeface = Typeface.DEFAULT_BOLD
    textView.textSize = 22f
    textView.setLineSpacing(6f, 1f)
    textView.letterSpacing = 0.1f
}

// If you want to use a custom font, you can load it from resources
fun applyCustomFont(textView: TextView, fontResId: Int) {
    val customFont = ResourcesCompat.getFont(textView.context, fontResId)
    textView.typeface = customFont
}
