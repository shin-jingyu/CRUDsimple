import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import me.nettee.basket.controller.dto.BasketCommandDto
import me.nettee.basket.domain.Basket
import me.nettee.basket.exception.BasketCommandErrorCode
import me.nettee.basket.mapper.BasketDtoMapper
import me.nettee.basket.repository.BasketCommandRepository
import me.nettee.basket.service.BasketCommandService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Instant
import java.util.*

class BasketCommandServiceTest {

    private val basketCommandRepository: BasketCommandRepository = mockk();
    private val basketDtoMapper: BasketDtoMapper = mockk();

    private val basketCommandService = BasketCommandService(basketCommandRepository, basketDtoMapper)


    @Test
    fun `create with Basket should save and return Basket`() {
        // Given
        val basket = Basket(id = 1L, itemName = "item", itemPrice = 1000L)
        every { basketCommandRepository.save(basket) } returns basket

        // When
        val result = basketCommandService.create(basket)

        // Then
        assertEquals(basket, result)
        verify { basketCommandRepository.save(basket) }
    }

    @Test
    fun `create with BasketCreateRequest should map and save Basket`() {
        // Given
        val now = Instant.now()
        val request = BasketCommandDto.BasketCreateRequest("item", 1000L)
        val mappedBasket = Basket(id = 1L, itemName = "item", itemPrice = 1000L)
        every { basketDtoMapper.toEntity(request, now, now) } returns mappedBasket
        every { basketCommandRepository.save(mappedBasket) } returns mappedBasket

        // When
        val result = basketCommandService.create(request)

        // Then
        assertEquals(mappedBasket, result)
        verify { basketDtoMapper.toEntity(request, any(), any()) }
        verify { basketCommandRepository.save(mappedBasket) }
    }

    @Test
    fun `update should find, modify, and return updated Basket`() {
        // Given
        val id = 1L
        val itemName = "updatedItem"
        val itemPrice = 2000L
        val existingBasket = Basket(id = id, itemName = "item", itemPrice = 1000L)
        every { basketCommandRepository.findById(id) } returns Optional.of(existingBasket)
        every { basketCommandRepository.save(existingBasket) } returns existingBasket

        // When
        val result = basketCommandService.update(id, itemName, itemPrice)

        // Then
        assertEquals(existingBasket, result)
        verify { basketCommandRepository.findById(id) }
    }

    @Test
    fun `update should throw exception if Basket not found`() {
        // Given
        val id = 1L
        every { basketCommandRepository.findById(id) } returns Optional.empty()

        // When/Then
        assertThrows<BasketCommandErrorCode.BASKET_NOT_FOUND.defaultException> {
            basketCommandService.update(id, "item", 2000L)
        }
        verify { basketCommandRepository.findById(id) }
    }

    @Test
    fun `delete should call repository to delete by id`() {
        // Given
        val id = 1L
        every { basketCommandRepository.deleteById(id) } returns Unit

        // When
        basketCommandService.delete(id)

        // Then
        verify { basketCommandRepository.deleteById(id) }
    }
}