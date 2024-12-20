package me.nettee.basket.service;

import lombok.RequiredArgsConstructor;
import me.nettee.basket.repository.BasketQueryRepository;
import me.nettee.basket.repository.projection.BasketProjection.BasketDetailProjection;
import me.nettee.basket.usecase.BasketSelectUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketQueryService implements BasketSelectUseCase {

    private final BasketQueryRepository basketQueryRepository;


    @Override
    public List<BasketDetailProjection> findListByBasketId(Long id) {
        return basketQueryRepository.findListByBasketId(id);
    }
}
