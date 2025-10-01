-- Insert Categories
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'High-performance laptops and notebooks', 'Laptops');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Smartphones and mobile devices', 'Smartphones');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Audio devices and speakers', 'Speakers');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Wearable tech and fitness trackers', 'Wearables');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Storage devices and drives', 'Storage');


-- Products for Laptops
INSERT INTO product (id, available_quantity, description, name, price, cat_id)
VALUES
    (nextval('product_seq'), 12, '14-inch ultrabook with Intel i7 and 16GB RAM', 'Ultrabook Pro 14', 1249.99, (SELECT id FROM category WHERE name = 'Laptops')),
    (nextval('product_seq'), 10, '15.6-inch gaming laptop with RTX graphics', 'Gaming Beast 15', 1599.99, (SELECT id FROM category WHERE name = 'Laptops')),
    (nextval('product_seq'), 8, 'Lightweight business laptop with long battery life', 'BizMate 13', 999.99, (SELECT id FROM category WHERE name = 'Laptops')),
    (nextval('product_seq'), 15, '2-in-1 convertible laptop with touchscreen', 'FlexBook 14', 849.99, (SELECT id FROM category WHERE name = 'Laptops')),
    (nextval('product_seq'), 20, 'Budget laptop with AMD Ryzen processor', 'EconoBook 15', 549.99, (SELECT id FROM category WHERE name = 'Laptops'));


-- Products for Smartphones
INSERT INTO product (id, available_quantity, description, name, price, cat_id)
VALUES
    (nextval('product_seq'), 25, 'Flagship smartphone with triple camera setup', 'SmartOne X', 1099.99, (SELECT id FROM category WHERE name = 'Smartphones')),
    (nextval('product_seq'), 30, 'Mid-range 5G smartphone with AMOLED display', 'SpeedPhone 5G', 599.99, (SELECT id FROM category WHERE name = 'Smartphones')),
    (nextval('product_seq'), 40, 'Compact smartphone with high refresh rate display', 'MiniSmart Z', 499.99, (SELECT id FROM category WHERE name = 'Smartphones')),
    (nextval('product_seq'), 18, 'Rugged smartphone with waterproof design', 'ToughPhone R', 699.99, (SELECT id FROM category WHERE name = 'Smartphones')),
    (nextval('product_seq'), 35, 'Entry-level Android phone with large battery', 'BudgetPhone Lite', 199.99, (SELECT id FROM category WHERE name = 'Smartphones'));


-- Products for Speakers
INSERT INTO product (id, available_quantity, description, name, price, cat_id)
VALUES
    (nextval('product_seq'), 22, 'Wireless Bluetooth speaker with deep bass', 'BassBoom X1', 149.99, (SELECT id FROM category WHERE name = 'Speakers')),
    (nextval('product_seq'), 18, 'Portable waterproof speaker for outdoors', 'AquaSound Mini', 99.99, (SELECT id FROM category WHERE name = 'Speakers')),
    (nextval('product_seq'), 25, 'Smart speaker with voice assistant integration', 'SmartEcho Plus', 179.99, (SELECT id FROM category WHERE name = 'Speakers')),
    (nextval('product_seq'), 20, 'Home theater soundbar with subwoofer', 'CinemaBar 300', 249.99, (SELECT id FROM category WHERE name = 'Speakers')),
    (nextval('product_seq'), 28, 'Compact bookshelf speakers with Hi-Fi sound', 'HiFiBookshelf 2', 129.99, (SELECT id FROM category WHERE name = 'Speakers'));


-- Products for Wearables
INSERT INTO product (id, available_quantity, description, name, price, cat_id)
VALUES
    (nextval('product_seq'), 30, 'Smartwatch with fitness and health tracking', 'FitTrack Pro', 299.99, (SELECT id FROM category WHERE name = 'Wearables')),
    (nextval('product_seq'), 22, 'Fitness band with heart rate and sleep monitor', 'HealthBand 3', 129.99, (SELECT id FROM category WHERE name = 'Wearables')),
    (nextval('product_seq'), 18, 'Smart glasses with augmented reality features', 'VisionAR One', 599.99, (SELECT id FROM category WHERE name = 'Wearables')),
    (nextval('product_seq'), 25, 'Premium smartwatch with cellular connectivity', 'TimeX Elite', 399.99, (SELECT id FROM category WHERE name = 'Wearables')),
    (nextval('product_seq'), 35, 'Kids smartwatch with GPS and SOS feature', 'KiddoWatch', 149.99, (SELECT id FROM category WHERE name = 'Wearables'));


-- Products for Storage
INSERT INTO product (id, available_quantity, description, name, price, cat_id)
VALUES
    (nextval('product_seq'), 50, 'External SSD with USB-C interface, 1TB', 'FlashDrive X1TB', 179.99, (SELECT id FROM category WHERE name = 'Storage')),
    (nextval('product_seq'), 40, 'Portable hard drive with 4TB capacity', 'MegaStore 4TB', 139.99, (SELECT id FROM category WHERE name = 'Storage')),
    (nextval('product_seq'), 45, 'High-speed NVMe M.2 SSD, 512GB', 'SpeedSSD 512', 99.99, (SELECT id FROM category WHERE name = 'Storage')),
    (nextval('product_seq'), 38, 'USB 3.2 Flash Drive, 128GB', 'QuickStick 128', 29.99, (SELECT id FROM category WHERE name = 'Storage')),
    (nextval('product_seq'), 25, 'Network Attached Storage (NAS) device, 2-Bay', 'HomeNAS Duo', 249.99, (SELECT id FROM category WHERE name = 'Storage'));
