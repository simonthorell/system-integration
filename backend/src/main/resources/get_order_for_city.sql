SELECT c.city, SUM(oi.quantity * p.price) AS total_order_value
FROM Customer c
JOIN `Order` o ON c.id = o.customer_id
JOIN OrderItem oi ON o.id = oi.order_id
JOIN Product p ON oi.product_id = p.id
GROUP BY c.city
HAVING total_order_value > :threshold
ORDER BY total_order_value DESC;