create table Department
(
    id          bigint PRIMARY KEY,
    description varchar
);

create table Employee
(
    id            bigint PRIMARY KEY,
    name          varchar not null,
    department_id bigint,
    CONSTRAINT fk_department
        FOREIGN KEY (department_id)
            REFERENCES Department (id)
);

INSERT into Department(id, description)
VALUES (1, 'Development');
INSERT into Department(id, description)
VALUES (2, 'HR');
INSERT into Department(id, description)
VALUES (3, 'C-LEVEL');

INSERT into Employee (id, name, department_id)
VALUES (1, 'DevJava', 1);
INSERT into Employee (id, name, department_id)
VALUES (2, 'DevPHP', 1);
INSERT into Employee (id, name, department_id)
VALUES (3, 'Suzy', 2);
INSERT into Employee (id, name, department_id)
VALUES (4, 'The Boss', 3);




