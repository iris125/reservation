CREATE DATABASE project;
CREATE TABLE administrator(
  admin_id char(7) not null PRIMARY KEY,
  admin_name text,
  admin_password text);
INSERT INTO administrator values('a000001','admin1','100001');
INSERT INTO administrator values('a000002','admin1','100002');

CREATE TABLE teacher(
  id char(7) not null PRIMARY KEY,
  name text,
  password text,
  introduction text DEFAULT NULL,
  address text DEFAULT NULL,
  maxunllnum int DEFAULT 10000,
  maxnumOfMonth int DEFAULT 100,
  maxtimeOfMonth int DEFAULT 10000);
  
CREATE TABLE student(
  id char(7) not null PRIMARYKEY,
  name text,
  password text DEFAULT NULL,
  introduction text DEFAULT NULL);
  
 CREATE TABLE reservation(
   r_id int auto-INCREMENT PRIMARY KEY,
   teacher text(7),
   student text(7) DEFAULT NULL,
   r_date date,
   r_year int,
   r_month int,
   beginTime text,
   finishTime text,
   length int,
   building text,
   room text,
   distance int,
   vaild_stu text,
   isvaild boolean DEFAULT true,
   wellfinished boolean DEFAULT true);
  
CREATE TABLE setting(
   id int auto-INCREMENT PRIMARY KEY,
   maxVaildNum int,
   minRegretDate int);
