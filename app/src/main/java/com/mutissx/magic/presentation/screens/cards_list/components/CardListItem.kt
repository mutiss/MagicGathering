package com.mutissx.magic.presentation.screens.cards_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mutissx.magic.domain.model.CardsDomain
import com.mutissx.magic.presentation.common.components.CardRowImage
import com.mutissx.magic.presentation.screens.Screen
import com.mutissx.magic.presentation.ui.theme.ColorPrimary
import com.mutissx.magic.presentation.ui.theme.Montserrat
import com.mutissx.magic.presentation.ui.theme.YellowCream

@Composable
fun CardListItem(navController: NavController, card: CardsDomain?) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(color = YellowCream)
            .border(width = 6.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                navController.navigate(Screen.CardsDetailScreen(card?.id.orEmpty()))
            }
    ) {
        CardRowImage(card?.image)
        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = card?.name.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                fontSize = 18.sp,
                fontFamily = Montserrat,
                color = ColorPrimary,
                fontWeight = FontWeight.Black
            )
            Text(
                text = card?.type.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                fontSize = 16.sp,
                fontFamily = Montserrat,
                color = ColorPrimary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = card?.artist.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                fontSize = 14.sp,
                fontFamily = Montserrat,
                color = ColorPrimary,
                fontWeight = FontWeight.Normal
            )
        }
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp), color = ColorPrimary
    )
}
