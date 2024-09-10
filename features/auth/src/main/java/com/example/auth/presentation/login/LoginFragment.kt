package com.example.auth.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import com.example.auth.di.AuthComponent
import com.example.auth.di.DaggerAuthComponent
import com.example.common.di.ComponentDepsProvider
import com.example.ui.base.BaseFragment
import com.example.ui.themes.ForBoostAppTheme
import com.example.ui.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    private var authComponent: AuthComponent? = null

    @Inject
    lateinit var vmFactory: ViewModelProviderFactory

    private val viewModel: LoginViewModel by viewModels { vmFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authComponent = DaggerAuthComponent.factory()
            .create(ComponentDepsProvider.get(requireContext()))
            .apply { inject(this@LoginFragment) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ForBoostAppTheme {
                    LoginScreen(viewModel = viewModel)
                }
            }
        }
    }
}