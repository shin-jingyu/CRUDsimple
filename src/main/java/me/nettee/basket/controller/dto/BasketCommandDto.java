package me.nettee.basket.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import me.nettee.basket.domain.Basket;

public final class BasketCommandDto {

    private BasketCommandDto() {}

    @Builder
    public record BasketCreateRequest(
            @NotNull
            Long basketId,

            @NotBlank(message = "상품 이름이 필요합니다.")
            String itemName,

            @NotNull(message = "상품의 가격이 필요합니다.")
            Long itemPrice
    ){}

    @Builder
    public record BasketUpdateRequest(
            @NotNull(message = "장바구니 번호가 지정되지 않았습니다.")
            Long id,

            @NotBlank(message = "상품 이름이 필요합니다.")
            String itemName,

            @NotNull(message = "상품의 가격이 필요합니다.")
            Long itemPrice
    ){}

    @Builder
    public record BasketCreateResponse(
            Basket basket
    ){}

    @Builder
    public record BasketUpdateResponse(
            Basket basket
    ){}
}
