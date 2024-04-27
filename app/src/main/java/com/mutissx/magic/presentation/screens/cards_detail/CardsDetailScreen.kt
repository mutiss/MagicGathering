package com.mutissx.magic.presentation.screens.cards_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mutissx.magic.presentation.common.components.ErrorAlertDialog
import com.mutissx.magic.presentation.common.components.Loader
import com.mutissx.magic.presentation.screens.cards_detail.components.CardDetailImageInformation
import com.mutissx.magic.presentation.screens.cards_detail.components.CardDetailInformation
import com.mutissx.magic.presentation.screens.cards_detail.viewstate.CardsDetailState
import com.mutissx.magic.presentation.ui.theme.YellowCream
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardsDetailScreen(
    navController: NavController,
    viewModel: CardsDetailViewModel = koinViewModel()
) {
    val openDialog = rememberSaveable { mutableStateOf(false) }
    val closeActivity = rememberSaveable { mutableStateOf(false) }
    val messageError = rememberSaveable { mutableStateOf("") }
    if (closeActivity.value) {
        openDialog.value = false
        closeActivity.value = false
        navController.navigateUp()
    }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(YellowCream)
    ) {
        when (val state = viewModel.state.collectAsState().value) {
            is CardsDetailState.CardsDetailLoadingState -> {
                Loader()
            }
            is CardsDetailState.CardsDetailSuccessState -> {
                val card = state.cardDetail
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(YellowCream)
                ) {
                    if(card?.image?.isEmpty() == false){
                        CardDetailImageInformation(card = card)
                    }
                    CardDetailInformation(card = card)
                }
            }
            is CardsDetailState.CardsDetailErrorState -> {
                messageError.value = state.messageError
                openDialog.value = true
            }
        }
    }
    if (openDialog.value) {
        ErrorAlertDialog(
            closeActivity = closeActivity,
            errorMessage = messageError.value,
            openDialogCustom = openDialog
        )
    }
}
