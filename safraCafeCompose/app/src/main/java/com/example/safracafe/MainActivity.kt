package com.example.safracafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safracafe.ui.theme.SafraCafeTheme
import com.example.safracafe.ui.theme.screens.Colheitas
import com.example.safracafe.ui.theme.screens.Funcionarios
import com.example.safracafe.ui.theme.screens.Lavouras

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SafraCafeTheme {
                SafraCafeApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun SafraCafeApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.COLHEITAS) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ){
        when (currentDestination) {
            AppDestinations.COLHEITAS -> Colheitas()
            AppDestinations.LAVOURAS -> Lavouras()
            AppDestinations.FUNCIONARIOS -> Funcionarios()
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    COLHEITAS("Colheitas", Icons.Default.Add),
    LAVOURAS("Lavouras", Icons.Default.Place),
    FUNCIONARIOS("Panhadores", Icons.Default.Face),
}

@Composable
fun Botao(text: String, onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 10.dp)
            .height(50.dp)
    ){
        Text(text, fontSize = 18.sp)
    }
}