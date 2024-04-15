DROP TABLE IF EXISTS pizza_ids;
DROP TABLE IF EXISTS orders;

CREATE TABLE pizza_ids
(
    order_id BIGINT NOT NULL,
    pizza_id BIGINT NOT NULL
);

CREATE TABLE orders
(
    id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT      NOT NULL,
    status  varchar(20) NOT NULL
);

ALTER TABLE pizza_ids
    ADD CONSTRAINT fk_pizza_ids_on_orders FOREIGN KEY (order_id) REFERENCES orders (id);