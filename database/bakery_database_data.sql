INSERT INTO SUPPLIER (SUPPLIER_ID, NAME, ADDRESS, PHONE) VALUES
(1, 'Supplier1', 'Supplier Street 1', '123456789'),
(2, 'Supplier2', 'Supplier Street 2', '987654321'),
(3, 'Supplier3', 'Supplier Street 3', '555555555'),
(4, 'Supplier4', 'Supplier Street 4', '999888777'),
(5, 'Supplier5', 'Supplier Street 5', '111222333');

INSERT INTO INGREDIENT (INGREDIENT_ID, NAME, SUPPLIER_ID, PRICE) VALUES
(1, 'Ingredient1', 1, 10.50),
(2, 'Ingredient2', 2, 8.75),
(3, 'Ingredient3', 3, 15.20),
(4, 'Ingredient4', 4, 12.30),
(5, 'Ingredient5', 5, 9.80);

INSERT INTO PRODUCT (PRODUCT_ID, NAME, PRICE) VALUES
(1, 'Product1', 25.99),
(2, 'Product2', 18.50),
(3, 'Product3', 30.75),
(4, 'Product4', 22.40),
(5, 'Product5', 15.90);

INSERT INTO PRODUCT_INGREDIENT (PRODUCT_ID, INGREDIENT_ID) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3),
(4, 4);

INSERT INTO CLIENT (CLIENT_ID, NAME, EMAIL, ADDRESS, PHONE) VALUES
(1, 'Client1', 'client1@example.com', 'Client Street 1', '111111111'),
(2, 'Client2', 'client2@example.com', 'Client Street 2', '222222222'),
(3, 'Client3', 'client3@example.com', 'Client Street 3', '333333333'),
(4, 'Client4', 'client4@example.com', 'Client Street 4', '444444444'),
(5, 'Client5', 'client5@example.com', 'Client Street 5', '555555555');

INSERT INTO `ORDER` (ORDER_ID, ORDER_DATE, CLIENT_ID) VALUES
(1, '2024-03-01', 1),
(2, '2024-03-02', 2),
(3, '2024-03-03', 3),
(4, '2024-03-04', 4),
(5, '2024-03-05', 5);

INSERT INTO ORDER_ITEM (ORDER_ITEM_ID, ORDER_ID, PRODUCT_ID, QUANTITY) VALUES
(1, 1, 1, 2),
(2, 2, 3, 1),
(3, 3, 2, 3),
(4, 4, 4, 1),
(5, 5, 5, 2);

INSERT INTO EMPLOYEE (EMPLOYEE_ID, NAME, POSITION) VALUES
(1, 'Employee1', 'Pastry Chef'),
(2, 'Employee2', 'Delivery Driver'),
(3, 'Employee3', 'Assistant'),
(4, 'Employee4', 'Pastry Chef'),
(5, 'Employee5', 'Delivery Driver');

INSERT INTO EMPLOYEE_DETAIL (EMPLOYEE_ID, BIRTHDATE, SALARY) VALUES
(1, '1990-01-15', 2500.50),
(2, '1985-05-20', 2000.75),
(3, '1995-09-10', 1800.30),
(4, '1980-03-25', 2800.90),
(5, '1998-11-08', 2100.25);

INSERT INTO EMPLOYEE_PRODUCT (EMPLOYEE_ID, PRODUCT_ID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

INSERT INTO EMPLOYEE_ORDER (EMPLOYEE_ID, ORDER_ID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);