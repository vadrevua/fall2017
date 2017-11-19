Create table foo (
ID number(4,0) primary key,
Name varchar2(255)
);

CREATE SEQUENCE foo_pk_seq
 START WITH     1000
 MAXVALUE       5000
 INCREMENT BY   1
 NOCYCLE;
 
insert into foo values (foo_pk_seq.nextval, 'John');
insert into foo values (foo_pk_seq.nextval, 'Adam');
truncate table foo;
insert into foo values (foo_pk_seq.nextval, 'Jake');
 
drop table foo;
drop sequence foo_pk_seq;

CREATE OR REPLACE FUNCTION RetrieveSalary
     RETURN NUMBER
IS
     v_Salary NUMBER(10,2);
BEGIN 
     SELECT salary INTO v_Salary 
     FROM employees 
     WHERE employee_id = '100';
     RETURN v_Salary;
END RetrieveSalary;
/

RetrieveSalary(); -- nope, this is a function
select * from RetrieveSalary; -- nope, this is a function
select RetrieveSalary from dual;

CREATE OR REPLACE FUNCTION RetrieveSalary (
  p_employee_id in employees.employee_id%TYPE )
     RETURN NUMBER
IS
     v_Salary employees.salary%TYPE;
BEGIN 
     SELECT salary INTO v_Salary 
     FROM employees 
     WHERE employee_id = p_employee_id;
     RETURN v_Salary;
END RetrieveSalary;
/

select RetrieveSalary(100) from dual;

CREATE OR REPLACE PROCEDURE IncreaseSalary (
  p_employee_id in employees.employee_id%TYPE )
IS
BEGIN 
     UPDATE employees set salary = salary * 1.05
     where employee_id = p_employee_id;
END IncreaseSalary;
/

exec IncreaseSalary(100);

create or replace PROCEDURE get_emp_names (dept_num IN NUMBER)
IS
      emp_name  VARCHAR2(10);
      CURSOR    c1 (dept_num NUMBER) IS
                         SELECT LAST_NAME FROM EMPLOYEES
                         WHERE DEPARTMENT_ID = dept_num;
BEGIN
    OPEN c1(dept_num);
    LOOP
         FETCH c1 INTO emp_name;
         EXIT WHEN C1%NOTFOUND;
         DBMS_OUTPUT.PUT_LINE(emp_name);
    END LOOP;
    CLOSE c1;
END;
/

SET SERVEROUTPUT ON;
exec get_emp_names(100);
