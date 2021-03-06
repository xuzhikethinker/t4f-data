CREATE TABLE TEST (
  C1 char(50)
);

COMMIT;

-- 1 v1
INSERT INTO TEST (C1) VALUES ('v1');
-- 4 v2
INSERT INTO TEST (C1) VALUES ('v2');
INSERT INTO TEST (C1) VALUES ('v2');
INSERT INTO TEST (C1) VALUES ('v2');
INSERT INTO TEST (C1) VALUES ('v2');
-- 2 v3
INSERT INTO TEST (C1) VALUES ('v3');
INSERT INTO TEST (C1) VALUES ('v3');
-- 1 v4
INSERT INTO TEST (C1) VALUES ('v4');

COMMIT;

SELECT COUNT(*) FROM TEST;

SELECT COUNT(C1) FROM TEST;

SELECT DISTINCT COUNT(C1) FROM TEST GROUP BY C1;

SELECT C1, COUNT(C1) FROM TEST GROUP BY C1;

SELECT COUNT(*) FROM ( SELECT C1, COUNT(C1) FROM TEST GROUP BY C1) a;

SELECT SUM(a.C), a.C1 FROM (SELECT C1, COUNT(C1) AS C FROM TEST GROUP BY C1) AS a GROUP BY a.C1;
-- 1   v1                                                
-- 4   v2                                                
-- 2   v3                                                
-- 1   v4                                                
