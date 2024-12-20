package me.nettee.basket.mapper;

import me.nettee.basket.controller.dto.BasketCommandDto.BasketCreateRequest;
import me.nettee.basket.controller.dto.BasketQueryDto.BasketSelectDetailResponse;
import me.nettee.basket.domain.Basket;
import me.nettee.basket.repository.projection.BasketProjection.BasketDetailProjection;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.util.List;

import static org.mapstruct.ap.internal.gem.MappingConstantsGem.ComponentModelGem.SPRING;

@Mapper(componentModel = SPRING)
public interface BasketDtoMapper {
    Basket toEntity(BasketCreateRequest basketCreateRequest, Instant createdAt, Instant updatedAt);

    default BasketSelectDetailResponse toResponse(List<BasketDetailProjection> basketProjections){
        return BasketSelectDetailResponse.builder()
                .basket(basketProjections)
                .build();
    };
}
