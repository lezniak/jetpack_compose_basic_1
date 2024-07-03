package com.example.jetpackbasic1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackbasic1.ui.theme.JetpackBasic1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackBasic1Theme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    list: List<String> = listOf("Android", "Compose")
) {
    Surface() {
        Column(modifier = modifier.padding(vertical = 5.dp)) {
            list.forEach {
                Greeting(name = it)
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var isExpanded = remember {
        mutableStateOf(false)
    }

    Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(vertical = 2.dp, horizontal = 5.dp)) {
        Row() {
            Column(Modifier.weight(1f).padding(if (isExpanded.value) 50.dp else 0.dp)) {
                Text(
                    text = "Hello"
                )
                Text(
                    text = "$name!"
                )
            }
            Button(
                onClick = { isExpanded.value = !isExpanded.value } ,
                modifier = Modifier
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(if (isExpanded.value) "Show less" else "Show more",color = MaterialTheme.colorScheme.primary)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackBasic1Theme {
        MyApp(modifier = Modifier.fillMaxSize())
    }
}