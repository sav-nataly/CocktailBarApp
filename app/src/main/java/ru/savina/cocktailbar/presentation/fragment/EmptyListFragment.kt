package ru.savina.cocktailbar.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.savina.cocktailbar.R
import ru.savina.cocktailbar.databinding.FragmentEmptyListBinding

class EmptyListFragment : Fragment() {
    private lateinit var binding: FragmentEmptyListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmptyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.emptyAddCocktailBtn.setOnClickListener {
            findNavController().navigate(R.id.action_emptyListFragment_to_newCocktailFragment)
        }
    }
}