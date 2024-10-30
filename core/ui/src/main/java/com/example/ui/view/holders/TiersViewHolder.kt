package com.example.ui.view.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.common.model.TierType
import com.example.ui.databinding.ItemTiersBinding

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