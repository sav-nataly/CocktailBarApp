package ru.savina.cocktailbar.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import ru.savina.cocktailbar.R
import ru.savina.cocktailbar.data.Cocktail
import ru.savina.cocktailbar.databinding.FragmentCocktailListBinding
import ru.savina.cocktailbar.presentation.adapter.CocktailsAdapter
import ru.savina.cocktailbar.presentation.viewmodel.CocktailViewModel

class CocktailListFragment : Fragment() {
    private lateinit var binding: FragmentCocktailListBinding

    private val viewModel: CocktailViewModel by activityViewModels()
    private val cocktailsAdapter = CocktailsAdapter() {
        onCocktailClicked(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCocktailListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addCocktailBtn.setOnClickListener {
            findNavController().navigate(R.id.action_cocktailListFragment_to_newCocktailFragment)
        }

        with(binding.cocktailRv) {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = cocktailsAdapter
        }
        cocktailsAdapter.update(viewModel.cocktailList)
    }

    private fun onCocktailClicked(cocktail: Cocktail) {
        viewModel.selectCocktail(cocktail)
        findNavController().navigate(R.id.action_cocktailListFragment_to_cocktailCardFragment)
    }
}