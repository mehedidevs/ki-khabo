package com.mehedi.kikhabo.presentation.details

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.mehedi.kikhabo.R
import com.mehedi.kikhabo.core.common.gone
import com.mehedi.kikhabo.core.common.visible
import com.mehedi.kikhabo.databinding.ActivityDetailsBinding
import com.mehedi.kikhabo.domain.model.MealItem
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


}