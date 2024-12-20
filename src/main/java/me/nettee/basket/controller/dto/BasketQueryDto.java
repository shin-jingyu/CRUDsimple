package me.nettee.basket.controller.dto;

import lombok.Builder;
import me.nettee.basket.repository.projection.BasketProjection.BasketDetailProjection;

import java.util.List;

public class BasketQueryDto {

    private BasketQueryDto() {}

    @Builder
    public record BasketSelectDetailResponse(
            List<BasketDetailProjection> basket
    ){}
}
