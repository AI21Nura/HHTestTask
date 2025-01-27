package com.ainsln.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ainsln.core.designsystem.R
import com.ainsln.core.designsystem.icon.AppIcons
import com.ainsln.core.designsystem.theme.Blue
import com.ainsln.core.designsystem.theme.CustomTextStyle
import com.ainsln.core.designsystem.theme.DarkBlue
import com.ainsln.core.designsystem.theme.DarkGreen
import com.ainsln.core.designsystem.theme.Gray4
import com.ainsln.core.designsystem.theme.Green
import com.ainsln.core.designsystem.theme.HHTestTaskTheme

@Composable
fun BigButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp)
){
    AppButton(
        text = text,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue,
            contentColor = Color.White,
            disabledContainerColor = DarkBlue,
            disabledContentColor = Gray4
        ),
        shape = RoundedCornerShape(8.dp),
        textStyle = CustomTextStyle.buttonText1,
        modifier = modifier,
         enabled = enabled,
        contentPadding = contentPadding
    )
}

@Composable
fun MediumButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding
){
    AppButton(
        text = text,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Green,
            contentColor = Color.White,
            disabledContainerColor = DarkGreen,
            disabledContentColor = Gray4
        ),
        shape = RoundedCornerShape(50.dp),
        textStyle = CustomTextStyle.buttonText2,
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding
    )
}

@Composable
fun SmallButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding
){
    AppButton(
        text = text,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue,
            contentColor = Color.White,
            disabledContainerColor = DarkBlue,
            disabledContentColor = Gray4
        ),
        shape = RoundedCornerShape(8.dp),
        textStyle = CustomTextStyle.buttonText2,
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding
    )
}

@Composable
fun SmallIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
){
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        modifier = modifier
    ){
        IconButton(
            onClick = onClick,
        ) {
            Icon(
                imageVector = icon,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize(0.6f)
            )
        }
    }
}

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
){
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { onClick() }
    ) {
        Text(
            text = text,
            color = Blue
        )
        icon?.let {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Blue
            )
        }
    }
}

@Composable
fun FavoriteToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
){
    IconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = Modifier.size(28.dp)
    ) {
        if (checked)
            Icon(
                imageVector = AppIcons.FavoriteActive,
                contentDescription = stringResource(R.string.remove_from_favorites),
                tint = Color.Unspecified
            )
        else
            Icon(
                imageVector = AppIcons.FavoriteInactive,
                contentDescription = stringResource(R.string.add_to_favorites),
                tint = MaterialTheme.colorScheme.outline
            )

    }
}

@Composable
internal fun AppButton(
    text: String,
    onClick: () -> Unit,
    colors: ButtonColors,
    shape: Shape,
    textStyle: TextStyle,
    enabled: Boolean,
    contentPadding: PaddingValues,
    modifier: Modifier
){
    Button(
        onClick = onClick,
        colors = colors,
        shape = shape,
        enabled = enabled,
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}

@Preview
@Composable
fun BigButtonPreview(){
    HHTestTaskTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            BigButton(text = "Label", onClick = {}, modifier = Modifier.fillMaxWidth(), enabled = true)
            BigButton(text = "Label", onClick = {}, modifier = Modifier.fillMaxWidth(), enabled = false)
        }
    }
}

@Preview
@Composable
fun MediumButtonPreview(){
    HHTestTaskTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            MediumButton(text = "Label", onClick = {}, modifier = Modifier.fillMaxWidth(), enabled = true)
            MediumButton(text = "Label", onClick = {}, modifier = Modifier.fillMaxWidth(), enabled = false)
        }
    }
}

@Preview
@Composable
fun SmallButtonPreview(){
    HHTestTaskTheme {
        Row {
            SmallButton(text = "Label", onClick = {}, modifier = Modifier.padding(20.dp), enabled = true)
            SmallButton(text = "Label", onClick = {}, modifier = Modifier.padding(20.dp), enabled = false)
        }
    }
}

@Preview
@Composable
fun SmallIconButtonPreview(){
    HHTestTaskTheme {
        SmallIconButton(
            icon = AppIcons.Filter,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun FavoriteToggleButtonPreview(){
    HHTestTaskTheme {
        Row {
            FavoriteToggleButton (
                checked = false,
                onCheckedChange = {}
            )
            FavoriteToggleButton (
                checked = true,
                onCheckedChange = {}
            )
        }
    }
}

@Preview
@Composable
fun TextButtonPreview(){
    HHTestTaskTheme {
        TextButton(
            text = "По соответствию",
            onClick = {},
            icon = AppIcons.Sort
        )
    }
}
