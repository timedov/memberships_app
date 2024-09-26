package com.example.subscribe.presentation

import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.example.common.di.ComponentDepsProvider
import com.example.subscribe.di.DaggerSubscribeComponent
import com.example.subscribe.di.SubscribeComponent
import com.example.subscribe.presentation.composables.SubscribeScreen
import com.example.ui.base.BaseFragment
import com.example.ui.themes.ForBoostAppTheme
import javax.inject.Inject

class SubscribeFragment : BaseFragment() {

    private var subscribeComponent: SubscribeComponent? = null

    @Inject
    lateinit var vmFactory: SubscribeViewModel.Factory

    private val viewModel: SubscribeViewModel by viewModels {
        SubscribeViewModel.provideFactory(vmFactory, "User")
    }

    override fun provideComposeView(): ComposeView =
        ComposeView(requireContext()).apply {
            setContent {
                ForBoostAppTheme { SubscribeScreen(viewModel) }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeComponent = DaggerSubscribeComponent.factory()
            .create(ComponentDepsProvider.get(requireContext()))
            .apply { inject(this@SubscribeFragment) }
    }
}