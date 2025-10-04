CREATE TABLE customers_payment (
    id SERIAL PRIMARY KEY,
    amount NUMERIC(19,2) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    order_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);
