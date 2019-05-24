DROP TABLE IF EXISTS workflow_task_choose_state_config;
CREATE TABLE workflow_task_choose_state_config (
	 id_task INT DEFAULT 0 NOT NULL ,
	 controller_name varchar(100) default NULL,
	 id_state_ok int(11) NOT NULL,
	 id_state_ko int(11) NOT NULL,
	 PRIMARY KEY (id_task)
);

DROP TABLE IF EXISTS workflow_task_choose_state_information;
CREATE TABLE workflow_task_choose_state_information (
  id_history int(11) NOT NULL,
  id_task int(11) NOT NULL,
  new_state VARCHAR(255) NOT NULL
);