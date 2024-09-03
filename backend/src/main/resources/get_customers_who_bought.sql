SELECT CONCAT(c.first_name, ' ', c.last_name) as name
FROM Customer c
JOIN `Order` o ON c.id = o.customer_id
JOIN OrderItem oi ON o.id = oi.order_id
JOIN Product p ON oi.product_id = p.id
WHERE (COALESCE(:productName, '') = '' OR LOWER(p.name) = LOWER(:productName))
  AND (COALESCE(:color, '') = '' OR LOWER(p.color) = LOWER(:color))
  AND (COALESCE(:size, '') = '' OR LOWER(p.size) = LOWER(:size))
  AND (COALESCE(:brand, '') = '' OR LOWER(p.brand) = LOWER(:brand));

