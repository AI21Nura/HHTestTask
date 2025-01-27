package com.ainsln.core.designsystem.component

import androidx.compose.material3.Badge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun NavItemBadge(text: String){
    Badge(
        containerColor = Color.Red,
        contentColor = Color.White
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = Color.White
        )
    }
}
