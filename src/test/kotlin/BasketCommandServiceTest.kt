import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import me.nettee.basket.controller.dto.BasketCommandDto
import me.nettee.basket.domain.Basket
import me.nettee.basket.mapper.BasketDtoMapper
import me.nettee.basket.repository.BasketCommandRepository
import me.nettee.basket.service.BasketCommandService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Instant.now


class BasketCommandServiceTest {

    @MockK
    private lateinit var basketCommandRepository: BasketCommandRepository

    @MockK
    private lateinit var basketDtoMapper: BasketDtoMapper

    private lateinit var basketCommandService: BasketCommandService

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this) // Mock 객체 초기화
        basketCommandService = BasketCommandService(basketCommandRepository, basketDtoMapper)
    }

    @Test
    fun `create Basket save Test`() {
        // Given
        val basket = Basket(1,1,"초콜릿",1000, now(), now())
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
        val now = now()
        val request = BasketCommandDto.BasketCreateRequest(1, "초콜릿",1500)
        val basket =  Basket(1,1,"초콜릿",1000, now(), now())
        every { basketDtoMapper.toEntity(request, now, now) } returns basket
        every { basketCommandRepository.save(basket) } returns basket

        // When
        val result = basketCommandService.create(basket)

        // Then
        assertEquals(basket, result)
        verify { basketDtoMapper.toEntity(request, now(), now()) }
        verify { basketCommandRepository.save(basket) }
    }

//    @Test
//    fun `update should find, modify, and return updated Basket`() {
//        // Given
//        val id = 1L
//        val itemName = "updatedItem"
//        val itemPrice = 2000L
//        val existingBasket = Basket(id = id, itemName = "item", itemPrice = 1000L)
//        every { basketCommandRepository.findById(id) } returns Optional.of(existingBasket)
//        every { basketCommandRepository.save(existingBasket) } returns existingBasket
//
//        // When
//        val result = basketCommandService.update(id, itemName, itemPrice)
//
//        // Then
//        assertEquals(existingBasket, result)
//        verify { basketCommandRepository.findById(id) }
//    }
//
//    @Test
//    fun `update should throw exception if Basket not found`() {
//        // Given
//        val id = 1L
//        every { basketCommandRepository.findById(id) } returns Optional.empty()
//
//        // When/Then
//        assertThrows<BasketCommandErrorCode.BASKET_NOT_FOUND.defaultException> {
//            basketCommandService.update(id, "item", 2000L)
//        }
//        verify { basketCommandRepository.findById(id) }
//    }
//
//    @Test
//    fun `delete should call repository to delete by id`() {
//        // Given
//        val id = 1L
//        every { basketCommandRepository.deleteById(id) } returns Unit
//
//        // When
//        basketCommandService.delete(id)
//
//        // Then
//        verify { basketCommandRepository.deleteById(id) }
//    }
}