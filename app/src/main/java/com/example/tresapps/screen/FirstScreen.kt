package com.example.tresapps.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tresapps.R
import com.example.tresapps.navigation.AppScreen

@Composable
fun FirstScreen(navController: NavController) {
    BodyContent(navController)
}

@Composable
fun BodyContent(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        PosicionPantallla(
            titulo = "Mis años perrunos",
            imagen = painterResource(id = R.drawable.img)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = {
            navController.navigate(route = AppScreen.SecondScreen.route)
        }) {
            Text(text = "Ir a la segunda")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    val navController = rememberNavController()
    FirstScreen(navController)
}

@Composable
fun PosicionPantallla(
    titulo: String,
    imagen: Painter,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var edad by remember { mutableStateOf("") }
        var resultado by remember { mutableStateOf("") }

        Image(
            painter = imagen,
            contentDescription = "Imagen representativa de años perrunos",
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center
        )
        Text(
            text = titulo,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )
        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Ingrese su edad") },
            modifier = Modifier.padding(bottom = 16.dp)
        )
        ElevatedButton(
            onClick = {
                try {
                    val res = edad.toInt() * 7
                    resultado = res.toString()
                } catch (e: NumberFormatException) {
                    resultado = "Entrada no válida"
                }
            },
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Text(text = "Calcular")
        }
        OutlinedTextField(
            value = resultado,
            onValueChange = { resultado = it },
            readOnly = true,
            label = { Text("Edad Perruna") }
        )
    }
}
