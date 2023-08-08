package ru.savina.cocktailbar.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import ru.savina.cocktailbar.databinding.ViewIngredientLayoutBinding

class IngredientView(context: Context, attrs: AttributeSet?) : CardView(context, attrs) {

    val binding = ViewIngredientLayoutBinding.inflate(LayoutInflater.from(context), this, false)

}