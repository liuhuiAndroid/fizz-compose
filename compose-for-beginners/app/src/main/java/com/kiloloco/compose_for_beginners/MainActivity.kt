package com.kiloloco.compose_for_beginners

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.kiloloco.compose_for_beginners.ui.ComposeforbeginnersTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeforbeginnersTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    BoxLayout()
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ComposeforbeginnersTheme {
            BoxLayout()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun LoginFormPreview() {
        LoginForm()
    }

    @Composable
    fun BoxLayout() {
        Box(alignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.size(200.dp).background(Color.Blue),
                alignment = Alignment.TopEnd
            ) {
                Box(
                    modifier = Modifier.size(25.dp).background(Color.Cyan)
                )
            }
            Box(
                modifier = Modifier.size(100.dp).background(Color.Red),
                alignment = Alignment.BottomStart
            ) {
                Box(
                    modifier = Modifier.size(25.dp).background(Color.Magenta)
                )
            }
        }
    }

    @Composable
    fun LoginForm() {
        Box(alignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val modifier = Modifier.padding(vertical = 4.dp)

                val usernameState = remember { mutableStateOf(TextFieldValue()) }
                TextField(
                    value = usernameState.value,
                    onValueChange = { usernameState.value = it },
                    placeholder = { Text("Username") },
                    modifier = modifier
                )

                val passwordState = remember { mutableStateOf(TextFieldValue()) }
                TextField(
                    value = passwordState.value,
                    onValueChange = { passwordState.value = it },
                    placeholder = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = modifier
                )
                Button(
                    onClick = {
                        val username = usernameState.value.text
                        val password = passwordState.value.text
                        Log.i("kilo", "Username: $username - Password: $password")
                    },
                    modifier = modifier.then(Modifier.drawShadow(elevation = 3.dp))
                ) {
                    Text("Login")
                }
            }
        }
    }

}

