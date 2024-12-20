package me.nettee.basket.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import me.nettee.basket.controller.dto.BasketCommandDto.BasketUpdateRequest;
import me.nettee.basket.controller.dto.BasketCommandDto.BasketUpdateResponse;
import me.nettee.basket.controller.dto.BasketCommandDto.BasketCreateResponse;
import me.nettee.basket.controller.dto.BasketCommandDto.BasketCreateRequest;
import me.nettee.basket.usecase.BasketCreateUseCase;
import me.nettee.basket.usecase.BasketDeleteUseCase;
import me.nettee.basket.usecase.BasketUpdateUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
@Validated
public class BasketCommandApi {

    private final BasketCreateUseCase basketCreateUseCase;
    private final BasketUpdateUseCase basketUpdateUseCase;
    private final BasketDeleteUseCase basketDeleteUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BasketCreateResponse createBasket(@Validated @RequestBody BasketCreateRequest basketCreateRequest) {
        var basket = basketCreateUseCase.create(basketCreateRequest);
        return BasketCreateResponse.builder()
                .basket(basket)
                .build();
    }

    @PutMapping
    public BasketUpdateResponse updateBasket(
            @Validated @RequestBody BasketUpdateRequest basketUpdateRequest
    ){
        var basket = basketUpdateUseCase.update(basketUpdateRequest.id(), basketUpdateRequest.itemName(), basketUpdateRequest.itemPrice());

        return BasketUpdateResponse.builder()
                .basket(basket)
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBasket(
            @NotNull(message = "장바구니 번호가 올바르지 않습니다.")
            @PathVariable("id") Long id
    ){
        basketDeleteUseCase.delete(id);
    }
}
