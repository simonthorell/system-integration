SELECT c.name, COUNT(p.id) AS product_count
FROM Category c
JOIN ProductCategory pc ON c.id = pc.category_id
JOIN Product p ON pc.product_id = p.id
GROUP BY c.name
ORDER BY product_count DESC;