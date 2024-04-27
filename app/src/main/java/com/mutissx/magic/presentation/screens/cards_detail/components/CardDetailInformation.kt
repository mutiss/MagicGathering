package com.mutissx.magic.presentation.screens.cards_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mutissx.magic.R
import com.mutissx.magic.domain.model.CardsDomain
import com.mutissx.magic.presentation.common.components.SpacerVerticalWithValue
import com.mutissx.magic.presentation.ui.theme.ColorPrimary
import com.mutissx.magic.presentation.ui.theme.Montserrat

@Composable
fun CardDetailInformation(card: CardsDomain?) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = card?.name ?: "",
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 20.sp,
            fontFamily = Montserrat,
            color = ColorPrimary,
            fontWeight = FontWeight.Black
        )
        SpacerVerticalWithValue(value = 4)
        Text(
            text = card?.type ?: "",
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 18.sp,
            fontFamily = Montserrat,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        SpacerVerticalWithValue(value = 4)
        Text(
            text = card?.text ?: "",
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 14.sp,
            fontFamily = Montserrat,
            color = ColorPrimary,
            fontWeight = FontWeight.Normal
        )
        SpacerVerticalWithValue(value = 4)
        Text(
            text = card?.artist ?: "",
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 12.sp,
            fontFamily = Montserrat,
            color = ColorPrimary,
            fontWeight = FontWeight.Bold
        )
    }
}
