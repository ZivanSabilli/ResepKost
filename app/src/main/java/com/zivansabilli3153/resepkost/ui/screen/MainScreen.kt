package com.zivansabilli3153.resepkost.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.zivansabilli3153.resepkost.data.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onAddClick: () -> Unit,
    onItemClick: (Long) -> Unit
) {
    val recipes by viewModel.recipes.collectAsState()
    val showList by viewModel.showList.collectAsState()

    var showThemeMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "ResepKost")
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.changeLayout(!showList)
                        }
                    ) {
                        Icon(
                            imageVector = if (showList) {
                                Icons.Default.GridView
                            } else {
                                Icons.AutoMirrored.Filled.List
                            },
                            contentDescription = "Ubah tampilan"
                        )
                    }

                    IconButton(
                        onClick = {
                            showThemeMenu = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Palette,
                            contentDescription = "Pilih tema"
                        )
                    }

                    DropdownMenu(
                        expanded = showThemeMenu,
                        onDismissRequest = {
                            showThemeMenu = false
                        }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(text = "Biru")
                            },
                            onClick = {
                                viewModel.changeTheme("blue")
                                showThemeMenu = false
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(text = "Hijau")
                            },
                            onClick = {
                                viewModel.changeTheme("green")
                                showThemeMenu = false
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(text = "Oranye")
                            },
                            onClick = {
                                viewModel.changeTheme("orange")
                                showThemeMenu = false
                            }
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah resep"
                )
            }
        }
    ) { padding ->
        if (recipes.isEmpty()) {
            EmptyContent(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            )
        } else {
            if (showList) {
                RecipeList(
                    recipes = recipes,
                    onItemClick = onItemClick,
                    modifier = Modifier.padding(padding)
                )
            } else {
                RecipeGrid(
                    recipes = recipes,
                    onItemClick = onItemClick,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}

@Composable
private fun EmptyContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Belum ada resep",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Tekan tombol + untuk menambahkan resep makanan anak kost.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun RecipeList(
    recipes: List<Recipe>,
    onItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(recipes) { recipe ->
            RecipeItem(
                recipe = recipe,
                onClick = {
                    onItemClick(recipe.id)
                }
            )
        }
    }
}

@Composable
private fun RecipeGrid(
    recipes: List<Recipe>,
    onItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(recipes) { recipe ->
            RecipeItem(
                recipe = recipe,
                onClick = {
                    onItemClick(recipe.id)
                }
            )
        }
    }
}

@Composable
private fun RecipeItem(
    recipe: Recipe,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row {
                Text(
                    text = recipe.category,
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = " • ${recipe.duration}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = recipe.ingredients,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}