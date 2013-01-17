select 
  visits, 
  sum(case when num_v_sites > 2 then 1 else 0 end), 
  sum(case when num_v_sites = 2 then 1 else 0 end), 
  sum(case when num_v_sites = 1 then 1 else 0 end), 
  sum(case when num_v_sites <= 0 then 1 else 0 end) 
from (
  select 
    v_id, 
    max(host) as visits, 
    count(distinct host) as num_v_sites 
  from (
    select 
      v_id as v_id, 
      case 
        when url RLIKE '.*url2\.com' then 1 
        when url RLIKE '.*url2\.com' then 0 
        else -1 end 
      as host, 
      qb_pv_r_url 
    from 
      table_name
    where 
      sit_di='sit_id' 
      and r_date='2013-01-16'
      and type=0 
  ) a 
  group by uvid
) b 
group by visits
;
