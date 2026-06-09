package com.zivansabilli3153.resepkost.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsDataStore(
    private val context: Context
) {
    private val showListKey = booleanPreferencesKey("show_list")
    private val themeKey = stringPreferencesKey("theme_color")

    val showListFlow = context.dataStore.data.map { preferences ->
        preferences[showListKey] ?: true
    }

    val themeFlow = context.dataStore.data.map { preferences ->
        preferences[themeKey] ?: "blue"
    }

    suspend fun saveShowList(showList: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[showListKey] = showList
        }
    }

    suspend fun saveTheme(theme: String) {
        context.dataStore.edit { preferences ->
            preferences[themeKey] = theme
        }
    }
}