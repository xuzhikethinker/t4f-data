CREATE TABLE ITEM (
	SID INTEGER GENERATED BY DEFAULT AS IDENTITY DEFAULT (START WITH 3, INCREMENT BY 1) NOT NULL,
	TICKET_NUMBER VARCHAR ( 255 ) NOT NULL,
	DISPLAY_NAME VARCHAR ( 255 ),
	EXTENSION VARCHAR ( 255 ),
	VALUE INTEGER,
	STATUS_TYPE_SID INTEGER NOT NULL,
	ITEM_TYPE_SID INTEGER NOT NULL,
	PARENT_SID INTEGER
	)
;

ALTER TABLE ITEM
	ADD CONSTRAINT ITE_Constraint1 PRIMARY KEY (
		SID
		)
;

ALTER TABLE ITEM
	ADD CONSTRAINT ITE_Constraint3 FOREIGN KEY (
		ITEM_TYPE_SID
		)
	REFERENCES ITEM_TYPE (
		SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM
	ADD CONSTRAINT ITE_Constraint2 FOREIGN KEY (
		STATUS_TYPE_SID
		)
	REFERENCES STATUS_TYPE (
		SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM
	ADD CONSTRAINT ITE_Constraint4 FOREIGN KEY (
		PARENT_SID
		)
	REFERENCES ITEM (
		SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

