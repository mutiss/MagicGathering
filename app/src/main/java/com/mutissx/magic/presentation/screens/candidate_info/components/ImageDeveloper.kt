package com.mutissx.magic.presentation.screens.candidate_info.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mutissx.magic.R

@Composable
fun ImageDeveloper() {
    val painter = painterResource(R.drawable.cbg_profile_photo)
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.Black, shape = RectangleShape),
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
