package com.mehedi.kikhabo.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedi.kikhabo.core.Resource
import com.mehedi.kikhabo.domain.use_case.GetMealListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: GetMealListUseCase) : ViewModel() {
    
    
    fun getMealData(mealName: String) {
        
        viewModelScope.launch {
            
            
            useCase.invoke(mealName).collect { response ->
                
                when (response) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        Log.d("getMealData", "getMealData: ${response.data} ")
                        
                        
                    }
                }
                
                
            }
            
        }
        
        
    }
    
    
}