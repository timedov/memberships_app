package com.example.ui.view.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.common.utils.loadCaching
import com.example.ui.R
import com.example.ui.databinding.ItemPostBinding
import com.example.ui.model.PostUiModel

class PostViewHolder(
    private val binding: ItemPostBinding,
    private val onClick: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: PostUiModel) {
        with(binding) {
            authorTv.text = post.author
            postedAgoTv.text = post.postedAgo
            titleTv.text = post.title
            postImageIv.loadCaching(post.image)
            bodyTv.text = post.body
            infoTv.text = binding.root.context
                .getString(R.string.post_info, post.category, post.viewsCount.toString())
            commentCountTv.text = post.commentsCount.toString()
            root.setOnClickListener { onClick(post.id) }
        }
    }
}