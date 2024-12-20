package me.nettee.basket.usecase;


import me.nettee.basket.controller.dto.BasketCommandDto.BasketCreateRequest;
import me.nettee.basket.domain.Basket;

public interface BasketCreateUseCase {
    Basket create(Basket basket);
    Basket create(BasketCreateRequest basketCreateRequest);
}
