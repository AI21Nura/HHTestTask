package com.ainsln.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ainsln.core.common.result.AppException
import com.ainsln.core.designsystem.component.SmallButton
import com.ainsln.core.designsystem.icon.AppIcons
import com.ainsln.core.ui.R

@Composable
fun PlaceholderBlock(
    text: String,
    icon: ImageVector,
    compact: Boolean,
    modifier: Modifier = Modifier,
    subtext: String? = null,
    actions: (@Composable () -> Unit)? = null
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = 12.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth().padding(vertical = 16.dp)
    ){
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(if (compact) 48.dp else 96.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(
                text = text,
                style = if (compact) MaterialTheme.typography.titleSmall
                        else MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.outline
            )
            subtext?.let {
                Text(
                    text = subtext,
                    style = if (compact) MaterialTheme.typography.bodyLarge
                            else MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                )
            }
        }
        actions?.invoke()
    }
}

@Composable
fun ErrorBlock(
    e: AppException,
    modifier: Modifier = Modifier,
    compact: Boolean = true,
    onRetryClick: (() -> Unit)? = null
){
    val msg = when(e){
        is AppException.NetworkError -> stringResource(R.string.error_network)
        is AppException.DatabaseError -> stringResource(R.string.error_database)
        is AppException.UnknownError -> stringResource(R.string.error_unknown)
    }

    PlaceholderBlock(
        text = msg,
        icon = AppIcons.Error,
        compact = compact,
        modifier = modifier,
        actions = {
            onRetryClick?.let {
                SmallButton(
                    text = stringResource(R.string.retry),
                    onClick = onRetryClick,
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
            }
        }
    )
}

@Composable
fun PlaceholderScreen(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    subtext: String? = null
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        PlaceholderBlock(
            text = text,
            icon = icon,
            compact = false,
            subtext = subtext
        )
    }
}
