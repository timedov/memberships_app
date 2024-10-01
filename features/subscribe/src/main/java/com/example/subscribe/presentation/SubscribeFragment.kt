package com.example.subscribe.presentation

import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.example.common.di.ComponentDepsProvider
import com.example.subscribe.di.DaggerSubscribeComponent
import com.example.subscribe.presentation.composables.SubscribeScreen
import com.example.subscribe.presentation.model.SubscribeEvent
import com.example.ui.base.BaseFragment
import com.example.ui.themes.ForBoostAppTheme
import com.example.ui.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class SubscribeFragment : BaseFragment() {

    @Inject
    lateinit var vmFactory: ViewModelProviderFactory

    private val viewModel: SubscribeViewModel by viewModels { vmFactory }

    override fun provideComposeView(): ComposeView =
        ComposeView(requireContext()).apply {
            setContent {
                ForBoostAppTheme { SubscribeScreen(viewModel) }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerSubscribeComponent.factory()
            .create(ComponentDepsProvider.get(requireContext()))
            .apply { inject(this@SubscribeFragment) }

        viewModel.obtainEvent(
            SubscribeEvent.Initiate(username = arguments?.getString(USERNAME).orEmpty())
        )
    }

    companion object {

        private const val USERNAME = "username"

        fun newInstance(username: String) = SubscribeFragment().apply {
            arguments = Bundle().apply {
                putString(USERNAME, username)
            }
        }
    }
}