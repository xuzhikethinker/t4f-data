SELECT count(*) FROM table_hive;

SELECT composite_id, count 
  FROM (
    SELECT 
      composite_id, count(*) AS count 
    FROM 
      table 
    GROUP BY 
      composite_id
    ) t 
  WHERE 
    count > 1;   

SELECT table.*
  FROM table 
  LEFT OUTER JOIN table_hive 
    ON (
      table.composite_id = table_hive.composite_id
      )
  WHERE 
    table_hive.composite_id IS NULL;

SELECT table.*
  FROM table 
  LEFT OUTER JOIN raw_pageviews 
    ON (
      table.composite_id = raw_pageviews.v
      )
  WHERE 
    table_hive.composite_id IS NULL;

SELECT table.*
  FROM table 
  LEFT OUTER JOIN 
    (SELECT * from table_hive 
      WHERE table_hive.record_date='2012-08-01') table_hive
    ON (table_hive.composite_id = table.composite_id)
  WHERE table_hive.composite_id IS NULL;

SELECT count(table_hive.*)
  FROM table_hive 
  LEFT OUTER JOIN table
    ON (table_hive.composite_id = table.composite_id)
  WHERE table.composite_id IS NULL;

SELECT distinct table_hive.page_time
  FROM table_hive 
  LEFT OUTER JOIN table
    ON (table_hive.composite_id = table.composite_id)
  WHERE table.composite_id IS NULL;

SELECT distinct table_hive.composite_id
  FROM table_hive 
  LEFT OUTER JOIN table
    ON (table_hive.composite_id = table.composite_id)
  WHERE table.composite_id IS NULL;

-- -----------------------------------------------------------------------------
hive -e "use standarddatapipeline; describe extended t2_004_pings_partitioned partition (qb_g_r_tracking_id='test', qb_pv_d_recorddate='2012-08-01')"
s3n://name-model/raw/t2_004_pings_partitioned/qb_g_r_tracking_id=test/qb_pv_d_recorddate=2012-08-01
-- -----------------------------------------------------------------------------
hive -e "use standarddatapipeline; describe extended raw_pageviews;"
s3n://name-test/data/raw_pageviews
-- -----------------------------------------------------------------------------
create external table t2_108_pageviews (
        U_R_Visitor_ID                  STRING,
        PV_R_PingTime_UNIX              BIGINT,

        PV_R_URL                        STRING,
        PV_R_Referrer_URL               STRING,
        PV_R_PageNumber                 INT,
        
        PV_R_CustomValues               STRING,
        
        U_R_UserWebAgent                STRING,
        U_R_UserIPAddress               STRING,
        U_R_UserWebAgentSettings        STRING,
        
        Files_To_Make                      BIGINT,

        V_d_sdp_VisitNum                INT COMMENT 'Visit Num: new user, or referrer doesnt match prev page',
        V_d_sdp_PageNum                 INT COMMENT 'Page Num in visit: new user, or referrer doesnt match prev page',
        V_d_sdp_LastPage                INT COMMENT 'BOOL int Is last page in visit: new user, or referrer doesnt match prev page',
        V_d_sdp_VisitReferrer_URL       STRING COMMENT 'Referrer for visit: new user, or referrer doesnt match prev page',
        PV_d_sdp_TimeOnPage             INT COMMENT '', 
        V_d_sdp_TimeInVisit             INT COMMENT 'Duration of visit so far: new user, or referrer doesnt match prev page',
        PV_d_sdp_TimeOnSite             INT COMMENT 'Total user time on the site , over all visits, increments up with each pageview... a sum of all max V_d_sdp_TimeInVisit for previous visits + sum of PV_d_sdp_TimeOnPage the the current visit )',
        V_d_sdp_TimeSinceLastVisit      INT COMMENT 'Time since last visit: new user, or referrer doesnt match prev page',
        V_d_sdp_TimeSinceFirstVisit     INT COMMENT 'Time since first visit: new user, or referrer doesnt match prev page',

        V_d_sdp_ReferrerCategory        STRING COMMENT '',
        V_d_sdp_ReferrerSubCategory     STRING COMMENT '',

        PV_d_URLCategory                STRING COMMENT '',
        PV_d_URLSubCategory             STRING COMMENT '',
        
        PV_R_ClientPingTime_UNIX        BIGINT,
        V_R_SessionCounters             STRING,
        PV_R_PageView_ID                STRING,
        U_R_Group_Visitor_ID            STRING,
        G_R_Group_Tracking_ID           STRING,
        
        V_d_SearchQuery                 STRING,
        V_d_SearchQueryCategorisation   STRING,
        V_d_ElementCategorisation       STRING
        
  )
  PARTITIONED BY (G_R_TrackingID string, PV_d_RecordDate string)
  ROW FORMAT DELIMITED
  FIELDS TERMINATED BY '\t'
  -- STORED AS INPUTFORMAT "com.hadoop.mapred.DeprecatedLzoTextInputFormat"
  -- OUTPUTFORMAT "org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat"
  -- location 's3n://${DATA_MODEL_BUCKET}/data/t2_108_pageviews/'
