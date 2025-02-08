package com.example.profile.impl.presentation

import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.example.common.di.ComponentDepsProvider
import com.example.profile.impl.di.DaggerProfileComponent
import com.example.profile.impl.presentation.composables.ProfileScreen
import com.example.profile.impl.presentation.model.ProfileEvent
import com.example.ui.base.BaseFragment
import com.example.ui.themes.ForBoostAppTheme
import com.example.ui.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

internal class ProfileFragment : BaseFragment() {

    @Inject
    lateinit var vmFactory: ViewModelProviderFactory

    private val viewModel: ProfileViewModel by viewModels { vmFactory }

    override fun provideComposeView(): ComposeView =
        ComposeView(requireContext()).apply {
            setContent {
                ForBoostAppTheme { ProfileScreen(viewModel) }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerProfileComponent.factory()
            .create(ComponentDepsProvider.get(requireContext()))
            .apply { inject(this@ProfileFragment) }

        viewModel.obtainEvent(ProfileEvent.Initiate(arguments?.getString(USERNAME).orEmpty()))
    }

    companion object {

        private const val USERNAME = "username"
    }
}