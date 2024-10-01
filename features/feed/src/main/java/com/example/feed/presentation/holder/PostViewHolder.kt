package com.example.feed.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.common.utils.loadCaching
import com.example.feed.databinding.ItemPostBinding
import com.example.ui.model.PostUiModel

class PostViewHolder(
    private val binding: ItemPostBinding,
    private val onClick: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(post: PostUiModel) {
        with(binding) {
            titleTv.text = post.title
            categoryTv.text = post.category
            postImageIv.loadCaching(post.image)
            authorTv.text = post.author
            postedAgoTv.text = post.postedAgo
            root.setOnClickListener { onClick(post.id) }
        }
    }
}