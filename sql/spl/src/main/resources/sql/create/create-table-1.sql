DROP TABLE BOOK
;

CREATE TABLE BOOK (
        SID INT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 20, INCREMENT BY 1),
        TITLE VARCHAR ( 24 ) NOT NULL,
        AUTHOR VARCHAR ( 24 ) NOT NULL,
        PRICE DECIMAL ( 8, 4 ) NOT NULL,
        BOOK_CATEGORY_SID INT
        )
;

DROP TABLE BOOK_CATEGORY
;

CREATE TABLE BOOK_CATEGORY (
        SID INT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 20, INCREMENT BY 1),
        NAME VARCHAR ( 250 ) NOT NULL
        )
;

ALTER TABLE BOOK
        ADD CONSTRAINT BOO_Constraint1 PRIMARY KEY (
                SID
                )
;

ALTER TABLE BOOK_CATEGORY
        ADD CONSTRAINT BOO_Constraint3 PRIMARY KEY (
                SID
                )
;
