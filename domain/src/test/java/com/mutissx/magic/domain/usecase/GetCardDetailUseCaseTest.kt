package com.mutissx.magic.domain.usecase

import com.mutissx.magic.core.api.ErrorParser
import com.mutissx.magic.core.domain.common.Resource
import com.mutissx.magic.core.testing.CoroutinesTestRule
import com.mutissx.magic.domain.model.CardsDomain
import com.mutissx.magic.domain.repository.CardsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetCardDetailUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutinesTestRule()

    private var cardsRepository: CardsRepository = mock()
    private var errorParser: ErrorParser = mock()

    private lateinit var getCardDetailUseCase: GetCardDetailUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getCardDetailUseCase = GetCardDetailUseCase(cardsRepository, errorParser)
    }

    @Test
    fun `invokeWithParams() returns Resource Success`() = runBlocking {
        // Given
        givenGetCharactersRepositoryIsSuccess()

        // When
        val result = getCardDetailUseCase.invokeWithParams(ANY_CARD_ID).toList()

        // Then
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertEquals(result[1].data, anyCardDomain)
    }

    @Test
    fun `invokeWithParams() returns Resource Error`() = runBlocking {
        // Given
        givenFailureErrorParsered()
        givenGetCharactersRepositoryIsFailure()

        // When
        val result = getCardDetailUseCase.invokeWithParams(ANY_CARD_ID).toList()

        // Then
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Error)
        assertEquals(result[1].message, anyThrowable.message)
    }

    private suspend fun givenGetCharactersRepositoryIsSuccess() {
        whenever(cardsRepository.getCardDetail(ANY_CARD_ID)) doReturn anyCardDomain
    }

    private suspend fun givenGetCharactersRepositoryIsFailure() {
        whenever(cardsRepository.getCardDetail(ANY_CARD_ID)) doThrow anyThrowable
    }

    private fun givenFailureErrorParsered() {
        whenever(errorParser.parseError(anyThrowable)) doReturn  anyThrowable
    }

    companion object {
        private const val ERROR_MESSAGE = "Error message"
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
        private val anyThrowable = java.lang.RuntimeException(ERROR_MESSAGE)
    }

}
