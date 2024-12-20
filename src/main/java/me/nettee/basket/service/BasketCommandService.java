package me.nettee.basket.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.nettee.basket.controller.dto.BasketCommandDto;
import me.nettee.basket.domain.Basket;
import me.nettee.basket.exception.BasketCommandErrorCode;
import me.nettee.basket.mapper.BasketDtoMapper;
import me.nettee.basket.repository.BasketCommandRepository;
import me.nettee.basket.usecase.BasketCreateUseCase;
import me.nettee.basket.usecase.BasketDeleteUseCase;
import me.nettee.basket.usecase.BasketUpdateUseCase;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class BasketCommandService implements
        BasketCreateUseCase,
        BasketUpdateUseCase,
        BasketDeleteUseCase {

    private final BasketCommandRepository basketCommandRepository;
    private final BasketDtoMapper basketDtoMapper;

    @Override
    public Basket create(Basket basket) {
        return basketCommandRepository.save(basket);
    }

    @Override
    public Basket create(BasketCommandDto.BasketCreateRequest basketCreateRequest) {
        Instant now = Instant.now();
        Basket basket = basketDtoMapper.toEntity(basketCreateRequest, now, now);
        return create(basket);
    }

    @Transactional
    @Override
    public Basket update(Long id, String itemName, Long itemPrice) {
        Basket basket = basketCommandRepository.findById(id).orElseThrow(BasketCommandErrorCode.BASKET_NOT_FOUND::defaultException);
        basket.prepareUpdate()
                .itemName(itemName)
                .itemPrice(itemPrice)
                .update();
        return basket;
    }

    @Override
    public void delete(Long id) {
        basketCommandRepository.deleteById(id);
    }


}
