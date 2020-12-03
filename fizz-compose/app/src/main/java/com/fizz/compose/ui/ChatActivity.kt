package com.fizz.compose.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.platform.setContent
import com.fizz.compose.data.MessageModel
import com.fizz.compose.ui.theme.FizzTheme
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalStateException

private const val KEY_ARG_DETAILS_CITY_NAME = "KEY_ARG_DETAILS_CITY_NAME"

fun launchChatActivity(context: Context, item: MessageModel) {
    context.startActivity(createDetailsActivityIntent(context, item))
}

@VisibleForTesting
fun createDetailsActivityIntent(context: Context, item: MessageModel): Intent {
    val intent = Intent(context, ChatActivity::class.java)
    intent.putExtra(KEY_ARG_DETAILS_CITY_NAME, item.city.name)
    return intent
}

@AndroidEntryPoint
class ChatActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = getChatArgs(intent)
        setContent {
            FizzTheme {
                Surface {
                    Box {
                        Text(args.cityName)
                    }
                }
            }
        }
    }

    private fun getChatArgs(intent: Intent): DetailsActivityArg {
        val cityArg = intent.getStringExtra(KEY_ARG_DETAILS_CITY_NAME)
        if (cityArg.isNullOrEmpty()) {
            throw IllegalStateException("DETAILS_CITY_NAME arg cannot be null or empty")
        }
        return DetailsActivityArg(cityArg)
    }
}

data class DetailsActivityArg(
    val cityName: String
)