SELECT c.first_name, c.last_name, SUM(oi.quantity * p.price) AS total_spent
FROM Customer c
JOIN `Order` o ON c.id = o.customer_id
JOIN OrderItem oi ON o.id = oi.order_id
JOIN Product p ON oi.product_id = p.id
GROUP BY c.first_name, c.last_name
ORDER BY total_spent DESC;
