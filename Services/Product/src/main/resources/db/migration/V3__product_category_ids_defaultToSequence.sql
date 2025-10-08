-- 1. Set sequences to start after current max IDs
SELECT setval('category_seq', (SELECT COALESCE(MAX(id), 0) FROM category));
SELECT setval('product_seq', (SELECT COALESCE(MAX(id), 0) FROM product));

-- 2. Attach sequences to the id columns
ALTER TABLE category ALTER COLUMN id SET DEFAULT nextval('category_seq');
ALTER TABLE product ALTER COLUMN id SET DEFAULT nextval('product_seq');
