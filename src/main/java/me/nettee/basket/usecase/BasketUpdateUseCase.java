package me.nettee.basket.usecase;

import me.nettee.basket.domain.Basket;

public interface BasketUpdateUseCase {
    Basket update(Long id, String itemName, Long itemPrice);
}
