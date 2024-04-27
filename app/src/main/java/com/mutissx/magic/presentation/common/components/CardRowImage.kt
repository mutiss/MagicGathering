package com.mutissx.magic.presentation.common.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mutissx.magic.R

@Composable
fun CardRowImage(url: String?, width: Dp = 150.dp, height: Dp = 200.dp) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = Modifier
            .size(width = width, height = height),
        error = painterResource(id = R.drawable.magic_card_placeholder)
    )
}
