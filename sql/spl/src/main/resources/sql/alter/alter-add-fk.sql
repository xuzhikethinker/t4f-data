ALTER TABLE BOOK
	ADD CONSTRAINT BOO_Constraint8 FOREIGN KEY (
		BOOK_CATEGORY_SID
		)
	REFERENCES BOOK_CATEGORY (
		SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;