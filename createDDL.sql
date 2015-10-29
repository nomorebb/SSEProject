CREATE TABLE TOPIC (ID INTEGER AUTO_INCREMENT NOT NULL, CREATE_TIME DATETIME, DESCRIPTION VARCHAR(1000), NAME VARCHAR(500) NOT NULL, OUTSIDER VARCHAR(500), PASS_STATUS VARCHAR(45), TEACHER_COMMENT VARCHAR(1000), UPDATE_TIME DATETIME, TOPICTYPE_ID INTEGER NOT NULL, PRIMARY KEY (ID))
CREATE TABLE TOPIC_TYPE (ID INTEGER AUTO_INCREMENT NOT NULL, TYPE_NAME VARCHAR(45) NOT NULL, PRIMARY KEY (ID))
CREATE TABLE USER (ID INTEGER AUTO_INCREMENT NOT NULL, ROLE VARCHAR(31), ACCOUNT VARCHAR(45) NOT NULL, CREATE_TIME DATETIME, EMAIL VARCHAR(1000), GENDER VARCHAR(45), GPA VARCHAR(45), NAME VARCHAR(45) NOT NULL, PASSWORD VARCHAR(45) NOT NULL, PHONE VARCHAR(1000), SELF_DESCRIPTION VARCHAR(1000), UPDATE_TIME DATETIME, MATCH_TYPE VARCHAR(30), TEACHER INTEGER, CANDIDATE_TOPICS VARCHAR(1000), CAPACITY INTEGER, DEGREE VARCHAR(45), DIRECTION VARCHAR(500), TITLE VARCHAR(45), PRIMARY KEY (ID))
CREATE TABLE MENU (ID INTEGER AUTO_INCREMENT NOT NULL, NAME VARCHAR(45) NOT NULL, PARENTID INTEGER, ROLE VARCHAR(45) NOT NULL, URL VARCHAR(2000) NOT NULL, PRIMARY KEY (ID))
CREATE TABLE WILL (CREATE_TIME DATETIME, LEVEL INTEGER NOT NULL, STATUS VARCHAR(10) NOT NULL, UPDATE_TIME DATETIME, STUDENTID INTEGER NOT NULL, TEACHERID INTEGER NOT NULL, PRIMARY KEY (STUDENTID, TEACHERID))
CREATE TABLE TIME_NODE (ID INTEGER AUTO_INCREMENT NOT NULL, CREATE_TIME DATETIME, DESCRIPTION VARCHAR(500) NOT NULL, NAME VARCHAR(45) NOT NULL, TIME DATETIME NOT NULL, UPDATE_TIME DATETIME, PRIMARY KEY (ID))
CREATE TABLE DOCUMENT_COMMENT (ID INTEGER AUTO_INCREMENT NOT NULL, CONTENT VARCHAR(2000), CREATE_TIME DATETIME, RATING VARCHAR(10) NOT NULL, TITLE VARCHAR(100), UPDATE_TIME DATETIME, PARENT_DOCUMENT INTEGER NOT NULL, USER INTEGER NOT NULL, PRIMARY KEY (ID))
CREATE TABLE ATTACHMENT (ID INTEGER AUTO_INCREMENT NOT NULL, CREATE_TIME DATETIME, FINALVERSION TINYINT(1) default 0 NOT NULL, LISTNAME VARCHAR(500) NOT NULL, REALNAME VARCHAR(500) NOT NULL, SIZE VARCHAR(100) NOT NULL, STATUS VARCHAR(10) NOT NULL, UPDATE_TIME DATETIME, URL VARCHAR(1500) NOT NULL, CREATOR INTEGER NOT NULL, DOCUMENT INTEGER, PRIMARY KEY (ID))
CREATE TABLE DOCUMENT (ID INTEGER AUTO_INCREMENT NOT NULL, CONTENT VARCHAR(5000) NOT NULL, CREATE_TIME DATETIME, DOCUMENTTYPE VARCHAR(20) NOT NULL, NAME VARCHAR(45) NOT NULL, UPDATE_TIME DATETIME, CREATOR INTEGER NOT NULL, LAST_MODIFIED_BY INTEGER, PRIMARY KEY (ID))
ALTER TABLE TOPIC ADD CONSTRAINT FK_TOPIC_TOPICTYPE_ID FOREIGN KEY (TOPICTYPE_ID) REFERENCES TOPIC_TYPE (ID)
ALTER TABLE USER ADD CONSTRAINT FK_USER_TEACHER FOREIGN KEY (TEACHER) REFERENCES USER (ID)
ALTER TABLE DOCUMENT_COMMENT ADD CONSTRAINT FK_DOCUMENT_COMMENT_PARENT_DOCUMENT FOREIGN KEY (PARENT_DOCUMENT) REFERENCES DOCUMENT (ID)
ALTER TABLE DOCUMENT_COMMENT ADD CONSTRAINT FK_DOCUMENT_COMMENT_USER FOREIGN KEY (USER) REFERENCES USER (ID)
ALTER TABLE ATTACHMENT ADD CONSTRAINT FK_ATTACHMENT_CREATOR FOREIGN KEY (CREATOR) REFERENCES USER (ID)
ALTER TABLE ATTACHMENT ADD CONSTRAINT FK_ATTACHMENT_DOCUMENT FOREIGN KEY (DOCUMENT) REFERENCES DOCUMENT (ID)
ALTER TABLE DOCUMENT ADD CONSTRAINT FK_DOCUMENT_CREATOR FOREIGN KEY (CREATOR) REFERENCES USER (ID)
ALTER TABLE DOCUMENT ADD CONSTRAINT FK_DOCUMENT_LAST_MODIFIED_BY FOREIGN KEY (LAST_MODIFIED_BY) REFERENCES USER (ID)