
DROP TABLE IF EXISTS `add_to_cart`;

CREATE TABLE `add_to_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `added_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_pk_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `add_to_cart` WRITE;
/*!40000 ALTER TABLE `add_to_cart` DISABLE KEYS */;

INSERT INTO `add_to_cart` (`id`, `product_id`, `qty`, `price`, `added_date`, `user_id`)
VALUES
	(1,2,1,150,NULL,2),
	(2,2,1,150,NULL,2);

/*!40000 ALTER TABLE `add_to_cart` ENABLE KEYS */;
UNLOCK TABLES;