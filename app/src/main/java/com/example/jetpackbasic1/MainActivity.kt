package com.example.jetpackbasic1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Welcome to the Basics Codelab")
            Button(onClick = onButtonClick) {
                Text(text = "Continue")
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier) {
    var isBoardingMessageShowed by rememberSaveable {
        mutableStateOf(true)
    }
    if (isBoardingMessageShowed)
        Greetings(modifier)
    else
        StartScreen() {
            isBoardingMessageShowed = !isBoardingMessageShowed
        }


}

@Composable
private fun Greetings(modifier: Modifier = Modifier, list: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = list) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        colors = CardDefaults.cardColors(),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(vertical = 2.dp, horizontal = 5.dp)
    ) {
        Row() {
            Column(
                Modifier
                    .padding(16.dp)
                    .weight(1f)
                    .animateContentSize(
                        animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    ))
            ) {
                Text(
                    text = "Hello"
                )
                Text(
                    text = "$name",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                if (isExpanded)
                    Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc id posuere sem, sit amet porta ante. Nam pharetra est eu facilisis ornare. Duis nec mi eu ipsum porta tristique sit amet ac tortor. Nulla suscipit, ante eu porttitor posuere, mauris erat elementum mauris, ac tristique mauris enim varius tellus. Praesent tincidunt libero quis nibh pulvinar pellentesque. Donec et nibh sit amet erat gravida blandit at vitae nulla. Cras tempus consectetur nunc, a luctus mauris.")
            }
            IconButton(
                onClick = { isExpanded = !isExpanded },
                modifier = Modifier
                    .weight(1f),
            ) {
                Icon(imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore, contentDescription = "")
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
        StartScreen() {}
    }
}