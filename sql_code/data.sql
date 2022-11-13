insert into authors(aid,ausername,apassword,aemail)
VALUES('2','architsangal','test','archit.sangal@iiitb.ac.in');

insert into authors(aid,ausername,apassword,aemail)
VALUES('1','vardhan','test123','vardhan@iiitb.ac.in');

insert into authors(aid,ausername,apassword,aemail)
VALUES('3','divyam','test@123','divyam@iiitb.ac.in');

insert into authors(aid,ausername,apassword,aemail)
VALUES('4','gagan','test#123','gagan@iiitb.ac.in');



-- insert questionbank starts
insert into questionbank(qid,qdomain,aid,qstatus)
VALUES('1','Phy','2','ACTIVE');

insert into questionbank(qid,qdomain,aid,qstatus)
VALUES('2','OOP','4','ACTIVE');
-- insert questionbank ends


-- insert qvtable starts
insert into qvtable(qid,qversion,qtype)
VALUES('1','1', 1);

insert into qvtable(qid,qversion,qtype)
VALUES('1','2', 1);

insert into qvtable(qid,qversion,qtype)
VALUES('1','3', 1);

insert into qvtable(qid,qversion,qtype)
VALUES('2','1', 2);
-- insert qvtable ends


-- insert mcq starts

-- version 1
insert into mcq(qid,qversion,mcq_choice,mcq_option,is_correct,question_text)
VALUES('1','1','1','1 x 1e8','NO','What is the speed of light?');

insert into mcq(qid,qversion,mcq_choice,mcq_option,is_correct,question_text)
VALUES('1','1','2','5 x 1e8','NO','What is the speed of light?');

insert into mcq(qid,qversion,mcq_choice,mcq_option,is_correct,question_text)
VALUES('1','1','3','3 x 1e8','YES','What is the speed of light?');

insert into mcq(qid,qversion,mcq_choice,mcq_option,is_correct,question_text)
VALUES('1','1','4','4 x 1e8','NO','What is the speed of light?');


-- version 2
insert into mcq(qid,qversion,mcq_choice,mcq_option,is_correct,question_text)
VALUES('1','2','3','1 x 1e8','NO','What is the speed of light in m/s?');

insert into mcq(qid,qversion,mcq_choice,mcq_option,is_correct,question_text)
VALUES('1','2','2','5 x 1e8','NO','What is the speed of light in m/s?');

insert into mcq(qid,qversion,mcq_choice,mcq_option,is_correct,question_text)
VALUES('1','2','1','3 x 1e8','YES','What is the speed of light in m/s?');

insert into mcq(qid,qversion,mcq_choice,mcq_option,is_correct,question_text)
VALUES('1','2','4','7 x 1e8','NO','What is the speed of light in m/s?');


-- version 3
insert into mcq(qid,qversion,mcq_choice,mcq_option,is_correct,question_text)
VALUES('1','3','3','1 x 1e8','NO','What is the speed of light in m/s in vaccum?');

insert into mcq(qid,qversion,mcq_choice,mcq_option,is_correct,question_text)
VALUES('1','3','1','5 x 1e8','NO','What is the speed of light in m/s in vaccum?');

insert into mcq(qid,qversion,mcq_choice,mcq_option,is_correct,question_text)
VALUES('1','3','2','3 x 1e8','YES','What is the speed of light in m/s in vaccum?');

insert into mcq(qid,qversion,mcq_choice,mcq_option,is_correct,question_text)
VALUES('1','3','4','7 x 1e8','NO','What is the speed of light in m/s in vaccum?');
-- insert mcq ends


-- insert Subjective starts
insert into subjective(qid,qversion,subjective_answer_points,question_text)
VALUES('2','1','Abstraction, Encapsulation, Inheritance, Polymorphism','Explain the 4 pillars of OOPs?');
-- insert Subjective ends


-- insert staticvariables starts
insert into staticvariables(var_class,var_var,var_value)
VALUES('Question','IDCount',2);

insert into staticvariables(var_class,var_var,var_value)
VALUES('Author','IDCount',4);
-- insert staticvariables ends