package com.ainsln.feature.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ainsln.core.designsystem.component.SearchTextField
import com.ainsln.core.designsystem.component.SmallIconButton
import com.ainsln.core.designsystem.icon.AppIcons
import com.ainsln.feature.search.R

@Composable
fun SearchRow(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: (String) -> Unit,
    placeholderText: String,
    leadingIcon: @Composable () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        SearchTextField(
            value = value,
            onValueChange = onValueChange,
            onSearchClick = onSearchClick,
            placeholderText = placeholderText,
            leadingIcon = leadingIcon,
            modifier = Modifier.weight(1f)
        )

        SmallIconButton(
            icon = AppIcons.Filter,
            onClick = {},
            contentDescription = stringResource(R.string.filters),
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
        )
    }
}
