select AVG(salary) from employees;

select AVG(salary) from employees where department_id = 100;

select MAX(salary) from employees where manager_id is not null;

select COUNT(*) from employees;

select COUNT(*) from employees where first_name = 'John';

select COUNT(distinct last_name) from employees;

select AVG(salary), MAX(salary), MIN(salary), SUM(salary) from employees natural join jobs where job_title like 'Sales%';

select MIN(hire_date), MAX(hire_date) from employees where salary between 1500 and 2500;

select avg(commission_pct) from employees;

select avg(nvl(commission_pct,0)) from employees;

-- select department_id, count(*) from employees group by department_id;

select department_id, count(*) from employees group by department_id;

select department_id, AVG(salary) from employees group by department_id order by department_id;

-- select department_id, AVG(salary) from employees where AVG(salary) > 5000 group by department_id;

select department_id, AVG(salary) from employees where salary > 5000 group by department_id;

select department_name, avg(salary) from employees join departments on employees.department_id = departments.department_id group by department_name having avg(salary) > 5000;

SELECT    job_title, SUM(salary) FROM      employees natural join jobs WHERE	job_title NOT LIKE 'Sales%' GROUP BY  job_title HAVING    AVG(salary) > 5000 ORDER BY  SUM(salary) DESC;

SELECT    MAX(AVG(salary)) FROM      employees GROUP BY  department_id;

SELECT  department_name, job_title, AVG(salary) FROM employees join departments on employees.department_id = departments.department_id join jobs on employees.job_id = jobs.job_id GROUP BY department_name, job_title ORDER BY job_title, department_name;

SELECT j.job_title, MAX(e.salary)  FROM employees e join jobs j on e.job_id = j.job_id GROUP BY j.job_title HAVING MAX(e.salary) >=4000;