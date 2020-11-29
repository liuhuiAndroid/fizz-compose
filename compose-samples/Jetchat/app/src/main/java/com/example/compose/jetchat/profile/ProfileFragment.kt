package com.example.compose.jetchat.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.compose.jetchat.NavActivity
import com.example.compose.jetchat.theme.JetchatTheme

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val userId = arguments?.getString("userId")
        viewModel.setUserId(userId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(context = requireContext()).apply {
            setContent {
                viewModel.userData.observeAsState().value.let { userData: ProfileScreenState? ->
                    JetchatTheme {
                        if (userData == null) {
                            ProfileError()
                        } else {
                            ProfileScreen(
                                userData = userData,
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
}
