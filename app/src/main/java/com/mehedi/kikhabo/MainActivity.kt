package com.mehedi.kikhabo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mehedi.kikhabo.core.common.gone
import com.mehedi.kikhabo.core.common.visible
import com.mehedi.kikhabo.databinding.ActivityMainBinding
import com.mehedi.kikhabo.presentation.home.HomeAdapter
import com.mehedi.kikhabo.presentation.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    private val viewModel: HomeViewModel by viewModels()
    
    private val mealAdapter: HomeAdapter by lazy {
        HomeAdapter()
    }
    
    lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.myMealListRecyclerview.apply {
            adapter = mealAdapter
            
        }
        
        
        
        
        
        lifecycleScope.launch {
            viewModel.homeMeal.collect { homeDataState ->
                
                if (homeDataState.loading) {
                    binding.progress.visible()
                    
                }
                
                if (homeDataState.error != null) {
                    binding.progress.gone()
                    
                }
                
                if ((homeDataState.homeData?.size ?: 0) != 0) {
                    binding.progress.gone()
                    mealAdapter.submitList(homeDataState.homeData)
                }
                
                
            }
        }
        
        
        
        
        viewModel.getMealData("Dessert")
        
        
    }
}