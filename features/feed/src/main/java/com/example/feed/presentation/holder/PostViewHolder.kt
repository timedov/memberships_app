package com.example.feed.presentation.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.common.utils.loadCaching
import com.example.domain.model.PostModel
import com.example.feed.databinding.ItemPostBinding

class PostViewHolder(
    private val binding: ItemPostBinding,
    private val onClick: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(post: PostModel) {
        with(binding) {
            titleTv.text = post.title
            categoryTv.text = post.category
            postImageIv.loadCaching(post.image)
            videosCountTv.text = post.videosCount.toString()
            postsCountTv.text = post.postsCount.toString()
            root.setOnClickListener { onClick(post.id) }
        }
    }
}