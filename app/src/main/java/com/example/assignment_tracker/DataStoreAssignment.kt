package com.example.assignment_tracker

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("add_fragment_prefs")
class DataStoreAssignment (private val context: Context) {
    private val ASSIGNMENT_NAME_KEY = stringPreferencesKey("assignment_name")
    private val DUE_DATE_KEY = stringPreferencesKey("due_date")

    val assignmentName : Flow<String?> = context.dataStore.data.map {
        prefs ->
        val name = prefs[ASSIGNMENT_NAME_KEY]
        Log.d("DataStore", "Retrieved name: $name")
        name
    }.distinctUntilChanged()

    val dueDate : Flow<String?> = context.dataStore.data.map {
        prefs ->
        val date = prefs[DUE_DATE_KEY]
        Log.d("DataStore", "Retrieved due date: $date")
        date
    }.distinctUntilChanged()

    suspend fun saveAssignmentName(name: String) {
        try {
            context.dataStore.edit { prefs ->
                prefs[ASSIGNMENT_NAME_KEY] = name
            }
        } catch (e: Exception) {
            Log.e("DataStore", "Error saving assignment name: ${e.message}")
        }
    }

    suspend fun saveDueDate(date: String) {
        try {
            context.dataStore.edit { prefs ->
                prefs[DUE_DATE_KEY] = date
            }
        } catch (e: Exception) {
            Log.e("DataStore", "Error saving due date: ${e.message}")
        }
    }
}