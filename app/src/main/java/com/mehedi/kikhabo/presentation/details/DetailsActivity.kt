package com.mehedi.kikhabo.presentation.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.mehedi.kikhabo.core.common.gone
import com.mehedi.kikhabo.core.common.visible
import com.mehedi.kikhabo.databinding.ActivityDetailsBinding
import com.mehedi.kikhabo.domain.model.MealDetails
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    val TAG: String = DetailsActivity::class.java.simpleName
    
    companion object {
        const val MEAL_ITEM_KEY = "MealItemKey"
        
    }
    
    
    private val viewModel: DetailsViewModel by viewModels()
    
    private lateinit var binding: ActivityDetailsBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        
        lifecycleScope.launch {
            viewModel.detailsMeal.collect { dataState ->
                
                if (dataState.loading) {
                    binding.progress.visible()
                    
                }
                
                if (dataState.error != null) {
                    binding.progress.gone()
                    
                }
                
                if ((dataState.homeData?.size ?: 0) != 0) {
                    binding.progress.gone()
                    setUiData(dataState.homeData?.get(0))
                    
                    Log.d(TAG, "onCreate: ${dataState.homeData}")
                    
                    //mealAdapter.submitList(homeDataState.homeData)
                }
                
                
            }
        }
        intent.getStringExtra(MEAL_ITEM_KEY)?.let { mealId ->
            viewModel.getMealDetailsData(mealId)
            Log.d(TAG, "mealId: ${mealId}")
        }
        
    }
    
    private fun setUiData(homeData: MealDetails?) {
        homeData?.let { recipe ->
            
            binding.apply {
                Glide.with(this@DetailsActivity).load(recipe.thumbnailUrl).into(ivMealThumbnail)
                tvMealName.text = recipe.name
                tvCategory.text = "Category: ${recipe.category}"
                tvArea.text = "Area: ${recipe.area}"
                tvInstructions.text = "${recipe.instructions}"
                setIngredients(recipe.ingredients)
                setTagsData(recipe.tags)
                
            }
            
            
        }
        
        
    }
    
    private fun setTagsData(tags: List<String>) {
        tags.forEach { tag ->
            val ingredientItem = Chip(this)
            ingredientItem.text = tag
            ingredientItem.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            
            binding.cgTags.addView(ingredientItem)
            
        }
        
        
    }
    
    @SuppressLint("ResourceAsColor")
    private fun setIngredients(ingredients: List<String>) {
        
        
        ingredients.forEachIndexed { index, ingredient ->
            val ingredientItem = TextView(this)
            ingredientItem.text = " ${index + 1}.$ingredient "
            ingredientItem.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            
            binding.ingredientsLayout.addView(ingredientItem)
            
        }
        
        
    }
    
    
}