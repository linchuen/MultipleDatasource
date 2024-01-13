CREATE TABLE `simple_wallet` (
  `user_id` bigint(20) NOT NULL,
  `amount` decimal(16,4) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- default data
INSERT IGNORE INTO main.simple_wallet(user_id, amount, updated_time)
VALUES(1, 100.0000, NOW()),
(2, 100.0000, NOW()),
(3, 100.0000, NOW()),
(4, 100.0000, NOW()),
(5, 100.0000, NOW()),
(6, 100.0000, NOW());
