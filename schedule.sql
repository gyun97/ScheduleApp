CREATE TABLE `schedules` (
                             `schedule_id`	INT	NOT NULL,
                             `director_id`	int	NOT NULL,
                             `director`	int	NOT NULL,
                             `work`	VARCHAR(200)	NOT NULL,
                             `pw`	VARCHAR(50)	NOT NULL,
                             `schedule_time`	DATETIME	NOT NULL
);

CREATE TABLE `director` (
                            `director_id`	int	NOT NULL,
                            `email`	VARCHAR(50)	NOT NULL,
                            `registrated_date`	DATETIME	NOT NULL,
                            `modified_date`	DATETIME	NULL,
                            `deleted_date`	DATETIME	NULL,
                            `director_name`	VARCHAR(50)	NOT NULL
);

ALTER TABLE `schedules` ADD CONSTRAINT `PK_SCHEDULES` PRIMARY KEY (
                                                                   `schedule_id`,
                                                                   `director_id`
    );

ALTER TABLE `director` ADD CONSTRAINT `PK_DIRECTOR` PRIMARY KEY (
                                                                 `director_id`
    );

ALTER TABLE `schedules` ADD CONSTRAINT `FK_director_TO_schedules_1` FOREIGN KEY (
                                                                                 `director_id`
    )
    REFERENCES `director` (
                           `director_id`
        );