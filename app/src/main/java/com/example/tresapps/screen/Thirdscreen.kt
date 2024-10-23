package com.example.tresapps.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tresapps.navigation.AppScreen
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Loteria")
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "ArrowBack")
                }
            }
        )
    }) { innerPadding ->
        BodyContent3(navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent3(navController: NavController, modifier: Modifier = Modifier) {
    var numeros by remember { mutableStateOf(generateUniqueNumbers(7, 1..70)) }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Simulador de Lotería", style = MaterialTheme.typography.titleLarge)


        Spacer(modifier = Modifier.height(26.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            numeros.forEach { numero ->
                Esfera(numero)
            }
        }


        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = {
            numeros = generateUniqueNumbers(7, 1..70)
        }) {
            Text(text = "Generar Nuevos Números")
        }


        Spacer(modifier = Modifier.height(36.dp))

        Button(onClick = {
            navController.navigate(route = AppScreen.FirstScreen.route)
        }) {
            Text(text = "Volver al inicio")
        }
    }
}

@Composable
fun Esfera(numero: Int) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(Color.Red, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(text = numero.toString(), color = Color.White)
    }
}

fun generateUniqueNumbers(count: Int, range: IntRange): List<Int> {
    val uniqueNumbers = mutableSetOf<Int>()
    while (uniqueNumbers.size < count) {
        uniqueNumbers.add(Random.nextInt(range.first, range.last + 1))
    }
    return uniqueNumbers.toList()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    val navController = rememberNavController()
    ThirdScreen(navController)
}
