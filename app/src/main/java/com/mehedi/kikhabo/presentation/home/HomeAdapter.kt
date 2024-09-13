package com.mehedi.kikhabo.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mehedi.kikhabo.databinding.ItemMealGirdBinding
import com.mehedi.kikhabo.domain.model.MealItem

class HomeAdapter : ListAdapter<MealItem, HomeAdapter.HomeViewHolder>(
    COMPARATOR
) {
    
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        
        return HomeViewHolder(
            ItemMealGirdBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    
    
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.binding.meal = getItem(position)
        
    }
    
    inner class HomeViewHolder(val binding: ItemMealGirdBinding) :
        RecyclerView.ViewHolder(binding.root)
    
    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<MealItem>() {
            override fun areItemsTheSame(oldItem: MealItem, newItem: MealItem): Boolean {
                return oldItem == newItem
            }
            
            override fun areContentsTheSame(oldItem: MealItem, newItem: MealItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
        
        
    }
    
    
}