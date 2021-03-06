CREATE TABLE EVENT (
	SID INTEGER GENERATED BY DEFAULT AS IDENTITY DEFAULT (START WITH 1, INCREMENT BY 1) NOT NULL,
	EVENT_DATE TIMESTAMP DEFAULT CURRENT TIMESTAMP NOT NULL,
	EVENT_TYPE_ID INTEGER,
	MEMBER_ID INTEGER,
	ITEM_SID INTEGER,
	ITEM_BUNDLE_SID INTEGER,
	ITEM_DIFFUSED_SID INTEGER,
	DIFFUSION_ID INTEGER,
	MESSAGE VARCHAR ( 1000 )
	)
;

ALTER TABLE EVENT
	ADD CONSTRAINT PK_AOS_NEWS_EV PRIMARY KEY (
		SID
		)
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVE_Constraint6 FOREIGN KEY (
		DIFFUSION_ID
		)
	REFERENCES DIFFUSION (
		ID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT NEWS_EVENT_7 FOREIGN KEY (
		EVENT_TYPE_ID
		)
	REFERENCES EVENT_TYPE (
		ID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVE_Constraint5 FOREIGN KEY (
		MEMBER_ID
		)
	REFERENCES MEMBER (
		MEMBER_ID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVE_Constraint4 FOREIGN KEY (
		ITEM_BUNDLE_SID
		)
	REFERENCES ITEM_BUNDLE (
		SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVE_Constraint3 FOREIGN KEY (
		ITEM_SID
		)
	REFERENCES ITEM (
		SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVE_Constraint1 FOREIGN KEY (
		ITEM_DIFFUSED_SID
		)
	REFERENCES ITEM_DIFFUSED (
		SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;


