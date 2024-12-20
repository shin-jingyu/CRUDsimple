package me.nettee.basket.usecase;

import me.nettee.basket.repository.projection.BasketProjection.BasketDetailProjection;
import java.util.List;

public interface BasketSelectUseCase {
   List<BasketDetailProjection> findListByBasketId(Long id);
}
