package me.nettee.basket.repository;

import me.nettee.basket.domain.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketCommandRepository extends JpaRepository<Basket, Long> {
}
