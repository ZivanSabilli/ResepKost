package com.zivansabilli3153.resepkost.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zivansabilli3153.resepkost.data.Recipe
import com.zivansabilli3153.resepkost.data.RecipeRepository
import kotlinx.coroutines.launch

data class DetailUiState(
    val title: String = "",
    val category: String = "",
    val duration: String = "",
    val ingredients: String = "",
    val steps: String = "",
    val errorMessage: String? = null
)

class DetailViewModel(
    private val repository: RecipeRepository
) : ViewModel() {

    var uiState by mutableStateOf(DetailUiState())
        private set

    private var currentRecipe: Recipe? = null

    fun loadRecipe(id: Long) {
        if (id == 0L) return

        viewModelScope.launch {
            val recipe = repository.getRecipeById(id)
            currentRecipe = recipe

            if (recipe != null) {
                uiState = DetailUiState(
                    title = recipe.title,
                    category = recipe.category,
                    duration = recipe.duration,
                    ingredients = recipe.ingredients,
                    steps = recipe.steps
                )
            }
        }
    }

    fun updateTitle(value: String) {
        uiState = uiState.copy(title = value, errorMessage = null)
    }

    fun updateCategory(value: String) {
        uiState = uiState.copy(category = value, errorMessage = null)
    }

    fun updateDuration(value: String) {
        uiState = uiState.copy(duration = value, errorMessage = null)
    }

    fun updateIngredients(value: String) {
        uiState = uiState.copy(ingredients = value, errorMessage = null)
    }

    fun updateSteps(value: String) {
        uiState = uiState.copy(steps = value, errorMessage = null)
    }

    fun saveRecipe(id: Long, onSuccess: () -> Unit) {
        if (
            uiState.title.isBlank() ||
            uiState.category.isBlank() ||
            uiState.duration.isBlank() ||
            uiState.ingredients.isBlank() ||
            uiState.steps.isBlank()
        ) {
            uiState = uiState.copy(errorMessage = "Semua data wajib diisi")
            return
        }

        viewModelScope.launch {
            val recipe = Recipe(
                id = id,
                title = uiState.title,
                category = uiState.category,
                duration = uiState.duration,
                ingredients = uiState.ingredients,
                steps = uiState.steps,
                createdAt = currentRecipe?.createdAt ?: System.currentTimeMillis()
            )

            if (id == 0L) {
                repository.insertRecipe(recipe)
            } else {
                repository.updateRecipe(recipe)
            }

            onSuccess()
        }
    }

    fun deleteRecipe(id: Long, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val recipe = repository.getRecipeById(id)
            if (recipe != null) {
                repository.deleteRecipe(recipe)
                onSuccess()
            }
        }
    }
}