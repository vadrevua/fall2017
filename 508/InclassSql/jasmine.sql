savepoint firstSave;
create table traveledCountries(name varchar(5), foreign key(employee_ID) references employees, foreign key(location_ID) references locations );
rollback to savepoint firstSave;
