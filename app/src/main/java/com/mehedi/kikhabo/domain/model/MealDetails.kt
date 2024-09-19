package com.mehedi.kikhabo.domain.model

/**
Created by Masum Mehedi on 9/19/2024.
masumehedissl@gmail.com
 **/
data class MealDetails(
    val id: String,
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumbnailUrl: String,
    val tags: List<String>,
    val youtubeUrl: String,
    val ingredients: List<String>,
    val measures: List<String>
)