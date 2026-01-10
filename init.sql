CREATE DATABASE IF NOT EXISTS `SpringCommerce`;

USE `SpringCommerce`;

CREATE TABLE IF NOT EXISTS categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS `sizes` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    plant_size VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS `characteristics` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    characteristic VARCHAR(30) NOT NULL
);

INSERT INTO categories (category) VALUES
('Outdoor plants'),
('Indoor plants'),
('Potted'),
('Others');

INSERT INTO `sizes` (plant_size) VALUES
('Mini'),
('Medium'),
('Large');

INSERT INTO characteristics (characteristic) VALUES
('Easy to care for'),
('Drought tolerant'),
('Requires direct light'),
('Others');

CREATE TABLE IF NOT EXISTS plants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    image VARCHAR(40),
    plant_name VARCHAR(50) NOT NULL,
    price INT NOT NULL,
    description VARCHAR(500),
    category VARCHAR(20),
    plant_size VARCHAR(10),
    characteristic VARCHAR(30)
);

INSERT INTO plants (image, plant_name, price, description, category, plant_size, characteristic) VALUES
('cactus.png', 'Cactus Plant 1', 100000, 'A low-maintenance plant with thick, fleshy stems that store water for long periods. It adds a bold and exotic look to your home, thriving in bright light with minimal attention.', 'Potted', 'Medium', 'Requires direct light'),
('cacti.png', 'Cacti Plant 1', 100000, 'A low-maintenance plant with thick, fleshy stems that store water for long periods. It adds a bold and exotic look to your home, thriving in bright light with minimal attention.', 'Potted', 'Medium', 'Requires direct light'),
('aloe_vera.png', 'Aloe Vera Plant 1', 125000, 'A medicinal plant known for its healing gel used for skin treatment and burns. Beyond its soothing properties, it also serves as a stylish addition to kitchens and sunny windows.', 'Indoor plants', 'Medium', 'Easy to care for'),
('succulent_cactus.png', 'Succulent Cactus 1', 59000, 'A popular tabletop plant with symmetrical rosettes and vibrant green leaves. This plant stores water in its thick leaves and thrives in small pots, making it great for gifts.', 'Potted', 'Mini', 'Drought tolerant'),
('green.png', 'Green Plant 1', 350000, 'A lush green indoor plant that purifies air and brings a calming natural vibe. It requires little maintenance and adapts well to various indoor conditions, improving overall ambiance.', 'Indoor plants', 'Medium', 'Easy to care for'),
('succulent.png', 'Succulent Plant 1', 230000, 'Requires bright light and occasional watering, great for apartments. Its leaf structure is engineered for survival and is also visually pleasing in modern spaces.', 'Outdoor plants', 'Mini', 'Requires direct light'),
('cactus.png', 'Cactus Plant 2', 100000, 'A low-maintenance plant with thick, fleshy stems that store water for long periods. It adds a bold and exotic look to your home, thriving in bright light with minimal attention.', 'Potted', 'Medium', 'Requires direct light'),
('cacti.png', 'Cacti Plant 2', 100000, 'A low-maintenance plant with thick, fleshy stems that store water for long periods. It adds a bold and exotic look to your home, thriving in bright light with minimal attention.', 'Potted', 'Medium', 'Requires direct light'),
('aloe_vera.png', 'Aloe Vera Plant 2', 125000, 'A medicinal plant known for its healing gel used for skin treatment and burns. Beyond its soothing properties, it also serves as a stylish addition to kitchens and sunny windows.', 'Indoor plants', 'Medium', 'Easy to care for'),
('succulent_cactus.png', 'Succulent Cactus 2', 59000, 'A popular tabletop plant with symmetrical rosettes and vibrant green leaves. This plant stores water in its thick leaves and thrives in small pots, making it great for gifts.', 'Potted', 'Mini', 'Drought tolerant'),
('green.png', 'Green Plant 2', 350000, 'A lush green indoor plant that purifies air and brings a calming natural vibe. It requires little maintenance and adapts well to various indoor conditions, improving overall ambiance.', 'Indoor plants', 'Medium', 'Easy to care for'),
('cactus.png', 'Cactus Plant 3', 100000, 'A low-maintenance plant with thick, fleshy stems that store water for long periods. It adds a bold and exotic look to your home, thriving in bright light with minimal attention.', 'Potted', 'Medium', 'Requires direct light'),

