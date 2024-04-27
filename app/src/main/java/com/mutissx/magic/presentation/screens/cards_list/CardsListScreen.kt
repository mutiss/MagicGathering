package com.mutissx.magic.presentation.screens.cards_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.mutissx.magic.presentation.common.components.Loader
import com.mutissx.magic.presentation.common.components.PaginationStateHandler
import com.mutissx.magic.presentation.screens.cards_list.components.CardListItem
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CardsListScreen(
    navController: NavController,
    viewModel: CardsListViewModel = koinViewModel()
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val cardsListState = viewModel.cardsListState
        .collectAsLazyPagingItems()

    Scaffold(scaffoldState = scaffoldState) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (cardsListState.loadState.refresh is LoadState.NotLoading) {
                items(
                    count = cardsListState.itemCount,
                    key = cardsListState.itemKey { it.id },
                    contentType = cardsListState.itemContentType { "contentType" }
                ) { index ->
                    val card = cardsListState[index]
                    CardListItem(navController = navController, card = card)
                }
                item {
                    PaginationStateHandler(
                        paginationState = cardsListState,
                        loadingComponent = {
                            Loader()
                        },
                        errorComponent = {
                            val message = it.message.toString()
                            LaunchedEffect(message) {
                                scaffoldState.snackbarHostState.showSnackbar(message)
                            }
                        }
                    )
                }
            }
        }
    }
}
