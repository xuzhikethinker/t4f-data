alter 
  table table 
  add partition (
    site_id='name', 
    ddate='2013-01-11'
  ) 
  location 
    's3n://.../site_id=name/date=2013-01-11';
