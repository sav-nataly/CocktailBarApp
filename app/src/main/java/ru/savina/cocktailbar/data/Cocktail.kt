package ru.savina.cocktailbar.data

import kotlinx.serialization.Serializable

@Serializable
data class Cocktail(
    val name: String,
    val imgId: String?,
    val description: String?,
    val ingredients: List<Ingredient>,
    val recipe: String?
)