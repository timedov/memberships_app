package com.example.savepost.presentation

import android.net.Uri
import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.example.common.di.ComponentDepsProvider
import com.example.common.utils.Constants
import com.example.domain.model.ContentType
import com.example.savepost.di.DaggerSavePostComponent
import com.example.savepost.presentation.composables.SavePostScreen
import com.example.savepost.presentation.model.SavePostEvent
import com.example.ui.base.BaseFragment
import com.example.ui.themes.ForBoostAppTheme
import com.example.ui.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class SavePostFragment : BaseFragment() {

    @Inject
    lateinit var vmFactory: ViewModelProviderFactory

    private val viewModel: SavePostViewModel by viewModels { vmFactory }

    override fun provideComposeView(): ComposeView =
        ComposeView(requireContext()).apply {
            setContent {
                ForBoostAppTheme {
                    SavePostScreen(
                        viewModel,
                        getContentType = this@SavePostFragment::getContentType
                    )
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerSavePostComponent.factory()
            .create(ComponentDepsProvider.get(requireContext()))
            .apply { inject(this@SavePostFragment) }

        viewModel.obtainEvent(
            SavePostEvent.Initiate(arguments?.getBoolean(LOAD_DRAFT) == true)
        )
    }

    private fun getContentType(uri: Uri): ContentType =
        requireContext().contentResolver.getType(uri)?.let {
            when {
                it.startsWith(Constants.IMAGE_CONTENT_TYPE_PREFIX) -> ContentType.IMAGE
                it.startsWith(Constants.VIDEO_CONTENT_TYPE_PREFIX) -> ContentType.VIDEO
                else -> ContentType.NONE
            }
        } ?: ContentType.NONE

    companion object {
        private const val LOAD_DRAFT = "loadDraft"
    }
}
