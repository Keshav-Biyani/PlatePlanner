package com.example.plateplanner.data.localPreferences

import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.Flow


val IS_EDITABLE_KEY= booleanPreferencesKey("is_editable")

interface EditablePref {
    suspend fun setEditableStatus(isEditable : Boolean)
    fun getEditableStatus() : Flow<Boolean>
}

