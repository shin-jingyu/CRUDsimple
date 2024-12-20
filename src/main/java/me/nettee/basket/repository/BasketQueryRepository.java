package me.nettee.basket.repository;

import me.nettee.basket.domain.Basket;
import me.nettee.basket.repository.projection.BasketProjection.BasketDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketQueryRepository extends JpaRepository<Basket, Long> {
    List<BasketDetailProjection> findListByBasketId(Long basketId);
}
