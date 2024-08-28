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
('Red Dress', 'M', 'Red', 59.99, 'FashionBrand', 10),
('Blue Jeans', 'L', 'Blue', 49.99, 'DenimCo', 20),
('White Sneakers', '10', 'White', 79.99, 'Sporty', 15),
('Black T-shirt', 'S', 'Black', 19.99, 'CoolTees', 25),
('Green Jacket', 'L', 'Green', 99.99, 'Outdoors', 5),
('Yellow Hat', 'One Size', 'Yellow', 14.99, 'HatCo', 30),
('Pink Socks', 'One Size', 'Pink', 5.99, 'SockWorld', 100),
('Leather Belt', 'M', 'Brown', 24.99, 'BeltMasters', 50),
('Winter Coat', 'XL', 'Black', 149.99, 'WinterWear', 8),
('Designer Bag', 'L', 'Beige', 199.99, 'LuxuryBrand', 3),
('Red Dress', 'S', 'Red', 59.99, 'FashionBrand', 10),
('Blue Jeans', 'M', 'Blue', 49.99, 'DenimCo', 20),
('White Sneakers', '11', 'White', 79.99, 'Sporty', 15),
('Black T-shirt', 'M', 'Black', 19.99, 'CoolTees', 25),
('Green Jacket', 'M', 'Green', 99.99, 'Outdoors', 5),
('Yellow Hat', 'One Size', 'Yellow', 14.99, 'HatCo', 30),
('Pink Socks', 'One Size', 'Pink', 5.99, 'SockWorld', 100),
('Leather Belt', 'L', 'Brown', 24.99, 'BeltMasters', 50),
('Winter Coat', 'L', 'Black', 149.99, 'WinterWear', 8),
('Designer Bag', 'S', 'Beige', 199.99, 'LuxuryBrand', 3),
('Red Dress', 'L', 'Red', 59.99, 'FashionBrand', 10),
('Blue Jeans', 'S', 'Blue', 49.99, 'DenimCo', 20),
('White Sneakers', '9', 'White', 79.99, 'Sporty', 15),
('Black T-shirt', 'L', 'Black', 19.99, 'CoolTees', 25),
('Green Jacket', 'S', 'Green', 99.99, 'Outdoors', 5),
('Yellow Hat', 'One Size', 'Yellow', 14.99, 'HatCo', 30),
('Pink Socks', 'One Size', 'Pink', 5.99, 'SockWorld', 100),
('Leather Belt', 'S', 'Brown', 24.99, 'BeltMasters', 50),
('Winter Coat', 'M', 'Black', 149.99, 'WinterWear', 8),
('Designer Bag', 'M', 'Beige', 199.99, 'LuxuryBrand', 3);

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
(1, 1), (2, 3), (3, 4), (4, 2), (5, 5),
(6, 6), (7, 7), (8, 8), (9, 9), (10, 10),
(11, 1), (12, 3), (13, 4), (14, 2), (15, 5),
(16, 6), (17, 7), (18, 8), (19, 9), (20, 10),
(21, 1), (22, 3), (23, 4), (24, 2), (25, 5),
(26, 6), (27, 7), (28, 8), (29, 9), (30, 10);

-- Create orders
INSERT INTO `Order` (customer_id, order_date) VALUES
(1, '2024-01-15'), (2, '2024-02-20'), (3, '2024-03-10'),
(4, '2024-04-01'), (5, '2024-05-15'), (6, '2024-06-20'),
(7, '2024-07-25'), (8, '2024-08-30'), (9, '2024-09-10'),
(10, '2024-10-15');

-- Order items
INSERT INTO OrderItem (order_id, product_id, quantity) VALUES 
(1, 1, 2), (1, 2, 1), (2, 3, 1), (2, 4, 3), (3, 5, 1),
(4, 6, 2), (4, 7, 5), (5, 8, 1), (5, 9, 1), (6, 10, 1),
(7, 11, 2), (7, 12, 1), (8, 13, 1), (8, 14, 3), (9, 15, 1),
(10, 16, 2), (10, 17, 5), (1, 18, 1), (2, 19, 1), (3, 20, 1),
(4, 21, 2), (5, 22, 1), (6, 23, 1), (7, 24, 3), (8, 25, 1),
(9, 26, 2), (10, 27, 5), (1, 28, 1), (2, 29, 1), (3, 30, 1);