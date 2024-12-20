CREATE TABLE IF NOT EXISTS basket (
    id          BIGSERIAL,
    basket_id   BIGSERIAL,
    item_price  BIGSERIAL,
    item_name   VARCHAR(255),
    created_at  TIMESTAMP       DEFAULT NOW(),
    updated_at  TIMESTAMP,

    CONSTRAINT pk_basket PRIMARY KEY (id)
);