SELECT COUNT(*) ITEM_EVENT_VIEW_BY_ISABEL_BE FROM EVENT 
	WHERE EVENT_TYPE_ID = 13 
		AND ITEM_DIFFUSED_SID 
			IN ( SELECT SID FROM ITEM_DIFFUSED WHERE RECEIVER_ID 
					IN (SELECT MEMBER_ID FROM MEMBER 
							WHERE COMPANY_ID 
								IN (SELECT COMPANY_ID FROM COMPANY 
										WHERE EMAIL LIKE '%isabel.be%' ) 
										OR COMPANY_ID 
											IN( SELECT COMPANY_ID FROM COMPANY_VALUE 
													WHERE VALUE_STRING LIKE '%isabel.be%'
												)
									)
							);
