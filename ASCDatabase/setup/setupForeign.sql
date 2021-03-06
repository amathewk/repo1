ALTER TABLE "FURN"."COLLECTION_CATEGORY_MAP" 
	ADD CONSTRAINT "CC1231985973656" FOREIGN KEY
		("CATEGORY_ID")
	REFERENCES "FURN"."CATEGORY"
		("ID")
	ON DELETE RESTRICT
	ON UPDATE RESTRICT
	ENFORCED
	ENABLE QUERY OPTIMIZATION;

ALTER TABLE "FURN"."COLLECTION_CATEGORY_MAP" 
	ADD CONSTRAINT "CC1231985989734" FOREIGN KEY
		("COLLECTION_ID")
	REFERENCES "FURN"."COLLECTION"
		("ID")
	ON DELETE RESTRICT
	ON UPDATE RESTRICT
	ENFORCED
	ENABLE QUERY OPTIMIZATION;

ALTER TABLE "FURN"."ITEM_COLLECTION" 
	ADD CONSTRAINT "CC1231986768796" FOREIGN KEY
		("ITEM_ID")
	REFERENCES "FURN"."ITEM"
		("ID")
	ON DELETE RESTRICT
	ON UPDATE RESTRICT
	ENFORCED
	ENABLE QUERY OPTIMIZATION;

ALTER TABLE "FURN"."ITEM_COLLECTION" 
	ADD CONSTRAINT "CC1231986785875" FOREIGN KEY
		("COLLECTION_CATEGORY_ID")
	REFERENCES "FURN"."COLLECTION_CATEGORY_MAP"
		("ID")
	ON DELETE RESTRICT
	ON UPDATE RESTRICT
	ENFORCED
	ENABLE QUERY OPTIMIZATION;
	






