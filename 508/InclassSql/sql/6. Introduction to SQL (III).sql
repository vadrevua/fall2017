select SYSDATE from dual;

select last_name, (SYSDATE-hire_date)/7 as weeks from employees;

select TO_CHAR(SYSDATE, 'DD MONTH YYYY') as Today from dual;

select TO_DATE('2003/07/09', 'yyyy/mm/dd') from dual;

select hire_date, ADD_MONTHS(hire_date,1) from employees;

select NEXT_DAY(sysdate,'TUESDAY') as "NEXT TUESDAY" from dual;

select MONTHS_BETWEEN (TO_DATE('02-02-1995','MM-DD-YYYY'), TO_DATE('01-01-1995','MM-DD-YYYY') ) as "Elapsed" from dual;

select * from employees, departments;

select * from employees, departments
where employees.department_id = departments.department_id;

select last_name, department_id, department_name
from employees, departments
where employees.department_id = departments.department_id;

select e.last_name, d.department_id, d.department_name
from employees e, departments d
where e.department_id = d.department_id;

select e.last_name as Employee, m.last_name as Manager
from employees e, employees m
where e.manager_id = m.employee_id;


select e.last_name as Employee, m.last_name as Manager
from employees e, employees m
where e.manager_id = m.employee_id
or e.manager_id is null;

select e.last_name as Employee, m.last_name as Manager
from employees e, employees m
where e.manager_id = m.employee_id
union
select e.last_name as Employee, null as Manager
from employees e
where e.manager_id is null;

select *
from employees inner join departments
on employees.department_id = departments.department_id;

select *
from employees, departments
where employees.department_id = departments.department_id;

select *
from employees left join departments
on employees.department_id = departments.department_id;

select *
from employees right join departments
on employees.department_id = departments.department_id;

select *
from employees full outer join departments
on employees.department_id = departments.department_id;

select *
from employees, departments
where employees.department_id = departments.department_id;

select e.last_name as Employee, m.last_name as Manager
from employees e left join employees m
on e.manager_id = m.employee_id;

