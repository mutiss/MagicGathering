package com.mutissx.magic.presentation.screens.cards_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mutissx.magic.R
import com.mutissx.magic.domain.model.CardsDomain
import com.mutissx.magic.presentation.common.components.SpacerVerticalWithValue
import com.mutissx.magic.presentation.common.components.ZoomableCardDetail
import com.mutissx.magic.presentation.ui.theme.Montserrat

@Composable
fun CardDetailImageInformation(card: CardsDomain) {
    ZoomableCardDetail(card.image, card.name)
    Text(
        text = stringResource(id = R.string.drag_image_to_see_bigger),
        modifier = Modifier
            .fillMaxWidth(),
        fontSize = 12.sp,
        fontFamily = Montserrat,
        color = Color.Gray,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Normal
    )
    SpacerVerticalWithValue(value = 16)
}
