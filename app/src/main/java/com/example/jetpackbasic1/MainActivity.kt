package com.example.jetpackbasic1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
private fun StartScreen(onButtonClick: () -> Unit) {
    Surface(color = Color.White) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = "Welcome to the Basics Codelab")
            Button(onClick = onButtonClick ) {
                Text(text = "Continue")
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier) {
    var isBoardingMessageShowed by remember {
        mutableStateOf(false)
    }
    if (isBoardingMessageShowed)
        Greetings(modifier)
    else
        StartScreen(){
            isBoardingMessageShowed = !isBoardingMessageShowed
        }


}
@Composable
private fun Greetings(modifier: Modifier = Modifier, list: List<String> = List(1000) { "$it" }){
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = list) { name ->
            Greeting(name = name)
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
            Column(
                Modifier
                    .weight(1f)
                    .padding(if (isExpanded.value) 50.dp else 0.dp)) {
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

@Preview(showBackground = true)
@Composable
fun StartScreenPrev() {
    JetpackBasic1Theme {
        StartScreen(  ) {}
    }
}