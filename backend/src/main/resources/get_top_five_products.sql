SELECT p.name, p.price, p.brand, SUM(oi.quantity) as amount_sold
FROM `Order` o
JOIN OrderItem oi ON o.id = oi.order_id
JOIN Product p ON oi.product_id = p.id
WHERE YEAR(o.order_date) = YEAR(CURDATE())
GROUP BY p.name, p.price, p.brand
ORDER BY amount_sold DESC
LIMIT 5;