package com.example.assignment_tracker

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainViewModel(application: Application) : AndroidViewModel(application){
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?> get() = _currentUser

    private var _currentFragment : Fragment? = null
    val currentFragment : Fragment? get() = _currentFragment

    init {
        _currentUser.value = firebaseAuth.currentUser
    }

    fun setCurrentFragment(fragment: Fragment) {
        _currentFragment = fragment
    }

    fun signOut() {
        firebaseAuth.signOut()
        _currentUser.value = null
    }
}