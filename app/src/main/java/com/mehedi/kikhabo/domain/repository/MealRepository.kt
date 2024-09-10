package com.mehedi.kikhabo.domain.repository

import com.mehedi.kikhabo.data.dto.ResponseMealDTO
import retrofit2.Response

interface MealRepository {
  
   suspend fun getMealList(mealName: String): Response<ResponseMealDTO>
    
}