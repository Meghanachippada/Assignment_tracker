package com.example.assignment_tracker

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("class_fragment_prefs")
class DataStoreClass (private val context: Context) {
    companion object {
        val PROFESSOR_NAME = stringPreferencesKey("professor_name")
        val CLASS_NAME = stringPreferencesKey("class_name")
        val CLASS_START = stringPreferencesKey("class_start")
        val CLASS_END = stringPreferencesKey("class_end")
    }

    val professorName : Flow<String?> = context.dataStore.data.map {
        prefs -> prefs[PROFESSOR_NAME]
    }.distinctUntilChanged()

    val className : Flow<String?> = context.dataStore.data.map {
        prefs -> prefs[CLASS_NAME]
    }.distinctUntilChanged()

    val classStart : Flow<String?> = context.dataStore.data.map {
        prefs -> prefs[CLASS_START]
    }.distinctUntilChanged()

    val classEnd : Flow<String?> = context.dataStore.data.map {
        prefs -> prefs[CLASS_END]
    }.distinctUntilChanged()

    suspend fun saveProfessorName(name: String) {
        try {
            context.dataStore.edit { prefs ->
                prefs[PROFESSOR_NAME] = name
            }
        } catch (e: Exception) {
            Log.e("Datastore","Error saving assignment name: ${e.message}")
        }
    }

    suspend fun saveClassName(name: String) {
        try {
            context.dataStore.edit { prefs ->
                prefs[CLASS_NAME] = name
            }
        } catch (e: Exception) {
            Log.e("Datastore","Error saving assignment name: ${e.message}")
        }
    }

    suspend fun saveClassStart(time: String) {
        try {
            context.dataStore.edit { prefs ->
                prefs[CLASS_START] = time
            }
        } catch (e: Exception) {
            Log.e("Datastore","Error saving assignment name: ${e.message}")
        }
    }

    suspend fun saveClassEnd(time: String) {
        try {
            context.dataStore.edit { prefs ->
                prefs[CLASS_END] = time
            }
        } catch (e: Exception) {
            Log.e("Datastore","Error saving assignment name: ${e.message}")
        }
    }
}