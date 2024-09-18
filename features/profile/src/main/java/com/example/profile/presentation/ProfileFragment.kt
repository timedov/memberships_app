package com.example.profile.presentation

import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.example.common.di.ComponentDepsProvider
import com.example.profile.di.DaggerProfileComponent
import com.example.profile.di.ProfileComponent
import com.example.profile.presentation.composables.ProfileScreen
import com.example.ui.base.BaseFragment
import com.example.ui.themes.ForBoostAppTheme
import com.example.ui.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class ProfileFragment : BaseFragment() {

    private var profileComponent: ProfileComponent? = null

    @Inject
    lateinit var vmFactory: ProfileViewModel.Factory

    private val viewModel: ProfileViewModel by viewModels {
        ProfileViewModel.provideFactory(vmFactory, "User")
    }

    override fun provideComposeView(): ComposeView {
        return ComposeView(requireContext()).apply {
            setContent {
                ForBoostAppTheme { ProfileScreen(viewModel) }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profileComponent = DaggerProfileComponent.factory()
            .create(ComponentDepsProvider.get(requireContext()))
            .apply { inject(this@ProfileFragment) }
    }
}