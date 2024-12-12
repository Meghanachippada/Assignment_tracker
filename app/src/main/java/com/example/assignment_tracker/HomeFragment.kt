package com.example.assignment_tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imageView: ImageView = view.findViewById(R.id.welcomeImageView) // Ensure this ImageView exists in activity_main.xml
        val giphyUrl = "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExMzRqazJ1NTk5a3d4bXNyMGQ5YXRzN2xxZDY5c3l6bDVkdmQwb3NiZyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/xUPGGDNsLvqsBOhuU0/giphy.gif" // Direct URL to Giphy
        Glide.with(this)
            .load(giphyUrl)
            .into(imageView)
        super.onViewCreated(view, savedInstanceState)
    }
}