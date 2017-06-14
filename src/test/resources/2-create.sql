CREATE TABLE IF NOT EXISTS `student`(
`student_id` VARCHAR(10) NOT NULL,
`student_name` VARCHAR (30) NOT NULL,
`degree_scheme` VARCHAR(50) NOT NULL,
PRIMARY KEY(`student_ID`));

CREATE TABLE IF NOT EXISTS `module`(
`module_id` VARCHAR (10) NOT NULL,
`module_name` VARCHAR (50) NOT NULL,
`credits` INT NOT NULL,
PRIMARY KEY(`module_id`));


CREATE TABLE IF NOT EXISTS `staff`(
`staff_id` VARCHAR (10) NOT NULL,
`staff_name` VARCHAR (30) NOT NULL,
`grade` VARCHAR(30) NOT NULL,
PRIMARY KEY(`staff_id`));

CREATE TABLE IF NOT EXISTS `registered`(
`student_id` VARCHAR (10) NOT NULL,
`module_id` VARCHAR(10) NOT NULL,
PRIMARY KEY(`student_id`,`module_id`),
FOREIGN KEY (`student_id`)
REFERENCES `eeu43f`.`student` (`student_id`)
ON DELETE CASCADE,
FOREIGN KEY (`module_id`)
REFERENCES `eeu43f`.`module` (`module_id`)
ON DELETE CASCADE);

CREATE TABLE IF NOT EXISTS `teaches`(
`staff_id` VARCHAR (10) NOT NULL,
`module_id` VARCHAR (10) NOT NULL,

PRIMARY KEY (`staff_id`,`module_id`),
FOREIGN KEY (`staff_id`)
REFERENCES `eeu43f`.`staff` (`staff_id`)
ON DELETE CASCADE,
FOREIGN KEY (`module_id`)
REFERENCES `eeu43f`.`module` (`module_id`)
ON DELETE CASCADE);