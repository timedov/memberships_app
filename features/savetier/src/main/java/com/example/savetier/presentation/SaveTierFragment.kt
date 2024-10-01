package com.example.savetier.presentation

import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.example.common.di.ComponentDepsProvider
import com.example.savetier.di.DaggerSaveTierComponent
import com.example.savetier.presentation.composables.SaveTierScreen
import com.example.ui.base.BaseFragment
import com.example.ui.themes.ForBoostAppTheme
import com.example.ui.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class SaveTierFragment : BaseFragment() {

    @Inject
    lateinit var vmFactory: ViewModelProviderFactory

    private val viewModel: SaveTierViewModel by viewModels { vmFactory }

    override fun provideComposeView(): ComposeView =
        ComposeView(requireContext()).apply {
            setContent {
                ForBoostAppTheme { SaveTierScreen(viewModel) }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerSaveTierComponent.factory()
            .create(ComponentDepsProvider.get(requireContext()))
            .apply { inject(this@SaveTierFragment) }

        viewModel.tierId = arguments?.getLong(TIER_ID) ?: -1
    }

    companion object {

        private const val TIER_ID = "tierId"
    }
}
