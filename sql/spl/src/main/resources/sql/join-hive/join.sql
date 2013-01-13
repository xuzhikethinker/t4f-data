use test;

create external table tables (
        visitor_id                         STRING,
        server_side_time                   BIGINT,

        url                        STRING,
        referrer_url               STRING,

        group_tracking_ID           STRING,

        visitor_table_composite_ID STRING,
        full_composite_ID STRING
        
)
PARTITIONED BY (tracking_ID string, record_date string)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
STORED AS INPUTFORMAT "com.hadoop.mapred.DeprecatedLzoTextInputFormat"
OUTPUTFORMAT "org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat"
location 's3n://..../data/tables/';

set mapred.output.compress=true;
set hive.exec.compress.output=true;
set io.seqfile.compression.type=BLOCK;
set mapred.output.compression.codec=com.hadoop.compression.lzo.LzopCodec;
set hive.exec.dynamic.partition.mode=nonstrict;
set hive.exec.dynamic.partition=true;

set hive.exec.max.dynamic.partitions=100000;
set hive.exec.max.dynamic.partitions.pernode=10000;


INSERT OVERWRITE TABLE tables
PARTITION(tracking_ID, record_date)
SELECT
        PV_RTime_UNI   PV_R
    PV_Rrrer
    PV_Rr,

    _CustomVal
  R_Usergent _UserIPAds,
    UserWebting   PVlientPing_UNIX,
    V_R_Sessounte _eView_ID,
    Group_Visitor_ID,
    Group_TracIngId   at(PageView_ID,Visitor_ID),
    at(ID_Visitor_ID,RecateTrackingIG_R_TragID, Recate

 FROM 
  tablename;

-- -------------------------tablename------------------------
ce external tapv_categorised
(
        visitor_id                         STRING,
        server_side_time                   BIGINT,

        url_category                        STRING,
        url_subcategory                        STRING,
        referrer_type               STRING,
        referrer_labels               STRING,
        referrer_details               STRING,

        search_query               STRING,
        search_query_cat               STRING,

        client_side_time        BIGINT,
        table_ID                STRING,

        visitor_table_composite_ID STRING,
        full_composite_ID STRING
        
)
PARTITIONED BY (tracking_ID string, record_date string)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
STORED AS INPUTFORMAT "com.hadoop.mapred.DeprecatedLzoTextInputFormat"
OUTPUTFORMAT "org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat"
location 's3n://name-test/data/pv_categorised/';

INSERT OVERWRITE TABLE pv_categorised
 §PARTITION(tracking_ID, rd_date)
SELECT
    Visitor_ID,
    Pin_UNIX,

    URLgory,
    URLSubCategory,

    ReferrerCate,
    ReferrerSubCategory,
    V_d_Visitrrer_Details,


    SearchQuery,
    V_d_SearchQueryCategorisation,

    ClingTime_UNIX,
    Pag_ID,

    at(PageVieVisitor_ID),
    at(PageView_IDVisitor_ID,RecordDate,G_R_TingID),
G_R_TraID, RecordDate

 FROM table;

-- -----------------------------------------------

select count(*) tablename (a.visitor_ID=b.visitor_ID and geview_ID=b.table_ID and a.record_date=b.record_date and a.trackID=b.tracking;

select count(*) from tables a JOIN pv_categorised b ON (a.visitor_ID=b.visitor_ID and a.table_ID=b.table_ID and a.record_date=b.record_date and a.tracking_ID=b.tracking_ID) 
 where a.record_date='2012-08-01' and b.record_date='2012-08-01' and a.tracking_ID='test' and b.tracking_ID='test';

select count(*) from tables a JOIN pv_categorised b on (a.full_composite_ID=b.full_composite_ID) where a.record_date='2012-08-01' and b.record_date='2012-08-01'  and a.tracking_ID='test' and b.tracking_ID='test';
