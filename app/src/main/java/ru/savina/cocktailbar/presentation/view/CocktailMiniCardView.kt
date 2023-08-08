package ru.savina.cocktailbar.presentation.view

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.core.content.withStyledAttributes
import ru.savina.cocktailbar.R
import ru.savina.cocktailbar.databinding.CocktailMiniCardViewBinding
import java.io.File

class CocktailMiniCardView constructor(
    context: Context,
    attrs: AttributeSet? = null
) : CardView(context, attrs) {
    val binding = CocktailMiniCardViewBinding.inflate(LayoutInflater.from(context), this, false)
}