select 
  record_date, 
  count(record_date) 
from pv 
  left outer join (
    select 
      guid as guid, 
      record_date as record_date 
    from 
      pv 
    where 
      tracking_id = 'tid2'
  ) t 
  on 
    pv.guid = t.guid 
    and pv.record_date = t.record_date 
  where tracking_id 
    IN ('tid1','tid2') 
    and record_date > '2010-12-20' 
    and t.guid is null
  group by 
    record_date;
