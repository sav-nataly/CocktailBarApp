package ru.savina.cocktailbar.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.savina.cocktailbar.data.Cocktail

class CocktailViewModel : ViewModel() {
    private val mutableCocktailList: MutableList<Cocktail> = mutableListOf()
    private val mutableSelectedCocktail = MutableLiveData<Cocktail>()

    val cocktailList: MutableList<Cocktail> get() = mutableCocktailList
    val selectedCocktail: LiveData<Cocktail> get() = mutableSelectedCocktail

    fun selectCocktail(cocktail: Cocktail) {
        mutableSelectedCocktail.value = cocktail
    }

}