('cacti.png', 'Cacti Plant 3', 100000, 'A low-maintenance plant with thick, fleshy stems that store water for long periods. It adds a bold and exotic look to your home, thriving in bright light with minimal attention.', 'Potted', 'Medium', 'Requires direct light'),
('aloe_vera.png', 'Aloe Vera Plant 3', 125000, 'A medicinal plant known for its healing gel used for skin treatment and burns. Beyond its soothing properties, it also serves as a stylish addition to kitchens and sunny windows.', 'Indoor plants', 'Medium', 'Easy to care for'),
('succulent_cactus.png', 'Succulent Plant 2', 59000, 'A popular tabletop plant with symmetrical rosettes and vibrant green leaves. This plant stores water in its thick leaves and thrives in small pots, making it great for gifts.', 'Potted', 'Mini', 'Drought tolerant'),
('green.png', 'Green Plant 3', 350000, 'A lush green indoor plant that purifies air and brings a calming natural vibe. It requires little maintenance and adapts well to various indoor conditions, improving overall ambiance.', 'Indoor plants', 'Medium', 'Easy to care for'),
('succulent.png', 'Succulent Plant 3', 230000, 'Requires bright light and occasional watering, great for apartments. Its leaf structure is engineered for survival and is also visually pleasing in modern spaces.', 'Outdoor plants', 'Mini', 'Requires direct light'),
('succulent_cactus.png', 'Succulent Cactus 3', 59000, 'A popular tabletop plant with symmetrical rosettes and vibrant green leaves. This plant stores water in its thick leaves and thrives in small pots, making it great for gifts.', 'Potted', 'Mini', 'Drought tolerant'),
('green.png', 'Green Plant 4', 350000, 'A lush green indoor plant that purifies air and brings a calming natural vibe. It requires little maintenance and adapts well to various indoor conditions, improving overall ambiance.', 'Indoor plants', 'Medium', 'Easy to care for'),
('cactus.png', 'Cactus Plant 4', 100000, 'A low-maintenance plant with thick, fleshy stems that store water for long periods. It adds a bold and exotic look to your home, thriving in bright light with minimal attention.', 'Potted', 'Medium', 'Requires direct light'),
('cacti.png', 'Cacti Plant 4', 100000, 'A low-maintenance plant with thick, fleshy stems that store water for long periods. It adds a bold and exotic look to your home, thriving in bright light with minimal attention.', 'Potted', 'Medium', 'Requires direct light'),
('aloe_vera.png', 'Aloe Vera Plant 4', 125000, 'A medicinal plant known for its healing gel used for skin treatment and burns. Beyond its soothing properties, it also serves as a stylish addition to kitchens and sunny windows.', 'Indoor plants', 'Medium', 'Easy to care for'),
('succulent_cactus.png', 'Succulent Cactus 4', 59000, 'A popular tabletop plant with symmetrical rosettes and vibrant green leaves. This plant stores water in its thick leaves and thrives in small pots, making it great for gifts.', 'Potted', 'Mini', 'Drought tolerant'),
('green.png', 'Green Plant 5', 350000, 'A lush green indoor plant that purifies air and brings a calming natural vibe. It requires little maintenance and adapts well to various indoor conditions, improving overall ambiance.', 'Indoor plants', 'Medium', 'Easy to care for'),

('succulent_cactus.png', 'Succulent Plant 4', 230000, 'Requires bright light and occasional watering, great for apartments. Its leaf structure is engineered for survival and is also visually pleasing in modern spaces.', 'Outdoor plants', 'Mini', 'Requires direct light'),
('cacti.png', 'Cacti Plant 5', 100000, 'A low-maintenance plant with thick, fleshy stems that store water for long periods. It adds a bold and exotic look to your home, thriving in bright light with minimal attention.', 'Potted', 'Medium', 'Requires direct light'),
('green.png', 'Green Plant 6', 350000, 'A lush green indoor plant that purifies air and brings a calming natural vibe. It requires little maintenance and adapts well to various indoor conditions, improving overall ambiance.', 'Indoor plants', 'Medium', 'Easy to care for');
