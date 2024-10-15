package com.example.commentreplies.presentation

import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.example.commentreplies.presentation.composables.CommentRepliesScreen
import com.example.commentreplies.presentation.model.CommentRepliesEvent
import com.example.common.di.ComponentDepsProvider
import com.example.commentreplies.di.DaggerCommentRepliesComponent
import com.example.ui.base.BaseFragment
import com.example.ui.themes.ForBoostAppTheme
import com.example.ui.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class CommentRepliesFragment : BaseFragment() {

    @Inject
    lateinit var vmFactory: ViewModelProviderFactory

    private val viewModel: CommentRepliesViewModel by viewModels { vmFactory }

    override fun provideComposeView(): ComposeView =
        ComposeView(requireContext()).apply {
            setContent {
                ForBoostAppTheme { CommentRepliesScreen(viewModel) }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerCommentRepliesComponent.factory()
            .create(ComponentDepsProvider.get(requireContext()))
            .apply { inject(this@CommentRepliesFragment) }

        viewModel.obtainEvent(CommentRepliesEvent.Initiate(
            commentId = arguments?.getString(PARENT_COMMENT_ID).orEmpty()
        ))
    }

    companion object {

        const val PARENT_COMMENT_ID = "parentCommentId"
    }
}
