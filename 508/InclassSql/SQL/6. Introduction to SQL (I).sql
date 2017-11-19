create table instructor (ID char(5), name varchar(20), dept_name varchar(20), salary number(8,2));

create table instructor (ID char(5), name varchar(20) not null, dept_name varchar(20), salary number(8,2), primary key (ID), foreign key (dept_name) references department);

drop table instructor;

create table department (dept_name varchar(20), building varchar(15), budget number(12,2), primary key (dept_name));

describe instructor;

select * from user_catalog;

insert into instructor values ('10211', 'Smith', 'Biology', 66000);

insert into department (dept_name, building) values ('Biology', 'West Hall');

update department set budget = 25000 where dept_name = 'Biology';

update instructor set salary = salary * 1.05 where name = 'Smith';

update instructor
set salary = case
when salary <= 100000 then salary * 1.05
else salary * 1.03
end;

delete from department;

delete from instructor where name = 'Smith';

alter table instructor add email varchar(25);

alter table instructor drop column email;

truncate table department;

drop table instructor;

drop table department;

select sysdate from dual;