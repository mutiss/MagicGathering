package com.mutissx.magic.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mutissx.magic.R
import com.mutissx.magic.presentation.ui.theme.ColorPrimary
import com.mutissx.magic.presentation.ui.theme.Montserrat

@Composable
fun ErrorAlertDialog(
    closeActivity: MutableState<Boolean>,
    errorMessage: String,
    openDialogCustom: MutableState<Boolean>
) {
    Dialog(onDismissRequest = {}) {
        CustomDialogUI(
            closeActivity = closeActivity,
            errorMessage = errorMessage,
            openDialogCustom = openDialogCustom
        )
    }
}

//Layout
@Composable
fun CustomDialogUI(
    closeActivity: MutableState<Boolean>,
    errorMessage: String,
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>
) {
    Card(
        //shape = MaterialTheme.shapes.medium,
        shape = RoundedCornerShape(10.dp),
        // modifier = modifier.size(280.dp, 240.dp)
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier
                .background(Color.White)
        ) {
            Error()
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Error",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Black,
                    color = ColorPrimary,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = errorMessage,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontFamily = Montserrat,
                    color = ColorPrimary,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth()

                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(Color.LightGray), horizontalArrangement = Arrangement.Center
            ) {
                TextButton(onClick = {
                    closeActivity.value = true
                    openDialogCustom.value = false
                }) {
                    Text(
                        stringResource(id = R.string.close),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }
    }
}
