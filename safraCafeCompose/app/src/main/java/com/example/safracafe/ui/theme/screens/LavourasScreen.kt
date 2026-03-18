package com.example.safracafe.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.safracafe.Botao

@Composable
fun Lavouras(){
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(0.dp, 30.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Botao("Lavouras", onClick = {})
        Botao("Adicionar Lavoura", onClick = AddLavoura())
        Botao("Adicionar Talhao", onClick = {})
    }
}

@Composable
fun AddLavoura(onAddClick: () -> Unit){
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(0.dp, 30.dp, 0.dp)
    ){
        var value by rememberSaveable { mutableStateOf("initial value") }
        Box{
            BasicTextField(value = value, onValueChange = {value = it })
            Text(text = "Placeholder")
        }



        Botao("Adicionar", onClick = {})
    }
}