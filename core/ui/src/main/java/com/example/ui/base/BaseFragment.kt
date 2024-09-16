package com.example.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import com.example.common.utils.observe

abstract class BaseFragment : Fragment() {

    @LayoutRes
    open fun getLayoutId(): Int? = null

    open fun provideComposeView(): ComposeView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getLayoutId()?.let { layoutRes ->
            inflater.inflate(layoutRes, container, false)
        } ?: provideComposeView() ?: throw IllegalStateException("Provide layoutRes or ComposeView")
    }

    inline fun <T> Flow<T>.observe(crossinline block: (T) -> Unit): Job {
        return observe( fragment = this@BaseFragment, block)
    }
}