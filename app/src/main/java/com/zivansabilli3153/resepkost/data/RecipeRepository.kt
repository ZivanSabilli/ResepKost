package com.zivansabilli3153.resepkost.data

class RecipeRepository(
    private val recipeDao: RecipeDao
) {
    val recipes = recipeDao.getAllRecipes()

    suspend fun getRecipeById(id: Long): Recipe? {
        return recipeDao.getRecipeById(id)
    }

    suspend fun insertRecipe(recipe: Recipe) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun updateRecipe(recipe: Recipe) {
        recipeDao.updateRecipe(recipe)
    }

    suspend fun deleteRecipe(recipe: Recipe) {
        recipeDao.deleteRecipe(recipe)
    }
}