create table users (
id INT PRIMARY KEY,
name VARCHAR(300) NOT NULL
);

create table icebox (
id INT PRIMARY KEY,
ingredients VARCHAR(300) NULL,
user_id INT UNIQUE,
FOREIGN KEY (user_id) REFERENCES users (id)
);

