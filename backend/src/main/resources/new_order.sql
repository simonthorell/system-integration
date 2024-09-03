INSERT INTO `Order` (customer_id, order_date)
VALUES (:customer_id, CURDATE()); -- Replace '1' with the actual customer ID
SET @order_id = LAST_INSERT_ID();
INSERT INTO OrderItem (order_id, product_id, quantity) VALUES 
(@order_id, :product_id, :quantity), 
