package com.mutissx.magic.presentation.screens.candidate_info.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mutissx.magic.R
import com.mutissx.magic.presentation.common.components.SpacerVerticalWithValue
import com.mutissx.magic.presentation.ui.theme.Montserrat

@Composable
fun EmailAndProjectInfo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .border(width = 2.dp, color = Color.Black, shape = RectangleShape)
            .padding(8.dp)
    )
    {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.welcome_message_info),
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            fontFamily = Montserrat,
            textAlign = TextAlign.Start
        )
        SpacerVerticalWithValue(2)
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.developer_email),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            fontFamily = Montserrat,
            textAlign = TextAlign.End
        )
        SpacerVerticalWithValue(2)
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.date_creation_project),
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            fontFamily = Montserrat,
            textAlign = TextAlign.End
        )
    }
}
