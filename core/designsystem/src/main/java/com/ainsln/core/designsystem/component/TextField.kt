package com.ainsln.core.designsystem.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ainsln.core.designsystem.icon.AppIcons
import com.ainsln.core.designsystem.theme.HHTestTaskTheme

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: (String) -> Unit,
    placeholderText: String,
    leadingIcon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    InputTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholderText,
                color = MaterialTheme.colorScheme.outline
            )
        },
        leadingIcon = leadingIcon,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearchClick(value) },
        ),
        singleLine = true,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun InputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    colors: TextFieldColors,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    shape: Shape = TextFieldDefaults.shape,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        enabled = enabled,
        singleLine = singleLine,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions
    ) { innerTextField ->

        TextFieldDefaults.DecorationBox(
            value = value,
            visualTransformation = visualTransformation,
            innerTextField = innerTextField,
            singleLine = singleLine,
            enabled = enabled,
            interactionSource = interactionSource,
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 0.dp
            ),
            trailingIcon = trailingIcon,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            shape = shape,
            colors = colors
        )
    }
}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    HHTestTaskTheme {
        SearchTextField(
            value = "",
            onValueChange = {},
            onSearchClick = {},
            placeholderText = "Должность, ключевые слова",
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = AppIcons.Search,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        )
    }
}
