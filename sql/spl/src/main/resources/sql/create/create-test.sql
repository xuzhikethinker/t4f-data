SELECT * FROM COMPANY WHERE COMPANY_ID=10000;

SELECT COMPANY.FIRSTNAME FIRSTNAME,COMPANY.NAME NAME, MEMBER.MEMBER_ID MEMBERID, 
			COMMUNITY.COMMUNITY_ID COMMUNITYID, COMMUNITY.NAME COMMUNITYNAME, 
			COMMUNITY_TYPE.LABEL_KEY COMMUNITYTYPEKEY, COMPANY.COMPANY_ID COMPANYID 
		    FROM COMPANY,MEMBER,COMMUNITY,COMMUNITY_TYPE 
				WHERE COMPANY.LOGIN='eric.charles@mangate.be' 
					AND COMPANY.PASSWORD='eric.charles@mangate.be' 
					AND COMMUNITY.NAME='AOS' 
					AND COMMUNITY.COMMUNITY_TYPE_ID=COMMUNITY_TYPE.ID 
					AND COMPANY.COMPANY_ID=MEMBER.COMPANY_ID 
					AND MEMBER.COMMUNITY_ID=COMMUNITY.COMMUNITY_ID;
