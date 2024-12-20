package me.nettee.basket.repository.projection;

import lombok.Builder;
import java.time.Instant;

public final class BasketProjection {

    @Builder
    public record BasketDetailProjection(
            Long id,
            Long basketId,
            String itemName,
            Long itemPrice,
            Instant createdAt,
            Instant updatedAt
    ){}

}
