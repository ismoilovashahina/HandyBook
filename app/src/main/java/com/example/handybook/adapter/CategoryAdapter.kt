package com.example.handybook.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.handybook.books.Categories
import com.example.handybook.databinding.ItemCategoryBinding

class CategoryAdapter(var categories:Array<Categories>, var onClick: MyCategoryInterface) : RecyclerView.Adapter<CategoryAdapter.CategoriesHolder>(){
    var selectedPos = -1

    class CategoriesHolder(binding: ItemCategoryBinding):RecyclerView.ViewHolder(binding.root){
        var category_main = binding.categoryCard
        var category_text = binding.categoryName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        return CategoriesHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        var category = categories[position]
        holder.category_text.text = category.categoryName

        if(selectedPos == position){
            if (category.status){
                category.status = false
                holder.category_main.setCardBackgroundColor(Color.parseColor("#03255c"))
                holder.category_text.setTextColor(Color.WHITE)
            }
        }
        else{
            category.status = true
            holder.category_main.setCardBackgroundColor(Color.WHITE)
            holder.category_text.setTextColor(Color.parseColor("#03255c"))
        }

        holder.category_main.setOnClickListener {

            onClick.onItemClick(category, position)
            notifyItemChanged(selectedPos);
            selectedPos = position
            notifyItemChanged(selectedPos);
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    interface MyCategoryInterface{
        fun onItemClick(category: Categories, position: Int)
    }
}