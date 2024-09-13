package com.mehedi.kikhabo.presentation.home

import com.mehedi.kikhabo.domain.model.MealItem

data class HomeDataState(
    val loading: Boolean = false,
    val homeData: List<MealItem?>? = null,
    val error: String? = null
)