;
-- -----------------------------------------------------------------------------
ALTER TABLE t2_108_pageviews ADD COLUMNS (
  V_d_GeoLocation    STRING COMMENT '2 letter country code based on IP of user'
)
;
-- ----------------------------------------------------------------------------
hive> describe extended pageviews;
OK
composite_id    string  pageview level identifier, combining visitor id, tracking id and record date
visitor_id  string  
server_side_time    bigint  
url string  
referrer_url    string  
browser_side_page_number    bigint  as generated in the javascript
custom_values   string  
user_web_agent  string  
user_ip_address string  
user_web_agent_settings string  
client_side_time    bigint  
qtracker_version    string  
session_counters    string  
pageview_id string  
group_visitor_id    string  
group_tracking_id   string  
page_time   bigint  calculated as the difference between successive pageviews
url_category    string  
url_subcategory string  
user_agent_categorisation   string  
geo_location    string  
clean_search_query  string  
search_categorisation   string  
page_num_in_visit   int 
visit_id    string  
visit_number    bigint  
visit_referrer_type string  
visit_referrer_details  string  
visit_referrer_label    string  
page_num_in_entrance    int 
entrance_id string  
entrance_number bigint  
entrance_referrer_type  string  
entrance_referrer_details   string  
entrance_referrer_label string  
page_num_in_session int 
session_id  string  
session_number  bigint  
canonical_url   string  
canonical_referrer_url  string  
raw_time_on_page    bigint  calculated as the time from 1st to last ping
entrance_referrer_url   string  
tracking_id string  
record_date string  
         
Detailed Table Information  Table(tableName:pageviews, dbName:standarddatapipeline, owner:batchrunner, createTime:1350985067, lastAccessTime:0, retention:0, sd:StorageDescriptor(cols:[FieldSchema(name:composite_id, type:string, comment:pageview level identifier, combining visitor id, tracking id and record date), FieldSchema(name:visitor_id, type:string, comment:null), FieldSchema(name:server_side_time, type:bigint, comment:null), FieldSchema(name:url, type:string, comment:null), FieldSchema(name:referrer_url, type:string, comment:null), FieldSchema(name:browser_side_page_number, type:bigint, comment:as generated in the javascript), FieldSchema(name:custom_values, type:string, comment:null), FieldSchema(name:user_web_agent, type:string, comment:null), FieldSchema(name:user_ip_address, type:string, comment:null), FieldSchema(name:user_web_agent_settings, type:string, comment:null), FieldSchema(name:client_side_time, type:bigint, comment:null), FieldSchema(name:qtracker_version, type:string, comment:null), FieldSchema(name:session_counters, type:string, comment:null), FieldSchema(name:pageview_id, type:string, comment:null), FieldSchema(name:group_visitor_id, type:string, comment:null), FieldSchema(name:group_tracking_id, type:string, comment:null), FieldSchema(name:page_time, type:bigint, comment:calculated as the difference between successive pageviews), FieldSchema(name:url_category, type:string, comment:null), FieldSchema(name:url_subcategory, type:string, comment:null), FieldSchema(name:user_agent_categorisation, type:string, comment:null), FieldSchema(name:geo_location, type:string, comment:null), FieldSchema(name:clean_search_query, type:string, comment:null), FieldSchema(name:search_categorisation, type:string, comment:null), FieldSchema(name:page_num_in_visit, type:int, comment:null), FieldSchema(name:visit_id, type:string, comment:null), FieldSchema(name:visit_number, type:bigint, comment:null), FieldSchema(name:visit_referrer_type, type:string, comment:null), FieldSchema(name:visit_referrer_details, type:string, comment:null), FieldSchema(name:visit_referrer_label, type:string, comment:null), FieldSchema(name:page_num_in_entrance, type:int, comment:null), FieldSchema(name:entrance_id, type:string, comment:null), FieldSchema(name:entrance_number, type:bigint, comment:null), FieldSchema(name:entrance_referrer_type, type:string, comment:null), FieldSchema(name:entrance_referrer_details, type:string, comment:null), FieldSchema(name:entrance_referrer_label, type:string, comment:null), FieldSchema(name:page_num_in_session, type:int, comment:null), FieldSchema(name:session_id, type:string, comment:null), FieldSchema(name:session_number, type:bigint, comment:null), FieldSchema(name:canonical_url, type:string, comment:null), FieldSchema(name:canonical_referrer_url, type:string, comment:null), FieldSchema(name:raw_time_on_page, type:bigint, comment:calculated as the time from 1st to last ping), FieldSchema(name:entrance_referrer_url, type:string, comment:null), FieldSchema(name:tracking_id, type:string, comment:null), FieldSchema(name:record_date, type:string, comment:null)], location:s3n://name-model-2/data/pageviews, inputFormat:com.hadoop.mapred.DeprecatedLzoTextInputFormat, outputFormat:org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat, compressed:false, numBuckets:-1, serdeInfo:SerDeInfo(name:null, serializationLib:org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe, parameters:{serialization.format= , field.delim=
Time taken: 0.458 seconds
hive> 
-- -----------------------------------------------------------------------------
LOAD DATA 
  LOCAL 
  INPATH '/q/bitbucket.git/sdp.packaging.git/pageviewreducer.out' 
  OVERWRITE 
  INTO TABLE 
  t2_108_pageviews 
  PARTITION (
    G_R_TrackingID='test', 
    PV_d_RecordDate='2012/09/13'
  )
;
-- -----------------------------------------------------------------------------
SELECT 
    U_R_Visitor_ID
  FROM
    t2_108_pageviews
  WHERE 
    G_R_TrackingID = 'test' 
    AND PV_d_RecordDate >= '2012/08/18' 
    AND PV_d_RecordDate <= '2012/09/20'
    AND U_R_Visitor_ID != ""
    AND NOT(U_R_Visitor_ID IS NULL)
;
