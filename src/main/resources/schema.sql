-- Crear la tabla de precios
CREATE TABLE tbl_prices (
    id_price BIGINT PRIMARY KEY,
    brand_id BIGINT,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    price_list INT,
    product_id BIGINT,
    priority INT,
    price DECIMAL(10, 2),
    currency VARCHAR(10)
);
