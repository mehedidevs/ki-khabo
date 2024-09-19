package com.mehedi.kikhabo.data.repository_impl

import com.mehedi.kikhabo.data.dto.ResponseMealDTO
import com.mehedi.kikhabo.data.dto.ResponseMealsDtoDetails
import com.mehedi.kikhabo.data.remote.MealApiService
import com.mehedi.kikhabo.domain.repository.MealRepository
import retrofit2.Response
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val apiService: MealApiService
) : MealRepository {
        override suspend fun getMealList(mealName: String): Response<ResponseMealDTO> {
        val response = apiService.getMealList(mealName)
        return response
        
    }

    override suspend fun getMealDetails(mealId: String): Response<ResponseMealsDtoDetails> {
        val response = apiService.getMealDetails(mealId)
        return response

    }
}