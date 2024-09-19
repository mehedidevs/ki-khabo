package com.mehedi.kikhabo.data.remote

import com.mehedi.kikhabo.data.dto.ResponseMealDTO
import com.mehedi.kikhabo.data.dto.ResponseMealsDtoDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    @GET("filter.php")
    suspend fun getMealList(
        @Query("c") mealName: String
    ): Response<ResponseMealDTO>

    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") mealId: String
    ): Response<ResponseMealsDtoDetails>


}