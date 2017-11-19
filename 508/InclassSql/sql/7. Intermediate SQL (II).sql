select last_name from employees
where
salary > (select salary from employees where employee_id = 111);

select last_name from employees
where
salary > (select salary from employees where employee_id = 111)
and
job_id = (select job_id from employees where employee_id = 109);

select department_name, MAX(salary)
from employees join departments
on employees.department_id = departments.department_id
group by department_name
having MIN(salary) >
(select MIN(salary) from employees where department_id = 30);

select last_name, job_title
from employees natural join jobs
where salary <
ANY (select salary from employees where job_id LIKE 'SA%')
and job_title NOT LIKE 'Sales%';

select last_name, job_title
from employees natural join jobs
where salary > 
ALL (select AVG(salary) from employees group by department_id);

select last_name, job_title
from employees natural join jobs
where manager_id
IN (select employee_id from employees where department_id = 20);

select distinct department_name
from employees e1 join departments
on e1.department_id = departments.department_id
where exists 
(select * from employees e2
 where e1.department_id = e2.department_id
 and e1.employee_id <> e2.employee_id);


select e1.last_name, e1.salary
from employees e1
where e1.salary >
(select AVG(salary) from employees e2
where e2.department_id = e1.department_id);

select e.last_name employee, e.department_id,
    m.last_name manager, e.salary
from employees e join employees m
   on e.manager_id = m.employee_id
where (e.manager_id, e.salary)
IN (select manager_id, salary from
      employees where department_id = 80)
order by department_id;

select e.last_name employee, e.department_id,
    m.last_name manager, e.salary
from employees e join employees m
   on e.manager_id = m.employee_id
where e.manager_id
IN (select manager_id from
      employees where department_id = 80)
and e.salary
IN (select salary from
      employees where department_id = 80)
order by department_id;

select last_name
from employees
where employee_id NOT IN (select manager_id from employees);

select last_name
from employees
where employee_id NOT IN 
(select manager_id from employees where manager_id is not null);

select last_name, job_title, salary, averages.avgDept
from employees natural join jobs ,
(select department_id, AVG(salary) avgDept
from employees
group by department_id) averages
where employees.department_id = averages.department_id
and employees.salary > averages.avgDept;

select department_name, (select count(*) from employees where departments.department_id = employees.department_id) from departments;

select department_name, count(*)
from employees join departments
on employees.department_id = departments.department_id
group by department_name;

select department_name, count(*)
from employees join departments on departments.department_id = employees.department_id
group by department_name;

select department_name, count(*)
from employees full join departments on departments.department_id = employees.department_id
group by department_name;