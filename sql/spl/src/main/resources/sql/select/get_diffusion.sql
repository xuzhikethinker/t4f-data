SELECT * FROM  DIFFUSION ;

SELECT * FROM ITEM_BUNDLE WHERE MEMBER_ID = 2 ;

SELECT * FROM ITEM_BUNDLE WHERE MEMBER_ID = 2 ;

SELECT EVENT.DIFFUSION_ID, DIFFUSION.MEMBER_ID, EVENT.EVENT_DATE, DIFFUSION.NAME,EVENT.SID FROM DIFFUSION, EVENT  
WHERE ITEM_BUNDLE_SID  IN (SELECT SID FROM ITEM_BUNDLE WHERE MEMBER_ID = 2 )AND EVENT_TYPE_ID = 6
AND EVENT.DIFFUSION_ID = DIFFUSION.ID ;

SELECT 
 EVENT.SID, EVENT.EVENT_TYPE_ID,
 ITEM_DIFFUSED.TOKEN,  
 ITEM_DIFFUSED.DIFFUSED_TYPE_SID,  
 ITEM_DIFFUSED.SENDER_ID, 
 ITEM_DIFFUSED.RECEIVER_ID ,  
 DIFFUSION.ID ,
 EVENT.EVENT_DATE, 
 DIFFUSION.NAME 
 FROM  EVENT,ITEM_DIFFUSED,DIFFUSION  
		  WHERE EVENT.DIFFUSION_ID= 10
		  AND EVENT.ITEM_DIFFUSED_SID = ITEM_DIFFUSED.SID  
		  AND DIFFUSION.ID=EVENT.DIFFUSION_ID  
		  AND EVENT.EVENT_TYPE_ID = 1
		    ORDER BY EVENT.EVENT_DATE ;
		    
SELECT * FROM EVENT_TYPE ;	

SELECT * FROM DIFFUSION ;

SELECT EVENT.SID, EVENT.EVENT_TYPE_ID,
 ITEM_DIFFUSED.TOKEN,  
 ITEM_DIFFUSED.DIFFUSED_TYPE_SID,  
 ITEM_DIFFUSED.SENDER_ID, 
 ITEM_DIFFUSED.RECEIVER_ID ,  
 EVENT.DIFFUSION_ID ,
 EVENT.EVENT_DATE, 
 DIFFUSION.NAME  
 FROM EVENT,ITEM_DIFFUSED,DIFFUSION   
 WHERE 
 EVENT.DIFFUSION_ID= DIFFUSION.ID
 
 AND EVENT.EVENT_TYPE_ID = 6
 AND EVENT.ITEM_BUNDLE_SID=2 
 
 ORDER BY EVENT.EVENT_DATE ;

SELECT * FROM  ITEM_DIFFUSED ;

SELECT * FROM  EVENT;

SELECT * FROM  ITEM_DIFFUSED WHERE  DIFFUSED_TYPE_SID = (SELECT SID FROM ITEM_DIFFUSED_TYPE WHERE  NAME= 'MAIL')  ;
	
SELECT COUNT(*) FROM  ITEM_DIFFUSED WHERE  DIFFUSED_TYPE_SID = (SELECT SID FROM ITEM_DIFFUSED_TYPE WHERE  NAME= 'MAIL')   
AND SID IN (SELECT * FROM EVENT WHERE DIFFUSION_ID=12 AND EVENT_TYPE_ID=7);

SELECT * FROM EVENT WHERE DIFFUSION_ID=12 AND EVENT_TYPE_ID=7 ;

SELECT COUNT(*) FROM EVENT WHERE EVENT_TYPE_ID = 7  AND DIFFUSION_ID= 12
			AND YEAR(EVENT_DATE)= 2006 AND DAY(EVENT_DATE)= 24 AND MONTH(EVENT_DATE)= 4 ;

SELECT * FROM  ITEM_DIFFUSED;
   