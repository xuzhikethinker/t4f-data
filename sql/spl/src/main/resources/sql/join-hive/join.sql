use test;

create external table pageviews (
        visitor_id                         STRING,
        server_side_time                   BIGINT,

        url                        STRING,
        referrer_url               STRING,

        group_tracking_ID           STRING,

        visitor_pageview_composite_ID STRING,
        full_composite_ID STRING
        
)
PARTITIONED BY (tracking_ID string, record_date string)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
STORED AS INPUTFORMAT "com.hadoop.mapred.DeprecatedLzoTextInputFormat"
OUTPUTFORMAT "org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat"
location 's3n://..../data/pageviews/';

set mapred.output.compress=true;
set hive.exec.compress.output=true;
set io.seqfile.compression.type=BLOCK;
set mapred.output.compression.codec=com.hadoop.compression.lzo.LzopCodec;
set hive.exec.dynamic.partition.mode=nonstrict;
set hive.exec.dynamic.partition=true;

set hive.exec.max.dynamic.partitions=100000;
set hive.exec.max.dynamic.partitions.pernode=10000;


INSERT OVERWRITE TABLE pageviews
PARTITION(tracking_ID, record_date)
SELECT
    U_R_Visitor_ID,
    PV_R_PingTime_UNIX,

    PV_R_URL,
    PV_R_Referrer_URL,
    PV_R_PageNumber,

    PV_R_CustomValues,

    U_R_UserWebAgent,
    U_R_UserIPAddress,
    U_R_UserWebAgentSettings,

    PV_R_ClientPingTime_UNIX,
    V_R_SessionCounters,
    PV_R_PageView_ID,
    U_R_Group_Visitor_ID,
    G_R_Group_Tracking_ID,
    concat(PV_R_PageView_ID,U_R_Visitor_ID),
    concat(PV_R_PageView_ID,U_R_Visitor_ID,PV_d_RecordDate,G_R_TrackingID),
G_R_TrackingID, PV_d_RecordDate


    FROM 
standarddatapipeline.t2_108_pageviews;

-- ------------------------------------------------------------------------------------------
create external table pv_categorised
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
        pageview_ID                STRING,

        visitor_pageview_composite_ID STRING,
        full_composite_ID STRING
        
)
PARTITIONED BY (tracking_ID string, record_date string)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
STORED AS INPUTFORMAT "com.hadoop.mapred.DeprecatedLzoTextInputFormat"
OUTPUTFORMAT "org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat"
location 's3n://name-test/data/pv_categorised/';


INSERT OVERWRITE TABLE pv_categorised
PARTITION(tracking_ID, record_date)
SELECT
    U_R_Visitor_ID,
    PV_R_PingTime_UNIX,

    PV_d_URLCategory,
    PV_d_URLSubCategory,

    V_d_sdp_ReferrerCategory,
    V_d_sdp_ReferrerSubCategory,
    V_d_Visit_Referrer_Details,


    V_d_SearchQuery,
    V_d_SearchQueryCategorisation,

    PV_R_ClientPingTime_UNIX,
    PV_R_PageView_ID,

    concat(PV_R_PageView_ID,U_R_Visitor_ID),
    concat(PV_R_PageView_ID,U_R_Visitor_ID,PV_d_RecordDate,G_R_TrackingID),
G_R_TrackingID, PV_d_RecordDate


    FROM 
standarddatapipeline.t2_108_pageviews;

-- --------------------------------------------------------

select count(*) from pageviews a JOIN pv_categorised b ON (a.visitor_ID=b.visitor_ID and a.pageview_ID=b.pageview_ID and a.record_date=b.record_date and a.tracking_ID=b.tracking_ID);

select count(*) from pageviews a JOIN pv_categorised b ON (a.visitor_ID=b.visitor_ID and a.pageview_ID=b.pageview_ID and a.record_date=b.record_date and a.tracking_ID=b.tracking_ID) 
 where a.record_date='2012-08-01' and b.record_date='2012-08-01' and a.tracking_ID='test' and b.tracking_ID='test';

select count(*) from pageviews a JOIN pv_categorised b on (a.full_composite_ID=b.full_composite_ID) where a.record_date='2012-08-01' and b.record_date='2012-08-01'  and a.tracking_ID='test' and b.tracking_ID='test';
