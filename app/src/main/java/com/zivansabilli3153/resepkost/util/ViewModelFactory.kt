package com.zivansabilli3153.resepkost.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zivansabilli3153.resepkost.data.RecipeRepository
import com.zivansabilli3153.resepkost.ui.screen.DetailViewModel
import com.zivansabilli3153.resepkost.ui.screen.MainViewModel

class ViewModelFactory(
    private val repository: RecipeRepository,
    private val settingsDataStore: SettingsDataStore
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository, settingsDataStore) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}