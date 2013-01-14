CREATE DATABASE IF NOT EXISTS db_name;

USE db_name;

DROP TABLE IF EXISTS table_name;

create external table table_name
(
        visitor_ID                         STRING,
        server_side_time                   BIGINT,

        url                        STRING,
        time_on_page                 BIGINT,
        
        custom_values               STRING,
        
        user_web_agent                STRING,
        user_ip_address               STRING,
        user_web_agent_settings        STRING,

        client_side_time        BIGINT,
        session_counters             STRING,
        table_ID                STRING,
        group_visitor_ID            STRING,
        group_tracking_ID           STRING,

        composite_ID STRING
        
)
PARTITIONED BY (tracking_ID string, record_date string)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
-- STORED AS 
--  INPUTFORMAT "com.hadoop.mapred.DeprecatedLzoTextInputFormat"
--  OUTPUTFORMAT "org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat"
-- location 's3n://${DATA_MODEL_BUCKET}/data/tables/'
LOCATION 'hdfs://localhost:9000/name-test/data/table_name';

LOAD DATAnameOCAL INPATH '/w/qbt/name.git/src/csv/table_name_2012-08-01.csv' 
  OVERWRITE INTO TABLE table_name 
  PARTITION (tracking_ID='test', record_date='2012-08-01');
LOAD DAname LOCAL INPATH '/w/qbt/name.git/src/csv/table_name_2012-08-02.csv' 
  OVERWRITE INTO TABLE table_name 
  PARTITION (tracking_ID='test', record_date='2012-08-02');

ALTER TABLE table_name 
  DROP IF EXISTS PARTITION (tracking_ID='test', record_date='2012-08-01');
ALTER TABLE table_name 
  DROP IF EXISTS PARTITION (tracking_ID='test', record_date='2012-08-02');

ALTER TABLE table_name 
  ADD PARTITION (tracking_ID='test', record_date='2012-08-01') 
    LOCATION 'hdfs://localhost:9000/name-test/data/table/tracking_id=test/record_date=2012-08-01';
ALTER TABLE table_name 
  ADD PARTITION (tracking_ID='test', record_date='2012-08-02') 
  LOCATION 'hdfs://localhost:9000/name-test/data/table/tracking_id=test/record_date=2012-08-02';

SELECT count(*) FROM table_name;


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
