package com.example.tresapps.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tresapps.navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Calculadora de Descuentos")
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
        BodyContent2(navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent2(navController: NavController, modifier: Modifier = Modifier) {

    var precio by remember { mutableStateOf("") }
    var descuento by remember { mutableStateOf("") }
    var cantidadDescuento by remember { mutableStateOf("") }
    var precioDescuento by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Spacer(modifier = Modifier.height(26.dp))
        Text(text = "Calculadora de Descuentos", color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))




        TextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text(text = "Precio") }
        )


        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = descuento,
            onValueChange = { descuento = it },
            label = { Text(text = "Descuento (%)") }
        )


        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            val descuentoPorc = descuento.toDoubleOrNull() ?: 0.0
            val precioOriginal = precio.toDoubleOrNull() ?: 0.0

            // Manejo de errores
            if (precioOriginal == 0.0 || descuentoPorc < 0) {
                cantidadDescuento = "Entrada no válida"
                precioDescuento = ""
            } else {
                cantidadDescuento = (precioOriginal * (descuentoPorc / 100)).toString()
                precioDescuento = (precioOriginal - cantidadDescuento.toDouble()).toString()
            }
        }) {
            Text(text = "Calcular")
        }


        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            cantidadDescuento = ""
            precioDescuento = ""
            precio = ""
            descuento = ""
        }) {
            Text(text = "Limpiar")
        }


        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = cantidadDescuento,
            onValueChange = { cantidadDescuento = it },
            readOnly = true,
            label = { Text(text = "Cantidad de Descuento") }
        )


        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = precioDescuento,
            onValueChange = { precioDescuento = it },
            readOnly = true,
            label = { Text(text = "Precio con Descuento") }
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = {
            navController.navigate(route = AppScreen.ThirdScreen.route)
        }) {
            Text(text = "Ir a la tercera")
        }


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    val navController = rememberNavController()
    SecondScreen(navController)
}
