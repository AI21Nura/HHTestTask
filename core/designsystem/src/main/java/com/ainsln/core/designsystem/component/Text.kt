package com.ainsln.core.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.ainsln.core.designsystem.theme.Green

@Composable
fun SelectedText(
    text: String?,
    modifier: Modifier = Modifier,
    color: Color = Green
){
    SafeText(
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
    SelectedText(text, modifier, MaterialTheme.colorScheme.outline)
}

@Composable
fun SafeText(
    text: String?,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    color: Color = Color.Unspecified
){
    text?.let {
        Text(
            text = text,
            style = style,
            color = color,
            modifier = modifier
        )
    }
}
