package com.example.ui.view.holders

import android.util.Log
import android.view.View
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.databinding.ItemLoadStateBinding
import com.example.ui.view.composables.ErrorScreen
import com.example.ui.view.composables.LoadingScreen

class LoadStateViewHolder(
    private val binding: ItemLoadStateBinding,
    private val onRetryClick: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        with(binding) {
            when (loadState) {
                is LoadState.Error -> {
                    loadingErrorCompose.setContent { ErrorScreen(onRetryClick = onRetryClick) }
                    loadingErrorCompose.visibility = View.VISIBLE
                    Log.e("LoadStateViewHolder", loadState.error.toString())
                }

                is LoadState.Loading -> {
                    loadingErrorCompose.setContent { LoadingScreen(isLoading = true) }
                    loadingErrorCompose.visibility = View.VISIBLE
                    Log.d("LoadStateViewHolder", "Loading")
                }

                else -> loadingErrorCompose.visibility = View.GONE
            }
        }
    }
}
