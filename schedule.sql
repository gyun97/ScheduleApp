CREATE TABLE `schedules` (
                             `schedule_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             `director_id` BIGINT NOT NULL,
                             `director_name` VARCHAR(50) NOT NULL,
                             `work` VARCHAR(200) NOT NULL,
                             `pw` VARCHAR(50) NOT NULL,
                             `schedule_time` DATETIME NOT NULL,
                             `registered_date` DATETIME NOT NULL,
                             `modified_date` DATETIME NULL,
                             FOREIGN KEY (`director_id`) REFERENCES `director`(`director_id`) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE `director` (
                            `director_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                            `email`	VARCHAR(50)	NOT NULL,
                            `registered_date`	DATETIME	NOT NULL,
                            `modified_date`	DATETIME	NULL,
                            `director_name`	VARCHAR(50)	NOT NULL
);





