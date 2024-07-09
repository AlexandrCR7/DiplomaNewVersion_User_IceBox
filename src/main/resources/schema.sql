create table icebox (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
list VARCHAR(300) NOT NULL
);

create table users (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(300) NOT NULL,
products VARCHAR(300) NOT NULL,
icebox_id INT NOT NULL,
FOREIGN KEY (icebox_id) REFERENCES icebox (id)
);

CREATE TABLE icebox_ingredient
(
    user_id INT NOT NULL,
    icebox_id INT NOT NULL,
    PRIMARY KEY (user_id, icebox_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (icebox_id) REFERENCES icebox (id)
);