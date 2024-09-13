package com.example.feed.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Tier
import com.example.feed.databinding.ItemTiersBinding

class TiersViewHolder(
    private val binding: ItemTiersBinding,
    private val onTierClick: (Tier) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind() {
        binding.tierBtnGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) onTierClick(Tier.entries[checkedId])
        }
    }
}