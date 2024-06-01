CREATE TABLE sexo(
Id  serial primary key, 
sexo varchar (50)
);
insert into sexo (sexo) values ('Masculino');
insert into sexo (sexo) values ('Femenino');

select * from sexo ;


create table estudiantes(
Id serial primary key,
nombres varchar (100),
apellidos varchar (100),
fksexo int,
edad int,
fecha_nacimiento date,
	
foreign key (fksexo) references sexo (id) on delete cascade on update cascade
);


select * from estudiantes;

select estudiantes.id,estudiantes.nombres,estudiantes.apellidos, sexo.sexo,estudiantes.edad,estudiantes.fecha_nacimiento
from estudiantes inner join sexo on estudiantes.fksexo = sexo.id;

 

