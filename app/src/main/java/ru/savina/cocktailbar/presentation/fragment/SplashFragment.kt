package ru.savina.cocktailbar.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.savina.cocktailbar.R
import ru.savina.cocktailbar.databinding.FragmentSplashBinding
import ru.savina.cocktailbar.presentation.viewmodel.CocktailViewModel

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel: CocktailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.cocktailList.isEmpty()) {
            Log.e("viewmodel", viewModel.cocktailList.size.toString())
            findNavController().navigate(R.id.action_splashFragment_to_emptyListFragment)
        } else {
            findNavController().navigate(R.id.action_splashFragment_to_cocktailListFragment)
        }
    }
}