alter table qvtable
    ADD CONSTRAINT FK_vtable FOREIGN KEY (qid)
REFERENCES questionbank(qid);

alter table questionbank
    ADD CONSTRAINT FK_author FOREIGN KEY (aid)
REFERENCES authors(aid);