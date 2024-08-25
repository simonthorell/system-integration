INSERT INTO Category (name) VALUES ('Dresses'), ('Shirts'), ('Pants'), ('Shoes'), ('Sportswear');

INSERT INTO Product (name, size, color, price, brand, stock_quantity) VALUES 
('Red Dress', 'M', 'Red', 59.99, 'FashionBrand', 10),
('Blue Jeans', 'L', 'Blue', 49.99, 'DenimCo', 20),
('White Sneakers', '10', 'White', 79.99, 'Sporty', 15),
('Black T-shirt', 'S', 'Black', 19.99, 'CoolTees', 25),
('Green Jacket', 'L', 'Green', 99.99, 'Outdoors', 5);

INSERT INTO Customer (first_name, last_name, email, address, city) VALUES 
('Alice', 'Wonderland', 'alice@example.com', '123 Main St', 'Wonderland'),
('Bob', 'Builder', 'bob@example.com', '456 Elm St', 'Buildertown'),
('Charlie', 'Chocolate', 'charlie@example.com', '789 Oak St', 'Chocoland');

-- Link products to categories
INSERT INTO ProductCategory (product_id, category_id) VALUES
(1, 1), (2, 3), (3, 4), (4, 2), (5, 5);

-- Create orders
INSERT INTO `Order` (customer_id, order_date) VALUES
(1, '2023-01-15'), (2, '2023-02-20'), (3, '2023-03-10');

-- Order items
INSERT INTO OrderItem (order_id, product_id, quantity) VALUES 
(1, 1, 2), (1, 2, 1), (2, 3, 1), (2, 4, 3), (3, 5, 1);