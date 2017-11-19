CREATE TABLE sal_log (
  log_date date,
  employee_id NUMBER(6,0) references employees,
  new_salary NUMBER(8,2),
  old_salary NUMBER(8,2),
  primary key (log_date, employee_id));

CREATE OR REPLACE TRIGGER log_salary
AFTER UPDATE OF salary ON employees
FOR EACH ROW
BEGIN
INSERT INTO sal_log (log_date, employee_id, new_salary, old_salary)
VALUES (SYSDATE, :new.employee_id, :new.salary, :old.salary);
END;
/

update employees set salary = 50000 where employee_id = 100;
update employees set salary = 24000 where employee_id = 100;
truncate table sal_log;

CREATE OR REPLACE TRIGGER update_job_history
AFTER UPDATE OF job_id, department_id ON employees
FOR EACH ROW
BEGIN
add_job_history(:old.employee_id, :old.hire_date, sysdate, :old.job_id, :old.department_id);
END;
/

update employees set job_id = 'AD_VP', hire_date = sysdate where employee_id = 103;
update employees set job_id = 'IT_PROG', hire_date = sysdate where employee_id = 103;

CREATE OR REPLACE TRIGGER salary_check
BEFORE INSERT OR UPDATE OF job_id, salary ON employees
FOR EACH ROW
BEGIN
check_sal(:new.job_id, :new.salary, :new.last_name);
END;
/

-- check_sal is a procedure not yet created in the database, therefore the trigger compilation will produce an error

CREATE VIEW emp_dept_join (first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, employee_manager_id, department_name, department_manager_id, location_id)
as
select  first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, employees.manager_id, department_name, departments.manager_id, location_id from employees join departments using (department_id);

CREATE OR REPLACE TRIGGER insert_emp_dept  INSTEAD OF INSERT ON emp_dept_join
DECLARE  v_department_id departments.department_id%TYPE;
BEGIN
  BEGIN
    SELECT department_id INTO v_department_id
    FROM   departments
    WHERE  department_name = :new.department_name;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      INSERT INTO departments (department_id, department_name) VALUES (departments_seq.nextval, :new.department_name) RETURNING department_id INTO v_department_id;
  END;
  INSERT INTO employees (employee_id, first_name, last_name, email, hire_date, job_id, department_id)
  VALUES(employees_seq.nextval, :new.first_name, :new.last_name, :new.email, :new.hire_date, :new.job_id, v_department_id);
END;
/

INSERT INTO emp_dept_join VALUES ('Alberto', 'Cano', 'acano@vcu.edu', null, sysdate, 'AD_PRES', 5000, null, 100, 'Marketing', null, null);
-- salary is ignored despite the view selects, but the trigger explicitly ignores.
INSERT INTO emp_dept_join VALUES ('Alberto', 'Cano', 'acanotheppt@vcu.edu', null, sysdate, 'AD_PRES', 5000, null, 100, 'Powerpointing', null, null);

alter table departments add total_salary numeric;

CREATE OR REPLACE TRIGGER total_salary 
AFTER DELETE OR INSERT OR UPDATE OF department_id, salary ON employees
FOR EACH ROW BEGIN 
IF DELETING OR (UPDATING AND :old.department_id != :new.department_id) 
THEN UPDATE departments 
SET total_salary = total_salary - :old.salary
WHERE department_id = :old.department_id;
END IF;
IF INSERTING OR (UPDATING AND :old.department_id != :new.department_id) 
THEN UPDATE departments 
SET total_salary = total_salary + :new.salary
WHERE department_id = :new.department_id; 
END IF;
IF (UPDATING AND :old.department_id = :new.department_id AND :old.salary != :new.salary) 
THEN UPDATE departments 
SET total_salary = total_salary - :old.salary + :new.salary
WHERE department_id = :new.department_id; 
END IF; 
END;
/

update employees set salary = salary;
update departments set total_salary = 0;

update departments d
set d.total_salary =
(select sum(e.salary) from employees e
where d.department_id = e.department_id);

alter table departments drop column total_salary;

drop trigger total_salary;

CREATE OR REPLACE TRIGGER update_salary 
AFTER INSERT OR UPDATE OF department_id ON employees
FOR EACH ROW BEGIN 
IF INSERTING OR
(UPDATING AND :old.department_id != :new.department_id)
THEN UPDATE employees 
SET salary = salary*1.05
WHERE department_id = :new.department_id;
END IF;
END;
/

update employees set department_id = 20 where employee_id = 100; -- woopps, mutating table

CREATE OR REPLACE TRIGGER update_salary 
BEFORE INSERT ON employees
FOR EACH ROW BEGIN 
UPDATE employees 
SET salary = salary*1.05
WHERE department_id = :new.department_id;
END;
/

update employees set department_id = 20 where employee_id = 100; -- that's fine