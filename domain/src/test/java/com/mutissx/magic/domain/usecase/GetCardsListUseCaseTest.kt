package com.mutissx.magic.domain.usecase

import androidx.paging.PagingData
import com.mutissx.magic.core.testing.CoroutinesTestRule
import com.mutissx.magic.domain.model.CardsDomain
import com.mutissx.magic.domain.repository.CardsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetCardsListUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutinesTestRule()

    private var cardsRepository: CardsRepository = mock()

    private lateinit var getCardsListUseCase: GetCardsListUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getCardsListUseCase = GetCardsListUseCase(cardsRepository)
    }

    @Test
    fun `invoke should return data when repository returns data`() = runBlocking {
        // Given
        val data = PagingData.from(listOf(
            anyCardDomain,
            anyCardDomain.copy(id = ANY_CARD_ID_2)
        ))
        whenever(cardsRepository.getCardsList()) doReturn flowOf(data)

        // When
        val result = getCardsListUseCase().toList()

        // Then
        assertEquals(result[0], data)
    }

    companion object {
        private const val ANY_CARD_ID = "Card id"
        private const val ANY_CARD_ID_2 = "Card id 2"
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
    }
}
