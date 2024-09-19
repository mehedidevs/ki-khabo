package com.mehedi.kikhabo.presentation.details

import com.mehedi.kikhabo.domain.model.MealDetails

data class DetailsDataState(
    val loading: Boolean = false,
    val homeData: List<MealDetails?>? = null,
    val error: String? = null
)