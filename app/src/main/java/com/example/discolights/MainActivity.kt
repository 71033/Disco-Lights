package com.example.discolights

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import com.example.discolights.ui.theme.DiscoLightsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiscoLightsTheme {
                DiscoLights()
            }
        }
    }
}

val colors = listOf(
    Color(0xFFff0000),
    Color(0xFFff7f00),
    Color(0xFFffff00),
    Color(0xFF00ff00),
    Color(0xFF0000ff),
    Color(0xFF4b0082),
    Color(0xFF9400d3)
)

fun indexOf(color: Color): Int{
    var index = 0
    for (col in colors){
        if (col == color){
            break
        }
        index++
    }
    return index
}

fun getRandomColor(): Color {
    return colors.random()
}

@Composable
fun DiscoLights(){
    var color by remember{ mutableStateOf(Color.Red) }
    var isChecked by remember{ mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                color = getRandomColor()
            }) {
                Text(text = "Change Color")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = !isChecked
                    }
                )
                Text(text = "Disco Mode")
                if (isChecked){
                    var i = indexOf(color)
                    i++
                    if(i == 6){
                        i = 0
                    }
                    color = colors[i]
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiscoLightsTheme {
        DiscoLights()

    }
}