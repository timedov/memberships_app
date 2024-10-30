package com.example.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common.model.TierType
import com.example.ui.view.holders.PostViewHolder
import com.example.ui.databinding.ItemPostBinding
import com.example.ui.model.PostUiModel
import com.example.ui.R
import com.example.ui.databinding.ItemTiersBinding
import com.example.ui.view.holders.TiersViewHolder

class PostPagingDataAdapter(
    private val onPostClick: (Long) -> Unit,
    private val onTierClick: (TierType) -> Unit
) : PagingDataAdapter<PostUiModel, RecyclerView.ViewHolder>(POST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.item_post -> PostViewHolder(
                ItemPostBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onPostClick
            )
            R.layout.item_tiers -> TiersViewHolder(
                ItemTiersBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onTierClick
            )
            else -> throw NoSuchElementException("No such view type: $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PostViewHolder) {
            getItem(position - 1)?.let { holder.bind(it) }
        } else if (holder is TiersViewHolder) {
            holder.onBind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            TIERS_TYPE -> R.layout.item_tiers
            else -> R.layout.item_post
        }
    }

    companion object {

        const val TIERS_TYPE = 0

        private val POST_COMPARATOR = object : DiffUtil.ItemCallback<PostUiModel>() {
            override fun areItemsTheSame(oldItem: PostUiModel, newItem: PostUiModel) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: PostUiModel, newItem: PostUiModel) =
                oldItem.id == newItem.id
        }
    }
}