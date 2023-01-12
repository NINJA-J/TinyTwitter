CREATE DATABASE  IF NOT EXISTS `tiny_twitter` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tiny_twitter`;
-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: localhost    Database: tiny_twitter
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `blog_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `content` text NOT NULL,
  `author` int DEFAULT NULL,
  `likes` int DEFAULT '0',
  `collects` int DEFAULT '0',
  PRIMARY KEY (`blog_id`),
  KEY `blog_user_user_id_fk` (`author`),
  CONSTRAINT `blog_user_user_id_fk` FOREIGN KEY (`author`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (8,'My favorite book','2022-12-15','2022-12-15','It\'s Harry Potter series.',6,0,0),(9,'Happy Winter Break!','2022-12-15','2022-12-15','The holiday is coming.',6,0,0),(10,'Do you know react?','2022-12-15','2022-12-15','It\'s a popular front end framework.',6,0,0),(11,'How about the weather in Boston?','2022-12-15','2022-12-15','It\'s sunny.',7,0,0),(12,'I hate pickle','2022-12-15','2022-12-15','I say no pickle everytime when I place order at Popeyes.',8,0,0),(13,'There are many filters in Zoom','2022-12-15','2022-12-15','They are cure',8,0,0);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_type`
--

DROP TABLE IF EXISTS `blog_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_type` (
  `blog_id` int NOT NULL,
  `type_id` int NOT NULL,
  PRIMARY KEY (`blog_id`,`type_id`),
  KEY `blog_type_blog_blog_id_fk` (`blog_id`),
  KEY `blog_type_type_type_id_fk` (`type_id`),
  CONSTRAINT `blog_type_blog_blog_id_fk` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`blog_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blog_type_type_type_id_fk` FOREIGN KEY (`type_id`) REFERENCES `type` (`type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_type`
--

LOCK TABLES `blog_type` WRITE;
/*!40000 ALTER TABLE `blog_type` DISABLE KEYS */;
INSERT INTO `blog_type` VALUES (8,1),(9,1),(10,3),(11,1),(12,1),(13,2);
/*!40000 ALTER TABLE `blog_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collection`
--

DROP TABLE IF EXISTS `collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collection` (
  `user_id` int NOT NULL,
  `blog_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`blog_id`),
  KEY `collection_blog_blog_id_fk` (`blog_id`),
  KEY `collection_user_user_id_fk` (`user_id`),
  CONSTRAINT `collection_blog_blog_id_fk` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`blog_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `collection_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection`
--

LOCK TABLES `collection` WRITE;
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
INSERT INTO `collection` VALUES (7,8),(7,10),(8,11);
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `update_date` date NOT NULL,
  `content` text NOT NULL,
  `blog_id` int DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `author` int NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comment_blog_blog_id_fk` (`blog_id`),
  KEY `comment_user_user_id_fk` (`author`),
  CONSTRAINT `comment_blog_blog_id_fk` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`blog_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_user_user_id_fk` FOREIGN KEY (`author`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (8,'2022-12-15','I like react too.',10,'2022-12-15',7),(9,'2022-12-15','What\'s the weather in Seattle?',11,'2022-12-15',7),(10,'2022-12-15','I recommend Harry Potter to all my friends.',8,'2022-12-15',7),(11,'2022-12-15','Seattle is rainy now.',11,'2022-12-15',8);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like` (
  `blog_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`blog_id`,`user_id`),
  KEY `like_blog_blog_id_fk` (`blog_id`),
  KEY `like_user_user_id_fk` (`user_id`),
  CONSTRAINT `like_blog_blog_id_fk` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`blog_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `like_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like`
--

LOCK TABLES `like` WRITE;
/*!40000 ALTER TABLE `like` DISABLE KEYS */;
INSERT INTO `like` VALUES (8,7),(10,7),(11,8);
/*!40000 ALTER TABLE `like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'life','about your life'),(2,'work','share your work experience'),(3,'tech stack','what tech stack do you like?');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'yan','yan','yan'),(4,'ma','ma','ma'),(5,'ad','ad','ad'),(6,'Flora','1','1'),(7,'Mark','2','2'),(8,'Lee','3','3');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'tiny_twitter'
--
/*!50003 DROP PROCEDURE IF EXISTS `allBlogs` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `allBlogs`()
begin
    with all_like_cnt as (
        select blog_id, count(user_id) likes from `like` group by blog_id
    ), all_collection_cnt as (
        select blog_id, count(user_id) collects from collection group by blog_id
    )
    select blog.blog_id, title, create_date, update_date, content,
           ifnull(lc.likes, 0) likes , ifnull(cc.collects, 0) collects,
        user_id user_user_id, name user_name from blog
        left join user on blog.author = user.user_id
        left join all_like_cnt lc on lc.blog_id = blog.blog_id
        left join all_collection_cnt cc on cc.blog_id = blog.blog_id;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `blogStatistics` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `blogStatistics`()
begin
    with like_stat as (
        select blog_id, count(user_id)  likes from `like` group by blog_id
    ), collect_stat as (
        select blog_id, count(user_id) collects from collection group by blog_id
    ), comment_cnt as (
        select blog_id, count(comment_id) cmts from comment group by blog_id
    )
    select title, user.name author, DATE_FORMAT(update_date,'%Y-%m-%d') update_date,
           ifnull(like_stat.likes, 0) likes, ifnull(collect_stat.collects, 0) collects,
           ifnull(cmts, 0) comments  from blog
        left join user on blog.author = user.user_id
        left join like_stat on blog.blog_id = like_stat.blog_id
        left join collect_stat on blog.blog_id = collect_stat.blog_id
        left join comment_cnt on blog.blog_id = comment_cnt.blog_id
        order by update_date desc;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `userStatistics` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `userStatistics`()
begin
    with blog_stat as (
        select author, count(blog_id) blogs, avg(length(content)) avg_blog_len from blog group by author
    ), comment_stat as (
        select author, count(comment_id) comments, avg(length(content)) avg_comment_len from comment group by author
    )
    select name, ifnull(blogs, 0) blogs, ifnull(avg_blog_len, 0) avg_blog_len,
           ifnull(comments, 0) comments, ifnull(avg_comment_len, 0) avg_cmt_len from user
        left join blog_stat b on user.user_id = b.author
        left join comment_stat c on user_id = c.author
        order by blogs;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-15 18:11:12
