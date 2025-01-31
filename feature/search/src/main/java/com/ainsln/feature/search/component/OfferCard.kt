package com.ainsln.feature.search.component

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ainsln.core.designsystem.component.CircleIcon
import com.ainsln.core.designsystem.component.SelectedText
import com.ainsln.core.designsystem.icon.AppIcons.getOfferIcon
import com.ainsln.core.designsystem.theme.CustomTextStyle
import com.ainsln.core.designsystem.theme.HHTestTaskTheme
import com.ainsln.core.model.Offer
import com.ainsln.core.ui.component.ShimmerEffect
import com.ainsln.core.ui.component.TextShimmerEffect
import com.ainsln.feature.search.PreviewData

@Composable
internal fun OfferCard(
    offer: Offer,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
){
    OfferCard(
        iconBlock = {
            getOfferIcon(offer.id)?.let { (icon, color) ->
                CircleIcon(
                    icon = icon,
                    backgroundColor = color
                )
            }
        },
        textBlock = {
            Text(
                text = offer.title,
                style = CustomTextStyle.titleExtraSmall,
                maxLines = if (offer.selectedText == null) 3 else 2,
                overflow = TextOverflow.Ellipsis
            )

            SelectedText(offer.selectedText)
        },
        onCardClick = onCardClick,
        modifier = modifier
    )
}

@Composable
internal fun ShimmerOfferCard(
    modifier: Modifier = Modifier
){
    OfferCard(
        iconBlock = {
            ShimmerEffect(
                Modifier
                    .size(32.dp)
                    .clip(CircleShape))
        },
        textBlock = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextShimmerEffect(0.8f)
                TextShimmerEffect(0.6f)
            }
        },
        onCardClick = {},
        modifier = modifier
    )
}

@Composable
internal fun OfferList(
    offers: List<Offer>,
    openOfferLink: (String) -> Unit,
    modifier: Modifier = Modifier
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(offers){ offer ->
            OfferCard(
                offer = offer,
                onCardClick = { openOfferLink(offer.link) },
            )
        }
    }
}

@Composable
internal fun ShimmerOfferList(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        repeat(3) { ShimmerOfferCard() }
    }
}

@Composable
private fun OfferCard(
    iconBlock: @Composable () -> Unit,
    textBlock: @Composable () -> Unit,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,

    ){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .size(132.dp)
            .clickable { onCardClick() }
    ) {
        Column(
            modifier.padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            Box(Modifier.weight(1f)){
                iconBlock()
            }

            Column(Modifier.weight(1f)) {
                textBlock()
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OfferCardPreview(){
    HHTestTaskTheme {
        Surface {
            OfferCard(
                offer = PreviewData.offers.first(),
                onCardClick = {},
            )
        }
    }
}
