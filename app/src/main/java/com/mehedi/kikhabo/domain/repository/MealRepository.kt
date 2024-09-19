package com.mehedi.kikhabo.domain.repository

import com.mehedi.kikhabo.data.dto.ResponseMealDTO
import com.mehedi.kikhabo.data.dto.ResponseMealsDtoDetails
import retrofit2.Response

interface MealRepository {

    suspend fun getMealList(mealName: String): Response<ResponseMealDTO>
    suspend fun getMealDetails(mealId: String): Response<ResponseMealsDtoDetails>

}