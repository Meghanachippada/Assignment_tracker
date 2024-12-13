package com.example.assignment_tracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.assignment_tracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MainViewModel::class.java]

        viewModel.currentUser.observe(this) {
            user -> if (user == null) {
                navigateToLogin()
            } else {
                setupNavigation()
        }
        }

        val currentFragment = viewModel.currentFragment
        if (currentFragment != null) {
            replaceFragment(currentFragment)
        } else {
            val homeFragment = HomeFragment()
            replaceFragment(homeFragment)
            viewModel.setCurrentFragment(homeFragment)
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setupNavigation() {
        binding.bottomNav.setOnItemSelectedListener {
            val selectedFragment = when (it.itemId) {
                R.id.home -> HomeFragment()
                R.id.add_menu -> AddFragment()
                R.id.view_menu -> ViewAssignmentFragment()
                R.id.class_menu -> ClassFragment()
                else -> null
            }

            if (selectedFragment != null) {
                replaceFragment(selectedFragment)
                viewModel.setCurrentFragment(selectedFragment)
                true
            } else {
                false
            }
        }

        // Logout Button Setup
        val logoutButton: Button = findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener {
            viewModel.signOut()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}
