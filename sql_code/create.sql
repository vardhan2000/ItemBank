create database daoproject;
use daoproject;

GRANT ALL PRIVILEGES ON daoproject.* TO 'root'@'localhost';

create table questionbank
(
    qid VARCHAR(100) NOT NULL,
    qdomain VARCHAR(100) NOT NULL,
    aid VARCHAR(100) NOT NULL,
    qstatus VARCHAR(100) NOT NULL,
    CONSTRAINT PK_item PRIMARY KEY (qid)
);

create table qvtable
(
    qid VARCHAR(100) NOT NULL,
    qversion VARCHAR(100) NOT NULL,
    qtype int NOT NULL, -- 1 for MCQ and 2 for Subjective
    CONSTRAINT PK_item PRIMARY KEY (qid,qversion)
);

create table staticvariables
(
    var_class VARCHAR(100) NOT NULL, -- 'Question'
    var_var VARCHAR(100) NOT NULL, -- 'IDCount'
    var_value int NOT NULL, -- num of items
    CONSTRAINT PK_item PRIMARY KEY (var_class,var_var)
);

create table authors
(
    aid VARCHAR(255) NOT NULL,
    ausername VARCHAR(255) NOT NULL,
    apassword VARCHAR(255) NOT NULL,
    aemail VARCHAR(255),
    CONSTRAINT PK_author PRIMARY KEY (aid,aemail)
);

create table mcq
(
    qid VARCHAR(100) NOT NULL,
    qversion VARCHAR(100) NOT NULL,
    mcq_choice VARCHAR(100) NOT NULL,
    mcq_option VARCHAR(100) NOT NULL,
    is_correct VARCHAR(100) NOT NULL,
    question_text VARCHAR(100) NOT NULL,
    CONSTRAINT PK_item PRIMARY KEY (qid,qversion,mcq_choice)
);

create table subjective
(
    qid VARCHAR(100) NOT NULL,
    qversion VARCHAR(100) NOT NULL,
    subjective_answer_points VARCHAR(10000) NOT NULL,
    question_text VARCHAR(100) NOT NULL,
    CONSTRAINT PK_item PRIMARY KEY (qid,qversion)
);