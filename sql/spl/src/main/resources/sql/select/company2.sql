SELECT * FROM COMPANY WHERE EMAIL LIKE '%angi%';
SELECT * FROM MEMBER ;

SELECT * FROM EVENT WHERE SID>150 ;




SELECT MEMBER.MEMBER_ID,COMPANY.COMPANY_ID,COMPANY.EMAIL EMAIL FROM COMPANY,MEMBER 


WHERE MEMBER.COMPANY_ID IN ( SELECT COMPANY_ID FROM COMPANY WHERE EMAIL LIKE '%u-mang%' AND IS_PERSON=1 ) AND MEMBER.COMPANY_ID = COMPANY.COMPANY_ID ;




SELECT MEMBER.MEMBER_ID MEMBER_ID 	, 
		MEMBER.COMMUNITY_ID COMMUNITY_ID , 
		COMPANY.EMAIL EMAIL	, 
		COMPANY.FIRSTNAME FIRSTNAME	,
		COMPANY.NAME LASTNAME FROM COMPANY,MEMBER	 
		WHERE MEMBER.COMPANY_ID 
		IN  ( SELECT COMPANY_ID FROM COMPANY WHERE EMAIL LIKE '%u-mang%' AND IS_PERSON=1 ) AND MEMBER.COMPANY_ID = COMPANY.COMPANY_ID ;
		
		

SELECT COMPANY.* FROM COMPANY WHERE COMPANY_ID IN (SELECT MEMBER.COMPANY_ID  FROM MEMBER WHERE MEMBER.COMMUNITY_ID = 1 AND MEMBER.IS_ACTIVE=1 AND IS_REJECTED=0 ) ;

 		