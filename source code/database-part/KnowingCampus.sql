-- phpMyAdmin SQL Dump
-- version 4.3.6
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 26, 2015 at 12:35 AM
-- Server version: 5.6.21
-- PHP Version: 5.5.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `KnowingCampus`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `isbn` varchar(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `author` varchar(50) NOT NULL,
  `pressname` varchar(30) NOT NULL,
  `publictime` varchar(20) NOT NULL,
  `description` text,
  `isborrowed` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `bookborrowedrecord`
--

CREATE TABLE IF NOT EXISTS `bookborrowedrecord` (
  `id` int(10) unsigned NOT NULL,
  `borrowtime` varchar(20) NOT NULL,
  `returntime` varchar(20) NOT NULL,
  `userid` varchar(15) DEFAULT NULL,
  `bookisbn` varchar(20) DEFAULT NULL
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(10) unsigned NOT NULL,
  `created_at` varchar(20) NOT NULL,
  `content` text NOT NULL,
  `uid` varchar(15) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `created_at`, `content`, `uid`, `sid`) VALUES
(16, '2015-03-23 11:51', 'Fighting!', '10002', 10),
(17, '2015-03-23 11:52', 'good for you!', '10003', 10),
(18, '2015-03-25 15:31', 'I think so!', '10004', 18);

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE IF NOT EXISTS `news` (
  `id` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `createtime` varchar(30) NOT NULL,
  `picdir` varchar(30) NOT NULL,
  `content` text NOT NULL,
  `ncid` int(11) DEFAULT NULL
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `newscategory`
--

CREATE TABLE IF NOT EXISTS `newscategory` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `secondhandgoods`
--

CREATE TABLE IF NOT EXISTS `secondhandgoods` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(20) NOT NULL,
  `description` varchar(100) NOT NULL,
  `publictime` varchar(20) NOT NULL,
  `price` varchar(20) NOT NULL,
  `issold` int(11) DEFAULT NULL,
  `categoryid` int(11) DEFAULT NULL,
  `userid` varchar(15) DEFAULT NULL
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `secondhandgoodscategory`
--

CREATE TABLE IF NOT EXISTS `secondhandgoodscategory` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `id` int(10) unsigned NOT NULL,
  `created_at` varchar(20) NOT NULL,
  `content` text NOT NULL,
  `uid` varchar(15) DEFAULT NULL,
  `scid` int(11) DEFAULT NULL
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`id`, `created_at`, `content`, `uid`, `scid`) VALUES
(12, '2015-03-23 11:53', 'I want eat pizza!', '10004', 3),
(11, '2015-03-23 11:48', 'I Love Skiing!', '10001', 4),
(10, '2015-03-23 11:43', 'Change the world by coding!', '10001', 2),
(13, '2015-03-23 11:55', 'Big Big Big Snow.', '10005', 2),
(14, '2015-03-23 14:51', 'Life is beautiful', '10007', 3),
(16, '2015-03-23 15:08', 'Be Happy!', '10009', 1),
(17, '2015-03-24 15:52', 'pick nose, pick nose, pick nose!!!!', '10003', 1),
(18, '2015-03-25 15:30', 'Page is a good teacher!', '10001', 2);

-- --------------------------------------------------------

--
-- Table structure for table `statuscategory`
--

CREATE TABLE IF NOT EXISTS `statuscategory` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(15) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `profile_image_url` varchar(30) DEFAULT NULL,
  `major` varchar(20) NOT NULL,
  `enter_year` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `password`, `gender`, `email`, `phone`, `description`, `profile_image_url`, `major`, `enter_year`) VALUES
('10001', 'dustin', '123456', 'M', 'DustinZeng@gmail.com', '2036402372', 'Change the Wrold by coding.', '/userpic/1.jpg', 'Computer Science', '2014'),
('10002', 'lizzie', '123456', 'F', 'lizzie23232@gmail.com', '2031218635', 'Life is beautiful', '/userpic/2.jpg', 'MBA', '2014'),
('10003', 'yueyang qin', '123456', 'M', 'yueyang@unh.newhaven.edu', '2035031539', 'I Love ACG', '/userpic/3.jpg', 'Computer Science', '2014'),
('10004', 'hurui', '123456', 'M', 'ruihu@unh.newhaven.edu', '2034308962', 'Cooler Coder', '/userpic/4.jpg', 'Computer Science', '2014'),
('10005', 'ted', '123456', 'M', 'ted@unh.newhaven.edu', '2035037506', 'haha', '/userpic/5.jpg', 'Computer Science', '2014'),
('10006', 'james', '123456', 'M', 'james@unh.newhaven.edu', '2031707428', 'Pain past is pleasure', '/userpic/6.jpg', 'Computer Science', '2014'),
('10007', 'lauren', '123456', 'F', 'lauren@unh.newhaven.edu', '2035201065', 'One today is worth two tomorrows.', '/userpic/7.jpg', 'Psychology', '2014'),
('10008', 'Leda', '123456', 'F', 'Leda@unh.newhaven.edu', '2031089163', 'You have to believe in yourself. That''s the secret of success.', '/userpic/8.jpg', 'MBA', '2014'),
('10009', 'Kevin', '123456', 'M', 'Kevin@unh.newhaven.edu', '2037451280', 'Cease to struggle and you cease to live.', '/userpic/9.jpg', 'Network System', '2014'),
('10010', 'Ayers', '123456', 'F', 'Ayers@unh.newhaven.edu', '2035670917', 'Nothing is impossible to a willing heart.', '/userpic/10.jpg', 'Art', '2014');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`isbn`);

--
-- Indexes for table `bookborrowedrecord`
--
ALTER TABLE `bookborrowedrecord`
  ADD PRIMARY KEY (`id`), ADD KEY `userid` (`userid`), ADD KEY `bookisbn` (`bookisbn`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`), ADD KEY `uid` (`uid`), ADD KEY `sid` (`sid`);

--
-- Indexes for table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`id`), ADD KEY `ncid` (`ncid`);

--
-- Indexes for table `newscategory`
--
ALTER TABLE `newscategory`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `secondhandgoods`
--
ALTER TABLE `secondhandgoods`
  ADD PRIMARY KEY (`id`), ADD KEY `categoryid` (`categoryid`), ADD KEY `userid` (`userid`);

--
-- Indexes for table `secondhandgoodscategory`
--
ALTER TABLE `secondhandgoodscategory`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`), ADD KEY `uid` (`uid`), ADD KEY `scid` (`scid`);

--
-- Indexes for table `statuscategory`
--
ALTER TABLE `statuscategory`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bookborrowedrecord`
--
ALTER TABLE `bookborrowedrecord`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `news`
--
ALTER TABLE `news`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT for table `secondhandgoods`
--
ALTER TABLE `secondhandgoods`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
