package com.example.postdetails.presentation

import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.example.common.di.ComponentDepsProvider
import com.example.postdetails.di.DaggerPostDetailsComponent
import com.example.postdetails.presentation.composables.PostDetailsScreen
import com.example.postdetails.presentation.model.PostDetailsEvent
import com.example.ui.base.BaseFragment
import com.example.ui.themes.ForBoostAppTheme
import com.example.ui.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class PostDetailsFragment : BaseFragment() {

    @Inject
    lateinit var vmFactory: ViewModelProviderFactory

    private val viewModel: PostDetailsViewModel by viewModels { vmFactory }

    override fun provideComposeView(): ComposeView =
        ComposeView(requireContext()).apply {
            setContent {
                ForBoostAppTheme { PostDetailsScreen(viewModel) }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerPostDetailsComponent.factory()
            .create(ComponentDepsProvider.get(requireContext()))
            .apply { inject(this@PostDetailsFragment) }

        viewModel.obtainEvent(
            PostDetailsEvent.Initiate(
                postId = arguments?.getLong(ARG_POST_ID) ?: -1L,
                authorName = arguments?.getString(ARG_AUTHOR_NAME) ?: "bebalab"
            )
        )
    }

    companion object {

        private const val ARG_POST_ID = "postId"
        private const val ARG_AUTHOR_NAME = "authorName"
    }
}
