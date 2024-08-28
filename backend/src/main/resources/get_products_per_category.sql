SELECT c.name, COUNT(p.id) as product_count
FROM Category c
JOIN Product p ON c.id = p.id
GROUP BY c.name
ORDER BY product_count DESC;