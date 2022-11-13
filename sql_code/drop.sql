alter table qvtable
    DROP FOREIGN KEY FK_vtable;
alter table questionbank
    DROP FOREIGN KEY FK_author;

drop table questionbank;
drop table authors;
drop table mcq;
drop table subjective;
drop table staticvariables;

drop database daoproject;