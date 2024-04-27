package com.mutissx.magic.presentation.screens.cards_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.mutissx.magic.core.domain.common.Resource
import com.mutissx.magic.core.testing.CoroutinesTestRule
import com.mutissx.magic.domain.model.CardsDomain
import com.mutissx.magic.domain.usecase.GetCardDetailUseCase
import com.mutissx.magic.presentation.screens.cards_detail.viewstate.CardsDetailState
import com.mutissx.magic.presentation.util.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CardsDetailViewModelTest {
    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private var getCardsDetailUseCase: GetCardDetailUseCase = mock()
    private var savedStateHandle: SavedStateHandle = mock()

    private lateinit var viewModel: CardsDetailViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = CardsDetailViewModel(getCardsDetailUseCase, savedStateHandle)
        givenSavedStateHandlerId()
    }

    @Test
    fun `card detail state return success state`() =
        runTest {
            givenGetCardsDetailSuccess()
            whenGetCardsDetailIsInvoked()
            thenGetCardsDetailUseCaseIsInvoked()
            thenGetCardDetailReturnsSuccess()
        }

    @Test
    fun `card detail state return error state`() = runTest {
        givenGetCardsDetailFails()
        whenGetCardsDetailIsInvoked()
        thenGetCardsDetailUseCaseIsInvoked()
        thenGetCardDetailReturnsError()
    }

    private fun givenSavedStateHandlerId() {
        whenever(savedStateHandle.get<String>(Constants.CARDS_DETAILS_ARGUMENT_KEY)) doReturn ANY_CARD_ID
    }

    private suspend fun givenGetCardsDetailSuccess() {
        whenever(getCardsDetailUseCase.invokeWithParams(ANY_CARD_ID)) doReturn flow {
            emit(
                Resource.Success(
                    anyCardDomain
                )
            )
        }
    }

    private suspend fun givenGetCardsDetailFails() {
        whenever(getCardsDetailUseCase.invokeWithParams(ANY_CARD_ID)) doReturn flow {
            emit(
                Resource.Error(message = ERROR_MESSAGE)
            )
        }
    }

    private fun whenGetCardsDetailIsInvoked() {
        viewModel.getCardsDetail()
    }

    private fun thenGetCardsDetailUseCaseIsInvoked() {
        verify(getCardsDetailUseCase, times(1)).invokeWithParams(ANY_CARD_ID)
    }

    private fun thenGetCardDetailReturnsSuccess() {
        assert(
            viewModel.state.value == CardsDetailState.CardsDetailSuccessState(
                anyCardDomain
            )
        )
    }

    private fun thenGetCardDetailReturnsError() {
        assert(viewModel.state.value == CardsDetailState.CardsDetailErrorState(messageError = ERROR_MESSAGE))
    }

    companion object {
        private const val ANY_CARD_ID = "Card id"
        private const val ANY_CARD_MULTIVERSE_ID = 12414
        private const val ANY_CARD_NAME = "Card name"
        private const val ANY_CARD_ARTIST = "Card artist"
        private const val ANY_CARD_TEXT = "Card artist"
        private const val ANY_CARD_IMAGE = "Card image"
        private const val ANY_CARD_TYPE = "Card type"

        private val anyCardDomain = CardsDomain(
            id = ANY_CARD_ID,
            multiverseId = ANY_CARD_MULTIVERSE_ID,
            name = ANY_CARD_NAME,
            artist = ANY_CARD_ARTIST,
            type = ANY_CARD_TYPE,
            image = ANY_CARD_IMAGE,
            text = ANY_CARD_TEXT
        )

        private const val ERROR_MESSAGE = "Could not reach server"
    }
}
