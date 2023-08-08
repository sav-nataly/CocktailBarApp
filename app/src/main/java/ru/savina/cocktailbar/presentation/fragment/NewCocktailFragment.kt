package ru.savina.cocktailbar.presentation.fragment

import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.savina.cocktailbar.R
import ru.savina.cocktailbar.data.Cocktail
import ru.savina.cocktailbar.data.Ingredient
import ru.savina.cocktailbar.databinding.FragmentNewCocktailBinding
import ru.savina.cocktailbar.domain.serialize
import ru.savina.cocktailbar.domain.writeFile
import ru.savina.cocktailbar.domain.writeImage
import ru.savina.cocktailbar.presentation.adapter.IngredientsAdapter
import ru.savina.cocktailbar.presentation.viewmodel.CocktailViewModel
import java.util.UUID

class NewCocktailFragment : Fragment(), IngredientInputDialogFragment.IngredientInputInterface {
    private lateinit var binding: FragmentNewCocktailBinding
    private val viewModel: CocktailViewModel by activityViewModels()
    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>

    private val ingredientsAdapter: IngredientsAdapter = IngredientsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewCocktailBinding.inflate(inflater, container, false)
        pickMedia =
            this.registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                if (uri != null) {
                    photoChosen(uri)
                }
            }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.ingredientsRv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ingredientsAdapter
        }

        binding.saveBtn.setOnClickListener {
            var imageName = ""

            if (binding.cocktailCardIv.drawable != null) {
                imageName = UUID.randomUUID().toString() + ".jpg"
                val bitmap = Bitmap.createBitmap(
                    binding.cocktailCardIv.width,
                    binding.cocktailCardIv.height,
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(bitmap)
                binding.cocktailCardIv.draw(canvas)
                context?.let { it1 -> writeImage(it1, bitmap, imageName) }
            }
            var cocktail = Cocktail(
                binding.cocktailNameTv.editText?.text.toString(),
                imageName,
                binding.cocktailDescriptionTv.editText?.text.toString(),
                ingredientsAdapter.ingredients,
                binding.cocktailRecipeTv.editText?.text.toString()
            )

            viewModel.cocktailList.add(cocktail)
            context?.let { it1 ->
                writeFile(
                    it1,
                    serialize(viewModel.cocktailList),
                    "cocktails.json"
                )
            }
            findNavController().navigate(R.id.action_newCocktailFragment_to_cocktailListFragment)
        }

        binding.cancelBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.cocktailCardIv.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.addIngredientBtn.setOnClickListener {
            IngredientInputDialogFragment(this).show(
                childFragmentManager, "")
        }
    }

    private fun photoChosen(uri: Uri) {
        binding.cocktailCardIv.setImageURI(uri)
    }

    override fun addIngredient(input: String) {
        if (input.length > 0)
            ingredientsAdapter.add(Ingredient(input))
    }
}