-- Create the Clothes Table
CREATE TABLE IF NOT EXISTS clothes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    size VARCHAR(10),
    color VARCHAR(20),
    price DECIMAL(10, 2)
);

-- Insert Items into the Clothes Table
INSERT INTO clothes (name, size, color, price) VALUES ('T-shirt', 'M', 'Red', 19.99);
INSERT INTO clothes (name, size, color, price) VALUES ('Jeans', 'L', 'Blue', 49.99);
INSERT INTO clothes (name, size, color, price) VALUES ('Jacket', 'XL', 'Black', 89.99);
INSERT INTO clothes (name, size, color, price) VALUES ('Sneakers', '10', 'White', 59.99);

-- Select All Items from the Clothes Table
SELECT * FROM clothes;