package com.mehedi.kikhabo.data.remote

import com.mehedi.kikhabo.data.dto.ResponseMealDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {
    
    @GET("json/v1/1/filter.php")
    suspend fun getMealList(
        @Query("c") mealName: String
    ): Response<ResponseMealDTO>
    
    
}