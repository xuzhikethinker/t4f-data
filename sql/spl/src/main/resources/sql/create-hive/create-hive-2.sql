CREATE DATABASE IF NOT EXISTS dbname;

USE dbname;

DROP TABLE IF EXISTS table_hive;

CREATE EXTERNAL TABLE table_hive
(

        composite_ID     STRING,
        page_time        INT
        
)
PARTITIONED BY (tracking_ID string, record_date string)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
-- STORED AS 
--  INPUTFORMAT "com.hadoop.mapred.DeprecatedLzoTextInputFormat"
--  OUTPUTFORMAT "org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat"
-- LOCATION 's3n://${DATA_MODEL_BUCKET}/data/table_hive/'
  LOCATION 'hdfs://localhost:9000/name-test/data/table_hive';

ALTER TABLE table_hive 
  DROP IF EXISTS PARTITION (tracking_ID='test', record_date='2012-08-01');
ALTER TABLE table_hive 
  DROP IF EXISTS PARTITION (tracking_ID='test', record_date='2012-08-02');

ALTER TABLE table_hive 
  ADD PARTITION (tracking_ID='test', record_date='2012-08-01') 
    LOCATION 'hdfs://localhost:9000/name-test/data/table_hive/tracking_id=test/record_date=2012-08-01';
ALTER TABLE table_hive 
  ADD PARTITION (tracking_ID='test', record_date='2012-08-02') 
  LOCATION 'hdfs://localhost:9000/name-test/data/table_hive/tracking_id=test/record_date=2012-08-02';

SELECT count(*) FROM table_hive;
