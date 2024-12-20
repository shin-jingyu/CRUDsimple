package me.nettee.basket.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.Instant;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long basketId;
    private String itemName;
    private Long itemPrice;
    private Instant createdAt;
    private Instant updatedAt;

    @Builder(
            builderClassName = "UpdateBasketBuilder",
            buildMethodName = "update",
            builderMethodName = "prepareUpdate"
    )
    public void updateBasket(String itemName, Long itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
}
