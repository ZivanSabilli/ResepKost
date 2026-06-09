package com.zivansabilli3153.resepkost.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zivansabilli3153.resepkost.data.RecipeRepository
import com.zivansabilli3153.resepkost.util.SettingsDataStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    repository: RecipeRepository,
    private val settingsDataStore: SettingsDataStore
) : ViewModel() {

    val recipes = repository.recipes.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val showList = settingsDataStore.showListFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = true
    )

    val theme = settingsDataStore.themeFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = "blue"
    )

    fun changeLayout(showList: Boolean) {
        viewModelScope.launch {
            settingsDataStore.saveShowList(showList)
        }
    }

    fun changeTheme(theme: String) {
        viewModelScope.launch {
            settingsDataStore.saveTheme(theme)
        }
    }
}