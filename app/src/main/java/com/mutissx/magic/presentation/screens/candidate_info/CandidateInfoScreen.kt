package com.mutissx.magic.presentation.screens.candidate_info

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mutissx.magic.R
import com.mutissx.magic.presentation.screens.Screen
import com.mutissx.magic.presentation.screens.candidate_info.components.EmailAndProjectInfo
import com.mutissx.magic.presentation.screens.candidate_info.components.ImageDeveloper
import com.mutissx.magic.presentation.screens.candidate_info.components.NameDeveloper
import com.mutissx.magic.presentation.screens.candidate_info.components.ProfessionInfo
import com.mutissx.magic.presentation.common.components.SpacerVerticalWithValue
import com.mutissx.magic.presentation.ui.theme.Montserrat
import com.mutissx.magic.presentation.ui.theme.YellowCream

@Composable
fun CandidateInfoScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 4.dp,
            backgroundColor = YellowCream,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(width = 10.dp, color = Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 24.dp)
            ) {
                NameDeveloper()
                SpacerVerticalWithValue(2)
                ImageDeveloper()
                SpacerVerticalWithValue(2)
                ProfessionInfo()
                SpacerVerticalWithValue(2)
                EmailAndProjectInfo()
            }
        }
        Button(onClick = {
            navController.navigate(Screen.CardsListScreen)
        }, modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.next),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                fontFamily = Montserrat,
                textAlign = TextAlign.Center
            )
        }
    }
}
