SELECT DATE_FORMAT(o.order_date, '%M') as month, 
       SUM(oi.quantity * p.price) as total_sales_value 
FROM `Order` o 
JOIN OrderItem oi ON o.id = oi.order_id 
JOIN Product p ON oi.product_id = p.id
WHERE YEAR(o.order_date) = YEAR(CURDATE()) 
GROUP BY month 
ORDER BY total_sales_value DESC 
LIMIT 1;
