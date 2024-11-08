package com.example.plateplanner.localPref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


val IS_EDITABLE_KEY= booleanPreferencesKey("is_editable")

interface EditablePref {
    suspend fun setEditableStatus(isEditable : Boolean)
    fun getEditableStatus() : Flow<Boolean>
}

class EditablePrefImpl(private val datastore:DataStore<Preferences>) : EditablePref {
    override suspend fun setEditableStatus(isEditable: Boolean) {
            datastore.edit {
                it[IS_EDITABLE_KEY]=isEditable

            }
    }

    override fun getEditableStatus(): Flow<Boolean> {
        return  datastore.data.catch { emit(emptyPreferences()) }.map {
            it[IS_EDITABLE_KEY] ?: false
        }
    }

}