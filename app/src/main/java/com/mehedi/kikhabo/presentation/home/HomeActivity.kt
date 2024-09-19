package com.mehedi.kikhabo.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mehedi.kikhabo.core.common.gone
import com.mehedi.kikhabo.core.common.visible
import com.mehedi.kikhabo.databinding.ActivityHomeBinding
import com.mehedi.kikhabo.presentation.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), HomeAdapter.MealClickListener {

    private val viewModel: HomeViewModel by viewModels()

    private val mealAdapter: HomeAdapter by lazy {
        HomeAdapter(this)
    }

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
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

    override fun onMealItemClick(mealId: String) {

        val detailsIntent = Intent(this@HomeActivity, DetailsActivity::class.java)
        detailsIntent.putExtra(DetailsActivity.MEAL_ITEM_KEY, mealId)
        startActivity(detailsIntent)


    }
}