CREATE TABLE `schedules` (
                             `schedule_id`	BIGINT	NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                             `director_name` VARCHAR(50) NOT NULL,
                             `work`	VARCHAR(200)	NOT NULL,
                             `pw`	VARCHAR(50)	NOT NULL,
                             `schedule_time`	DATETIME	NOT NULL,
                            `registered_date` DATETIME NOT NULL,
                            `modified_date` DATETIME NULL
);


CREATE TABLE `director` (
                            `director_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                            `email`	VARCHAR(50)	NOT NULL,
                            `registrated_date`	DATETIME	NOT NULL,
                            `modified_date`	DATETIME	NULL,
                            `deleted_date`	DATETIME	NULL,
                            `director_name`	VARCHAR(50)	NOT NULL
);




