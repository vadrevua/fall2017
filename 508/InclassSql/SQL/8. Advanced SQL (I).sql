create view employeesData (employee_id, last_name, department_name) as
select employee_id, last_name, department_name from employees, departments
where employees.department_id = departments.department_id;

create view employeesLocation (employee_id, last_name, city) as
select employee_id, last_name, city from employees join departments
on employees.department_id = departments.department_id
join locations on departments.location_id = locations.location_id;

create view employeesDataLocation
(last_name, department_name, city) as
select employeesData.last_name, department_name, city
from employeesData join employeesLocation on employeesData.employee_id = employeesLocation.employee_id;

create view highSalaryEmployees
(last_name, department_name, salary) as
select e.last_name, d.department_name, e.salary
from employees e join departments d
on e.department_id = d.department_id
where e.salary >
(select AVG(salary) from employees c
where e.department_id = c.department_id);

create view regionsView
as select region_id, region_name from regions;

insert into regionsView values (99,'Antarctica');

update regionsView
set region_name = 'Potato Land' where region_id = 99;

--insert into employeesDataLocation values ('John', 'Marketing', 'Seattle');

delete from regionsView where region_id = 99;

drop view employeesData;
drop view employeesLocation;
drop view employeesDataLocation;
drop view highSalaryEmployees;
drop view regionsView;


select d.department_id, d.department_name, stats.salary, stats.nemployees
from departments d,
(select dd.department_id, avg(e.salary) as salary, count(*) as nemployees
from employees e, departments dd
where e.department_id = dd.department_id
group by dd.department_id) stats
where d.department_id = stats.department_id
and stats.nemployees < 10 and stats.salary > 5000;

create or replace view department_average_salary (department_id, average_salary) as
select e.department_id, AVG(e.salary)
from employees e
group by e.department_id
having e.department_id is not null;

create or replace view department_employee_count (department_id, employee_count) as
select e.department_id, COUNT(*)
from employees e
group by e.department_id
having e.department_id is not null;

select d.department_id, d.department_name, a.average_salary, c.employee_count
from departments d, department_average_salary a, department_employee_count c
where d.department_id = a.department_id and d.department_id = c.department_id
and c.employee_count < 10 and a.average_salary > 5000;