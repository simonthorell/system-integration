-- This is a comment
CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    position VARCHAR(50) NOT NULL,
    salary DECIMAL(10, 2),
    hire_date DATE
);

INSERT INTO employees (name, position, salary, hire_date) VALUES
('John Doe', 'Developer', 60000.00, '2023-01-15'),
('Jane Smith', 'Manager', 80000.00, '2022-03-12');

SELECT * FROM employees;