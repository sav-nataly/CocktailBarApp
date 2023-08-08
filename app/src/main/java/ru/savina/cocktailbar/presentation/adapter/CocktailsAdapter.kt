package ru.savina.cocktailbar.presentation.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import ru.savina.cocktailbar.R
import ru.savina.cocktailbar.data.Cocktail
import ru.savina.cocktailbar.databinding.CocktailMiniCardViewBinding
import java.io.File

class CocktailsAdapter(private val onItemClicked: (Cocktail) -> Unit) :
    RecyclerView.Adapter<CocktailsAdapter.CocktailsViewHolder>() {
    private var cocktails: List<Cocktail> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailsViewHolder {
        val binding = CocktailMiniCardViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CocktailsViewHolder(binding) {
            onItemClicked(cocktails[it])
        }
    }

    override fun getItemCount(): Int = cocktails.size

    fun update(data: List<Cocktail>) {
        cocktails = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CocktailsViewHolder, position: Int) {
        val data = cocktails[position]
        holder.nameTv.text = data.name
        if (data.imgId?.length!! > 0)
            holder.imageIv.setImageURI(
                Uri.fromFile(
                    File(
                        holder.itemView.context.filesDir.path.plus(
                            "/"
                        ).plus(data.imgId)
                    )
                )
            )
        else
            holder.imageIv.setImageDrawable(
                ResourcesCompat.getDrawable(
                    holder.itemView.resources,
                    R.drawable.baseline_image_24, null
                )
            )
    }

    inner class CocktailsViewHolder(
        binding: CocktailMiniCardViewBinding,
        tapAtPosition: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        val nameTv = binding.cocktailNameTv
        val imageIv = binding.cocktailIv

        init {
            itemView.setOnClickListener {
                tapAtPosition(adapterPosition)
            }
        }
    }
}