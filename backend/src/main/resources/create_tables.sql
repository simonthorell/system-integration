CREATE TABLE IF NOT EXISTS Customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Order` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    order_date DATE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(id)
);

CREATE TABLE IF NOT EXISTS Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    size VARCHAR(10) NOT NULL,
    color VARCHAR(20) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    stock_quantity INT NOT NULL
);

CREATE TABLE IF NOT EXISTS OrderItem (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES `Order`(id),
    FOREIGN KEY (product_id) REFERENCES Product(id)
);

CREATE TABLE IF NOT EXISTS Category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS ProductCategory (
    product_id INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES Product(id),
    FOREIGN KEY (category_id) REFERENCES Category(id)
);
