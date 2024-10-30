package com.example.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.ui.databinding.ItemLoadStateBinding
import com.example.ui.view.holders.LoadStateViewHolder

class PostLoadStateAdapter(
    private val onRetryClick: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = LoadStateViewHolder(
        ItemLoadStateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onRetryClick
    )

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}