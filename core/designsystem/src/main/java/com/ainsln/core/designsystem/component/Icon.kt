package com.ainsln.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CircleIcon(
    icon: ImageVector,
    backgroundColor: Color,
    modifier: Modifier = Modifier
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(32.dp)
            .background(color = backgroundColor, shape = CircleShape)

    ){
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}
