package com.example.feed.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.PostModel
import com.example.domain.model.Tier
import com.example.feed.R
import com.example.feed.databinding.ItemPostBinding
import com.example.feed.databinding.ItemTiersBinding
import com.example.feed.presentation.holder.PostViewHolder
import com.example.feed.presentation.holder.TiersViewHolder

class PostAdapter(
    private val onPostClick: (Long) -> Unit,
    private val onTierClick: (Tier) -> Unit
) : PagingDataAdapter<PostModel, RecyclerView.ViewHolder>(POST_COMPARATOR) {

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
            getItem(position - 1)?.let { holder.onBind(it) }
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

        private val POST_COMPARATOR = object : DiffUtil.ItemCallback<PostModel>() {
            override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel) =
                oldItem.title == newItem.title
                        && oldItem.image == newItem.image
                        && oldItem.category == newItem.category
        }
    }
}