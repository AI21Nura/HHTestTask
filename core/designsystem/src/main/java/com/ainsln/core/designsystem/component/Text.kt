package com.ainsln.core.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ainsln.core.designsystem.theme.Green

@Composable
fun SelectedText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Green
){
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = color,
        modifier = modifier
    )
}

@Composable
fun SecondaryText(
    text: String,
    modifier: Modifier = Modifier
){
    SelectedText(text, modifier, MaterialTheme.colorScheme.outlineVariant)
}
