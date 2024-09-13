package com.mehedi.kikhabo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedi.kikhabo.core.Resource
import com.mehedi.kikhabo.domain.use_case.GetMealListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: GetMealListUseCase) : ViewModel() {
    
    private val _homeMeal = MutableStateFlow(HomeDataState())
    val homeMeal: StateFlow<HomeDataState>
        get() = _homeMeal
    
    
    fun getMealData(mealName: String) {
        
        viewModelScope.launch {
            useCase.invoke(mealName).collect { response ->
                
                when (response) {
                    is Resource.Error -> {
                        _homeMeal.value = HomeDataState(error = response.message)
                    }
                    
                    is Resource.Loading -> {
                        _homeMeal.value = HomeDataState(loading = true)
                    }
                    
                    is Resource.Success -> {
                        _homeMeal.value = HomeDataState(homeData = response.data)
                     
                        
                    }
                }
                
                
            }
            
        }
        
        
    }
    
    
}