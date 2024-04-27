package com.mutissx.magic.presentation.screens.candidate_info.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mutissx.magic.R
import com.mutissx.magic.presentation.ui.theme.Montserrat

@Composable
fun ProfessionInfo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .border(width = 2.dp, color = Color.Black, shape = CircleShape)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true),
            text = stringResource(id = R.string.developer_role),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            fontFamily = Montserrat,
            textAlign = TextAlign.Start
        )
        Image(
            painter = painterResource(id = R.drawable.ic_android_black_24dp),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Green)
        )
    }
}
