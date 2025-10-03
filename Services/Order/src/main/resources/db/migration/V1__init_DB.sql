CREATE TABLE customer_orders (
    id SERIAL PRIMARY KEY,
    reference VARCHAR(255),
    total_amount DECIMAL(19,2),
    payment_method VARCHAR(50),
    customer_id VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL
);

CREATE TABLE orders_items (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT,
    quantity DOUBLE PRECISION,
    CONSTRAINT fk_order
        FOREIGN KEY (order_id) REFERENCES customer_orders(id)
        ON DELETE CASCADE
);
