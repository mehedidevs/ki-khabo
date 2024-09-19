package com.mehedi.kikhabo.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedi.kikhabo.core.Resource
import com.mehedi.kikhabo.domain.use_case.GetMealDetailsUseCase
import com.mehedi.kikhabo.domain.use_case.GetMealListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val useCase: GetMealDetailsUseCase) :
    ViewModel() {

    private val _homeMeal = MutableStateFlow(DetailsDataState())
    val detailsMeal: StateFlow<DetailsDataState>
        get() = _homeMeal


    fun getMealDetailsData(mealId: String) {

        viewModelScope.launch {
            useCase.invoke(mealId).collect { response ->

                when (response) {
                    is Resource.Error -> {
                        _homeMeal.value = DetailsDataState(error = response.message)
                    }

                    is Resource.Loading -> {
                        _homeMeal.value = DetailsDataState(loading = true)
                    }

                    is Resource.Success -> {
                        _homeMeal.value = DetailsDataState(homeData = response.data)


                    }
                }


            }

        }


    }


}