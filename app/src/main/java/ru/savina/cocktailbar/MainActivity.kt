package ru.savina.cocktailbar

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.savina.cocktailbar.data.Cocktail
import ru.savina.cocktailbar.domain.getAllCocktails
import ru.savina.cocktailbar.presentation.viewmodel.CocktailViewModel


class MainActivity : AppCompatActivity() {
    private val viewModel: CocktailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initCocktails()
        setContentView(R.layout.activity_main)
    }

    private fun initCocktails() {
        var cocktailList: List<Cocktail> = getAllCocktails(this)
        viewModel.cocktailList.addAll(cocktailList)
    }
}