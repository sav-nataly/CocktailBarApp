package ru.savina.cocktailbar.data

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val description: String
)