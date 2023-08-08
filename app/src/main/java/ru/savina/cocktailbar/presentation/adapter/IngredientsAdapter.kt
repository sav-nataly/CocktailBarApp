package ru.savina.cocktailbar.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.savina.cocktailbar.data.Ingredient
import ru.savina.cocktailbar.databinding.ViewIngredientLayoutBinding

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {
    var ingredients = mutableListOf<Ingredient>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val binding = ViewIngredientLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return IngredientsViewHolder(binding)
    }

    override fun getItemCount(): Int = ingredients.size

    fun update(data: List<Ingredient>) {
        ingredients.addAll(data)
        notifyDataSetChanged()
    }

    fun add(ingredient: Ingredient) {
        ingredients.add(ingredient)
        notifyItemInserted(ingredients.size - 1)
    }
    private fun delete(position: Int) {
        ingredients.removeAt(position)
        notifyItemRemoved(position)
    }
    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val data = ingredients[position]
        holder.ingredientTv.text = data.description
        holder.deleteBtn.setOnClickListener { delete(position) }
    }

    inner class IngredientsViewHolder(
        binding: ViewIngredientLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val ingredientTv = binding.ingredientTv
        val deleteBtn = binding.deleteIngredientBtn

    }

}