package ru.savina.cocktailbar.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.savina.cocktailbar.R
import ru.savina.cocktailbar.databinding.FragmentCocktailCardBinding
import ru.savina.cocktailbar.presentation.viewmodel.CocktailViewModel
import java.io.File

class CocktailCardFragment : Fragment() {
    private lateinit var binding: FragmentCocktailCardBinding
    private val viewModel: CocktailViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCocktailCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cocktailNameTv.text = viewModel.selectedCocktail.value?.name;
        if (viewModel.selectedCocktail.value?.imgId?.length!! > 0)
            binding.cocktailIv.setImageURI(
                Uri.fromFile(
                    File(
                        view.context.filesDir.path.plus("/")
                            .plus(viewModel.selectedCocktail.value?.imgId)
                    )
                )
            )
        else
            binding.cocktailIv.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.baseline_image_24,
                    null
                )
            )
        binding.cocktailDescriptionTv.text = viewModel.selectedCocktail.value?.description;
        binding.cocktailRecipeTv.text = viewModel.selectedCocktail.value?.recipe;


        if (viewModel.selectedCocktail.value?.ingredients?.size!! > 0) {
            var str: String = ""
            for (ingredient in viewModel.selectedCocktail.value?.ingredients!!) {
                str = str.plus(ingredient.description).plus("\n")
            }
            binding.cocktailIngredientsTv.text = str
        }
    }
}