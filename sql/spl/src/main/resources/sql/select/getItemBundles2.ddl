SELECT * FROM ITEM_BUNDLE   ;
SELECT * FROM ITEM ;



SELECT ITEM_BUNDLE.*, 
			  ITEM_BUNDLE_TYPE.NAME   itemBundleTypeName,
			  ITEM_BUNDLE_TYPE.DESCRIPTION itemBundleTypeDescription, 
			  ITEM_BUNDLE_I18N.LANGUAGE_SID itemBundleI18NLanguageSid, 
			  ITEM_BUNDLE_I18N.KEYWORDS itemBundleI18NKeywords, 
			  ITEM_BUNDLE_I18N.MESSAGE itemBundleI18NMessage, 
			  ITEM_BUNDLE_I18N.TITLE itemBundleI18NTitle, 
			  ITEM_BUNDLE_I18N.SID_ITB_I18N itemBundleI18NSid, 
			  SKIN.NAME itemBundleSkinName , 
			  ITEM_BUNDLE_TYPE.NAME itemBundleTypeName, 
			  ITEM_BUNDLE_TYPE.DESCRIPTION itemBundleTypeDescription 
			  FROM ITEM_BUNDLE , ITEM_BUNDLE_TYPE, SKIN , ITEM_BUNDLE_I18N 
			  WHERE ITEM_BUNDLE_I18N.ITEM_BUNDLE_SID = ITEM_BUNDLE.SID 
			  AND ITEM_BUNDLE.SKIN_SID =  SKIN.SID 
			  AND ITEM_BUNDLE.ITEM_BUNDLE_TY_SID =  ITEM_BUNDLE_TYPE.SID
			  
			  AND ITEM_BUNDLE.SERVER_NAME ='www.angi.be' 
			
			  ;