DROP TABLE IF EXISTS ARTICLES cascade ;
DROP TABLE IF EXISTS USERS cascade;
DROP TABLE IF EXISTS AUTHORITIES cascade;
DROP SEQUENCE IF EXISTS HIBERNATE_SEQUENCE;

CREATE TABLE ARTICLES (
ARTICLE_ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
TITLE NVARCHAR2(128) NOT NULL,
CONTENT NVARCHAR2(2000) NOT NULL,
AUTHOR NVARCHAR2(128) NOT NULL,
IS_PUBLIC BOOLEAN NOT NULL,
DATE_CREATED DATETIME2 NOT NULL,
DATE_MODIFIED DATETIME2 NOT NULL);

CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1 NO CACHE NO CYCLE ;

CREATE TABLE USERS (
    USERNAME NVARCHAR2(128) PRIMARY KEY,
    PASSWORD NVARCHAR2(128) NOT NULL,
    ENABLED CHAR(1)  NOT NULL DEFAULT 1
);

CREATE TABLE AUTHORITIES (
    USERNAME NVARCHAR2(128) NOT NULL,
    AUTHORITY NVARCHAR2(128) NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username)
);
ALTER TABLE AUTHORITIES ADD CONSTRAINT AUTHORITIES_UNIQUE UNIQUE (USERNAME, AUTHORITY);

