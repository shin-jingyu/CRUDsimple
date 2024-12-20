package me.nettee.basket.controller;

import lombok.RequiredArgsConstructor;
import me.nettee.basket.controller.dto.BasketQueryDto.BasketSelectDetailResponse;
import me.nettee.basket.mapper.BasketDtoMapper;
import me.nettee.basket.usecase.BasketSelectUseCase;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
@Validated
public class BasketQueryApi {

    private final BasketSelectUseCase basketSelectUseCase;
    private final BasketDtoMapper basketDtoMapper;

    @GetMapping("/{id}")
    public BasketSelectDetailResponse selectDetailResponse(@PathVariable("id") Long id) {
        var list = basketSelectUseCase.findListByBasketId(id);

        return basketDtoMapper.toResponse(list);
    }
}
