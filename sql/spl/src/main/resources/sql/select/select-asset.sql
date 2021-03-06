SELECT * FROM ASSET WHERE ASSET_PRICE >1000 AND ASSET_PRICE <200000000000;

SELECT * FROM ASSET,ADDRESS WHERE ASSET_PRICE >1000 AND ASSET_PRICE <2000000000000 
	AND ADDRESS.ADDRESS_ID = ASSET_ADDRESS_ID;

SELECT MAX(ASSET_PRICE)+1 FROM ASSET ; 

SELECT MIN(ASSET_PRICE) FROM ASSET ; 

SELECT * FROM ADDRESS,ASSET WHERE ADDRESS.ADDRESS_ID = ASSET_ADDRESS_ID  AND  UPPER(ADDRESS.ZIPCITY) LIKE UPPER('%MonS%');

SELECT * FROM ASSET,ADDRESS WHERE ASSET_PRICE >1000 AND ASSET_PRICE <10000000000
	AND ADDRESS.ADDRESS_ID = ASSET_ADDRESS_ID AND ASSET.ASSET_ADDRESS_ID IN
		(SELECT ADDRESS_ID FROM ADDRESS WHERE  UPPER(ADDRESS.ZIPCITY) LIKE UPPER('%MonS%'));;

SELECT * FROM ASSET,ADDRESS WHERE ASSET_PRICE >1000 AND ASSET_PRICE < 100000000
	AND ADDRESS.ADDRESS_ID = ASSET_ADDRESS_ID 
	AND ASSET.ASSET_ADDRESS_ID IN
		(SELECT ADDRESS_ID FROM ADDRESS WHERE  UPPER(ADDRESS.ZIPCITY) LIKE UPPER('%%'))
				AND ASSET.ASSET_SID IN (SELECT ASSET_SID FROM ITEM 
											WHERE ITEM.SID IN (SELECT ITEM_BUNDLE_ASS.ITEM_SID FROM ITEM_BUNDLE_ASS 
			 												WHERE ITEM_BUNDLE_SID =1));

SELECT ASSET.* ,ADDRESS.* FROM ASSET  ,ADDRESS  WHERE ASSET_PRICE >-1 AND ASSET_PRICE < 150001 AND ADDRESS.ADDRESS_ID = ASSET_ADDRESS_ID 
AND ASSET.ASSET_ADDRESS_ID IN
(SELECT ADDRESS_ID FROM ADDRESS WHERE  UPPER(ADDRESS.ZIPCITY) LIKE UPPER('%o%')) AND ASSET.ASSET_SID IN 
(SELECT ASSET_SID FROM ITEM WHERE ITEM.SID  IN (SELECT ITEM_BUNDLE_ASS.ITEM_SID FROM ITEM_BUNDLE_ASS 
			 WHERE ITEM_BUNDLE_SID =1));

SELECT * FROM ADDRESS WHERE  UPPER(ADDRESS.ZIPCITY) LIKE UPPER('%%') and address_id >921194;
