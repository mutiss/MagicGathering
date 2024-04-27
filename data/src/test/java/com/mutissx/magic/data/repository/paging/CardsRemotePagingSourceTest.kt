package com.mutissx.magic.data.repository.paging

import androidx.paging.PagingSource
import com.mutissx.magic.core.api.ErrorParser
import com.mutissx.magic.core.testing.CoroutinesTestRule
import com.mutissx.magic.data.remote.api.CardsApi
import com.mutissx.magic.data.remote.dto.CardsDto
import com.mutissx.magic.data.remote.dto.CardsResponse
import com.mutissx.magic.domain.model.CardsDomain
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
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
class CardsRemotePagingSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutinesTestRule()

    private var cardsApi: CardsApi = mock()
    private var errorParser: ErrorParser = mock()

    private lateinit var pagingSource: CardsRemotePagingSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        pagingSource = CardsRemotePagingSource(cardsApi, errorParser)
    }

    @Test
    fun `load should return Page when API returns successful response`() = runBlocking {
        // Given
        val page = 1
        val response = CardsResponse(listOf(anyCardDto))
        val expectedData = listOf(anyCardDomain).toList()
        whenever(cardsApi.getCards(page)) doReturn response

        // When
        val result = pagingSource.load(PagingSource.LoadParams.Refresh(page, 10, false))

        // Then
        assertTrue(result is PagingSource.LoadResult.Page)
        val pageData = (result as PagingSource.LoadResult.Page).data.toList()
        assertEquals(pageData[0], expectedData[0])
        assertNull(result.prevKey)
        assertEquals(result.nextKey, page + 1)
    }

    @Test
    fun `load should return Page with null nextKey when API returns empty response`() = runBlocking {
        // Given
        val page = 1
        val response = CardsResponse(emptyList())
        val expectedData = listOf<CardsDomain>()
        whenever(cardsApi.getCards(page)) doReturn response

        // When
        val result = pagingSource.load(PagingSource.LoadParams.Refresh(page, 10, false))

        // Then
        assertTrue(result is PagingSource.LoadResult.Page)
        val pageData = (result as PagingSource.LoadResult.Page).data.toList()
        assertEquals(pageData, expectedData)
        assertNull(result.prevKey)
        assertNull(result.nextKey)
    }

    @Test
    fun `load should return Error when API throws exception`() = runBlocking {
        // Given
        val page = 1
        val exception = anyThrowable
        whenever(cardsApi.getCards(page)) doThrow exception
        whenever(errorParser.parseError(exception)) doReturn anyThrowable

        // When
        val result = pagingSource.load(PagingSource.LoadParams.Refresh(page, 10, false))

        // Then
        assertTrue(result is PagingSource.LoadResult.Error)
        assertEquals((result as PagingSource.LoadResult.Error).throwable, anyThrowable)
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

        private val anyCardDto = CardsDto(
            id = ANY_CARD_ID,
            multiverseId = ANY_CARD_MULTIVERSE_ID,
            name = ANY_CARD_NAME,
            artist = ANY_CARD_ARTIST,
            type = ANY_CARD_TYPE,
            image = ANY_CARD_IMAGE,
            text = ANY_CARD_TEXT
        )
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
