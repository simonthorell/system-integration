INSERT INTO Category (name) VALUES 
('Dresses'), 
('Shirts'), 
('Pants'), 
('Shoes'), 
('Sportswear'),
('Hats'),
('Socks'),
('Accessories'),
('Coats'),
('Bags');

INSERT INTO Product (name, size, color, price, brand, stock_quantity) VALUES 
('Red Dress', 'M', 'Red', 599.00, 'FashionBrand', 10),
('Blue Jeans', 'L', 'Blue', 499.00, 'DenimCo', 20),
('White Sneakers', '10', 'White', 799.00, 'Sporty', 15),
('Black T-shirt', 'S', 'Black', 199.00, 'CoolTees', 25),
('Green Jacket', 'L', 'Green', 999.00, 'Outdoors', 5),
('Yellow Hat', 'One Size', 'Yellow', 149.00, 'HatCo', 30),
('Pink Socks', 'One Size', 'Pink', 59.00, 'SockWorld', 100),
('Leather Belt', 'M', 'Brown', 249.00, 'BeltMasters', 50),
('Winter Coat', 'XL', 'Black', 1499.00, 'WinterWear', 8),
('Designer Bag', 'L', 'Beige', 1999.00, 'LuxuryBrand', 3),
('Red Dress', 'S', 'Red', 599.00, 'FashionBrand', 10),
('Blue Jeans', 'M', 'Blue', 499.00, 'DenimCo', 20),
('White Sneakers', '11', 'White', 799.00, 'Sporty', 15),
('Black T-shirt', 'M', 'Black', 199.00, 'CoolTees', 25),
('Green Jacket', 'M', 'Green', 999.00, 'Outdoors', 5),
('Yellow Hat', 'One Size', 'Yellow', 149.00, 'HatCo', 30),
('Pink Socks', 'One Size', 'Pink', 59.00, 'SockWorld', 100),
('Leather Belt', 'L', 'Brown', 249.00, 'BeltMasters', 50),
('Winter Coat', 'L', 'Black', 1499.00, 'WinterWear', 8),
('Designer Bag', 'S', 'Beige', 1999.00, 'LuxuryBrand', 3),
('Red Dress', 'L', 'Red', 599.00, 'FashionBrand', 10),
('Blue Jeans', 'S', 'Blue', 499.00, 'DenimCo', 20),
('White Sneakers', '9', 'White', 799.00, 'Sporty', 15),
('Black T-shirt', 'L', 'Black', 199.00, 'CoolTees', 25),
('Green Jacket', 'S', 'Green', 999.00, 'Outdoors', 5),
('Yellow Hat', 'One Size', 'Yellow', 149.00, 'HatCo', 30),
('Pink Socks', 'One Size', 'Pink', 59.00, 'SockWorld', 100),
('Leather Belt', 'S', 'Brown', 249.00, 'BeltMasters', 50),
('Winter Coat', 'M', 'Black', 1499.00, 'WinterWear', 8),
('Designer Bag', 'M', 'Beige', 1999.00, 'LuxuryBrand', 3),
('Cool Pants', '38', 'Black', 399.00, 'SweetPants', 20),
('Cool Pants', '34', 'Black', 399.00, 'SweetPants', 20),
('Cool Pants', '32', 'Black', 399.00, 'SweetPants', 20);


INSERT INTO Customer (first_name, last_name, email, address, city) VALUES 
('Alice', 'Wonderland', 'alice@example.com', '123 Main St', 'Wonderland'),
('Bob', 'Builder', 'bob@example.com', '456 Elm St', 'Buildertown'),
('Charlie', 'Chocolate', 'charlie@example.com', '789 Oak St', 'Chocoland'),
('David', 'Smith', 'david@example.com', '101 Maple St', 'Smithville'),
('Eva', 'Green', 'eva@example.com', '202 Pine St', 'Greenville'),
('Frank', 'Ocean', 'frank@example.com', '303 Cedar St', 'Oceanside'),
('Grace', 'Hopper', 'grace@example.com', '404 Birch St', 'HopperTown'),
('Henry', 'Ford', 'henry@example.com', '505 Spruce St', 'Fordville'),
('Ivy', 'League', 'ivy@example.com', '606 Ash St', 'LeagueCity'),
('Jack', 'Sparrow', 'jack@example.com', '707 Willow St', 'SparrowBay');

-- Link products to categories
INSERT INTO ProductCategory (product_id, category_id) VALUES 
-- Red Dress belongs to Dresses (1)
(1, 1), (11, 1), (21, 1),
-- Blue Jeans belongs to Pants (3)
(2, 3), (2, 5),
(12, 3), (12, 5),
(22, 3), (22, 5),
-- White Sneakers belong to Shoes (4)
(3, 4), (13, 4), (23, 4),
-- Black T-shirt belongs to both Shirts (2) and Sportswear (5)
(4, 2), (4, 5),
(14, 2), (14, 5),
(24, 2), (24, 5),
-- Green Jacket belongs to both Coats (9) and Sportswear (5)
(5, 5), (5, 9),
(15, 5), (15, 9),
(25, 5), (25, 9),
-- Yellow Hat belongs to both Hats (6) and Accessories (8)
(6, 6), (6, 8),
(16, 6), (16, 8),
(26, 6), (26, 8),
-- Pink Socks belong to both Socks (7) and Accessories (8)
(7, 7), (7, 8),
(17, 7), (17, 8),
(27, 7), (27, 8),
-- Leather Belt belongs to Accessories (8)
(8, 8), (18, 8), (28, 8),
-- Winter Coat belongs to Coats (9)
(9, 9), (19, 9), (29, 9),
-- Designer Bag belongs to both Bags (10) and Accessories (8)
(10, 10), (10, 8),
(20, 10), (20, 8),
(30, 10), (30, 8),
-- Comfy Black Pants belongs to both Pants (3) and Sportswear (5)
(31, 3), (31, 5),
(32, 3), (32, 5),
(33, 3), (33, 5);

-- Create orders
INSERT INTO `Order` (customer_id, order_date) VALUES
(1, '2024-01-15'), (2, '2024-02-20'), (3, '2024-03-10'),
(4, '2024-03-01'), (5, '2024-03-15'), (6, '2024-06-20'),
(7, '2024-07-25'), (8, '2024-08-30'), (9, '2024-09-10'),
(10, '2024-10-15');

-- Order items
INSERT INTO OrderItem (order_id, product_id, quantity) VALUES 
(1, 1, 2), (1, 2, 6), (2, 3, 1), (2, 4, 3), (3, 5, 3),
(4, 6, 1), (4, 7, 1), (5, 8, 1), (5, 9, 1), (6, 10, 1),
(7, 11, 2), (7, 12, 1), (8, 13, 1), (8, 14, 3), (9, 15, 1),
(10, 16, 2), (10, 17, 3), (1, 18, 2), (2, 19, 1), (3, 20, 1),
(1, 21, 1), (5, 22, 2), (6, 23, 1), (7, 24, 3), (8, 25, 1),
(9, 24, 1), (10, 27, 4), (1, 28, 1), (2, 29, 1), (3, 30, 1),
(1, 31, 5), (2, 31, 3), (3, 32, 3), (4, 31, 1), (5, 33, 4);