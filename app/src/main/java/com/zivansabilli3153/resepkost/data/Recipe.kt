package com.zivansabilli3153.resepkost.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val category: String,
    val duration: String,
    val ingredients: String,
    val steps: String,
    val createdAt: Long = System.currentTimeMillis()
)