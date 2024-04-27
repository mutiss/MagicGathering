package com.mutissx.magic.presentation.common.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mutissx.magic.R
import com.mutissx.magic.presentation.ui.theme.ColorPrimary
import com.mutissx.magic.presentation.ui.theme.Montserrat

@Composable
fun TopBar(
    navController: NavController,
    nameScreen: String
) {
    TopAppBar(
        title = {
            Text(
                text = nameScreen, color = Color.White, fontWeight = FontWeight.Black,
                fontSize = dimensionResource(id = R.dimen.topbar_text_size).value.sp,
                fontFamily = Montserrat
            )
        },
        backgroundColor = ColorPrimary,
        contentColor = Color.White,
        elevation = dimensionResource(id = R.dimen.topbar_elevation_size),
        navigationIcon = if (navController.previousBackStackEntry != null) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        } else {
            null
        }
    )
}
