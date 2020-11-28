package com.example.compose.jetchat.conversation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Providers
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.compose.jetchat.NavActivity
import com.example.compose.jetchat.R
import com.example.compose.jetchat.data.exampleUiState
import com.example.compose.jetchat.theme.JetchatTheme

class ConversationFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(context = requireContext()).apply {
            setContent {
                Providers(BackPressedDispatcherAmbient provides requireActivity()) {
                    JetchatTheme {
                        ConversationContent(
                                uiState = exampleUiState,
                                navigateToProfile = { user ->
                                    val bundle = bundleOf("userId" to user)
                                    findNavController().navigate(
                                            R.id.nav_profile,
                                            bundle
                                    )
                                },
                                onNavIconPressed = {
                                    (activity as? NavActivity)?.openDrawer()
                                }
                        )
                    }
                }
            }
        }
    }
}
