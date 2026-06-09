package com.zivansabilli3153.resepkost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.zivansabilli3153.resepkost.data.RecipeDatabase
import com.zivansabilli3153.resepkost.data.RecipeRepository
import com.zivansabilli3153.resepkost.navigation.SetupNavGraph
import com.zivansabilli3153.resepkost.ui.theme.ResepKostTheme
import com.zivansabilli3153.resepkost.util.SettingsDataStore
import com.zivansabilli3153.resepkost.util.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContext = applicationContext

        setContent {
            val database = remember {
                RecipeDatabase.getDatabase(appContext)
            }

            val repository = remember {
                RecipeRepository(database.recipeDao())
            }

            val settingsDataStore = remember {
                SettingsDataStore(appContext)
            }

            val themeColor by settingsDataStore.themeFlow.collectAsState(initial = "blue")

            val factory = remember {
                ViewModelFactory(
                    repository = repository,
                    settingsDataStore = settingsDataStore
                )
            }

            ResepKostTheme(
                themeColor = themeColor
            ) {
                SetupNavGraph(factory = factory)
            }
        }
    }
}