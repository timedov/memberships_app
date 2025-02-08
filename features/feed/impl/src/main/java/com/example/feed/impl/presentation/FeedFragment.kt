package com.example.feed.impl.presentation

import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.example.common.di.ComponentDepsProvider
import com.example.feed.impl.di.DaggerFeedComponent
import com.example.feed.impl.presentation.composable.FeedScreen
import com.example.ui.base.BaseFragment
import com.example.ui.themes.ForBoostAppTheme
import com.example.ui.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

internal class FeedFragment : BaseFragment() {

    @Inject
    lateinit var vmFactory: ViewModelProviderFactory

    private val viewModel: FeedViewModel by viewModels { vmFactory }

    override fun provideComposeView(): ComposeView =
        ComposeView(requireContext()).apply {
            setContent {
                ForBoostAppTheme { FeedScreen(viewModel) }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerFeedComponent.factory()
            .create(ComponentDepsProvider.get(requireContext()))
            .apply { inject(this@FeedFragment) }
    }
}