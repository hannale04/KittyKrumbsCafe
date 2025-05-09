DROP TABLE menu_items;
CREATE TABLE if not exists menu_items (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL UNIQUE,
    category ENUM('Food', 'Drink', 'Special') NOT NULL,
    price INT NOT NULL
);

INSERT INTO menu_items (name, category, price)
VALUES

('Chocolate Chip Cookie', 'Food', 2),
('Sugar Cookie', 'Food', 2),
('Snickerdoodle Cookie', 'Food', 2),
('Oatmeal Raisin Cookie', 'Food', 2),
('Peanut Butter Cookie', 'Food', 2),
('Butter Cookie', 'Food', 2),
('Iced Shortbread Cookie', 'Food', 3),
('Red Velvet Cupcake', 'Food', 3),
('Iced Lemon Loaf', 'Food', 3),
('Grilled Cheese Sandwich', 'Food', 5),
('Mac and Cheese', 'Food', 4),
('Croissant', 'Food', 4),
('Blueberry Muffin', 'Food', 4),
('Strawberry Shortcake', 'Food', 4),
('Fruit Parfait', 'Food', 5),

('Hot Tea', 'Drink', 3),
('Iced Tea', 'Drink', 3),
('Hot Coffee', 'Drink', 3),
('Iced Coffee', 'Drink', 3),
('Hot Chocolate', 'Drink', 4),
('Frozen Hot Chocolate', 'Drink', 5),
('Hot Chai Latte', 'Drink', 5),
('Iced Chai Latte', 'Drink', 5),
('Hot Vanilla Latte', 'Drink', 5),
('Iced Vanilla Latte', 'Drink', 5),
('Hot Caramel Macchiato', 'Drink', 5),
('Iced Caramel Macchiato', 'Drink', 5),
('Hot Matcha Latte', 'Drink', 5),
('Iced Matcha Latte', 'Drink', 5),
('Banana Smoothie', 'Drink', 5),

('Salted Caramel Macaron', 'Special', 2),
('Cheese Danish', 'Special', 3),
('Banana Nut Bread', 'Special', 3),
('Pumpkin Spice Roll', 'Special', 3),
('Peanut Butter Brownie', 'Special', 3),
('Catnip Chocolate Fudge ', 'Special', 3),
('Mocha Mousse', 'Special', 3),
('Coconut Cream Pie', 'Special', 4),
('Cinnamon Paw Roll', 'Special', 4),
('Peach Cobbler', 'Special', 4),
('Tiramisu Cup', 'Special', 4),
('Raspberry Cheesecake', 'Special', 5),
('Tabby’s Toffee Cake', 'Special', 5),
('Apple Spice Scone', 'Special', 5),
('Calico Crème Brûlée', 'Special', 6);



