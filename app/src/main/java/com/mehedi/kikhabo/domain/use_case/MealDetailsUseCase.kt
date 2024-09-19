package com.mehedi.kikhabo.domain.use_case

import android.util.Log
import com.mehedi.kikhabo.core.Resource
import com.mehedi.kikhabo.data.dto.toDomain
import com.mehedi.kikhabo.data.dto.toMealItem
import com.mehedi.kikhabo.domain.model.MealDetails
import com.mehedi.kikhabo.domain.model.MealItem
import com.mehedi.kikhabo.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(private val repository: MealRepository) {


    operator fun invoke(mealId: String): Flow<Resource<List<MealDetails?>?>> = flow {
        val data = repository.getMealDetails(mealId)
        try {
            emit(Resource.Loading())
            val data = repository.getMealDetails(mealId)

            if (data.isSuccessful && data.body() != null) {
                val ml = data.body()!!.meals
                val mlItem = ml.map { it.toDomain() }
                emit(Resource.Success(mlItem))
            }


        } catch (e: Exception) {
            Log.d("Exception", "invoke: ${e.message}")
            emit(Resource.Error(e.message ?: "Some Error"))
        }


    }


}