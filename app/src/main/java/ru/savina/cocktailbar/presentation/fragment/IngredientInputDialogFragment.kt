package ru.savina.cocktailbar.presentation.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import ru.savina.cocktailbar.databinding.FragmentIngredientInputDialogBinding

class IngredientInputDialogFragment(handler: IngredientInputInterface): DialogFragment() {
    private val clickHandler = handler
    private lateinit var binding: FragmentIngredientInputDialogBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentIngredientInputDialogBinding.inflate(LayoutInflater.from(context))
        binding.addBtn.setOnClickListener {
            clickHandler.addIngredient(binding.ingredientTil.editText?.text.toString())
            dialog?.dismiss()
        }

        binding.cancelBtn.setOnClickListener {
            dialog?.cancel()
        }
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root).create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    interface IngredientInputInterface {
        fun addIngredient(input: String)
    }
}