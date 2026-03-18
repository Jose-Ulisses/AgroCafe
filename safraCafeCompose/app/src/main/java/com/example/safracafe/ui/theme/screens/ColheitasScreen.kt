package com.example.safracafe.ui.theme.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.safracafe.Botao

@Composable
@PreviewScreenSizes
fun Colheitas(){
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(0.dp, 30.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Botao("Adicionar Colheita", onClick = {})
        Botao("Colheitas Anteriores", onClick = {})
    }
}