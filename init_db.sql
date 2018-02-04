CREATE TABLE IF NOT EXISTS `User` (
  `UserName` varchar(16) NOT NULL,
  `Password` char(64) NOT NULL,
  `Email` varchar(64) NOT NULL,
  `PushTime` time NOT NULL,
  PRIMARY KEY (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `Url` (
  `UrlID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(16) NOT NULL,
  `Title` varchar(100) NOT NULL,
  `Url` text NOT NULL,
  `Enabled` tinyint(1) NOT NULL,
  `RealTimePush` tinyint(1) NOT NULL,
  PRIMARY KEY (`UrlID`),
  FOREIGN KEY (`UserName`) REFERENCES `User` (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `Content` (
  `ContentID` int(11) NOT NULL AUTO_INCREMENT,
  `UrlID` int(11) NOT NULL,
  `Html` text NOT NULL,
  `Delta` text NOT NULL,
  PRIMARY KEY (`ContentID`),
  FOREIGN KEY (`UrlID`) REFERENCES `Url` (`UrlID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
