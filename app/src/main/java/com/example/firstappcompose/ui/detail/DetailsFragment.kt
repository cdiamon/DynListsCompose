package com.example.firstappcompose.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.firstappcompose.ui.theme.WalletTheme
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ViewWindowInsetObserver

class DetailsFragment : Fragment() {

    private var currencyPrice: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let { currencyPrice = it.getString("currencyPrice") }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        // Create a ViewWindowInsetObserver using this view, and call start() to
        // start listening now. The WindowInsets instance is returned, allowing us to
        // provide it to AmbientWindowInsets in our content below.
        val windowInsets = ViewWindowInsetObserver(this).start()

        setContent {

            CompositionLocalProvider(LocalWindowInsets provides windowInsets) {
                WalletTheme {
                    if (currencyPrice == null) {
                        ProfileError()
                    } else {
                        DetailsScreen(
                            userData = currencyPrice!!,
                        )
                    }
                }
            }
        }
    }
}
