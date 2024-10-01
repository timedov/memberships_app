package com.example.feed.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.TierType
import com.example.feed.databinding.ItemTiersBinding

class TiersViewHolder(
    private val binding: ItemTiersBinding,
    private val onTierClick: (TierType) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind() {
        binding.tierBtnGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) onTierClick(TierType.entries[checkedId])
        }
    }
}