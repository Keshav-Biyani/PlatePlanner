package com.example.plateplanner.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.plateplanner.BuildConfig
import com.example.plateplanner.data.repository.Repository
import com.example.plateplanner.data.local.database.RecipeDatabase
import com.example.plateplanner.data.localPreferences.EditablePref
import com.example.plateplanner.data.localPreferences.EditablePrefImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent ::class)
@Module
object AppModule{
    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): RecipeDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RecipeDatabase::class.java,
            "RecipeDatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context):DataStore<Preferences>{
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler (
                produceNewData = { emptyPreferences() }
            ), produceFile = {
                context.preferencesDataStoreFile("editable_status_data")
            }
        )
    }

    @Provides
    fun provideEditablePref(dataStore: DataStore<Preferences>) : EditablePref = EditablePrefImpl(dataStore)

    @Provides
    @Singleton
    fun provideApiKey(): String = BuildConfig.API_KEY

    @Provides
    @Singleton
    fun provideRepository(apiKey : String,db :RecipeDatabase): Repository {
        return Repository(apiKey,db)
    }
}

