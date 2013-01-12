ALTER TABLE MAIL_ACCOUNT
	ADD CONSTRAINT MAIL_ACCOUNT_MAIL_ACCOUNT_TYPE_FK FOREIGN KEY (
		MAIL_ACCOUNT_TYPE_SID
		)
	REFERENCES MAIL_ACCOUNT_TYPE (
		MAIL_ACCOUNT_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MAIL_ACCOUNT
	ADD CONSTRAINT MAIL_ACCOUNT_IDENTITY_FK FOREIGN KEY (
		IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ACCESS_CONTROL
	ADD CONSTRAINT ACCESS_CONTROL_ACTION_FK FOREIGN KEY (
		ACTION_SID
		)
	REFERENCES ACTION (
		ACTION_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ACCESS_CONTROL
	ADD CONSTRAINT ACCESS_CONTROL_ACCESS_CONTROL_TYPE_FK FOREIGN KEY (
		ACCESS_CONTROL_TYPE_SID
		)
	REFERENCES ACCESS_CONTROL_TYPE (
		ACCESS_CONTROL_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ACCESS_CONTROL
	ADD CONSTRAINT ACCESS_CONTROL_ITEM_BUNDLE_FK FOREIGN KEY (
		ITEM_BUNDLE_SID
		)
	REFERENCES ITEM_BUNDLE (
		ITEM_BUNDLE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE LINK
	ADD CONSTRAINT LINK_ITEM_FK FOREIGN KEY (
		ITEM_SID
		)
	REFERENCES ITEM (
		ITEM_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE LINK
	ADD CONSTRAINT LINK_JOB_FK FOREIGN KEY (
		JOB_SID
		)
	REFERENCES JOB (
		JOB_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE LINK
	ADD CONSTRAINT LINK_DIFFUSED_ITEM_FK FOREIGN KEY (
		DIFFUSED_ITEM_SID
		)
	REFERENCES DIFFUSED_ITEM (
		DIFFUSED_ITEM_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE LINK
	ADD CONSTRAINT LINK_PROJECT_FK FOREIGN KEY (
		PROJECT_SID
		)
	REFERENCES PROJECT (
		PROJECT_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE LINK
	ADD CONSTRAINT LINK_GROUP_FK FOREIGN KEY (
		GROUP_SID
		)
	REFERENCES "GROUP" (
		GROUP_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE LINK
	ADD CONSTRAINT LINK_DELIVERABLE_FK FOREIGN KEY (
		DELIVERABLE_SID
		)
	REFERENCES DELIVERABLE (
		DELIVERABLE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE LINK
	ADD CONSTRAINT LINK_ITEM_BUNDLE_FK FOREIGN KEY (
		ITEM_BUNDLE_SID
		)
	REFERENCES ITEM_BUNDLE (
		ITEM_BUNDLE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE LINK
	ADD CONSTRAINT LINK_DIFFUSION_FK FOREIGN KEY (
		DIFFUSION_SID
		)
	REFERENCES DIFFUSION (
		DIFFUSION_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE LINK
	ADD CONSTRAINT LINK_IDENTITY_FK FOREIGN KEY (
		IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE LINK
	ADD CONSTRAINT LINK_MEMBER_FK FOREIGN KEY (
		MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE LINK
	ADD CONSTRAINT LINK_MAIL_FK FOREIGN KEY (
		MAIL_SID
		)
	REFERENCES MAIL (
		MAIL_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE LINK
	ADD CONSTRAINT LINK_COMMUNITY_FK FOREIGN KEY (
		COMMUNITY_SID
		)
	REFERENCES COMMUNITY (
		COMMUNITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE LINK
	ADD CONSTRAINT LINK_SCHEDULE_FK FOREIGN KEY (
		SCHEDULE_SID
		)
	REFERENCES SCHEDULE (
		SCHEDULE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SCHEDULE_RESOURCE_ASSIG
	ADD CONSTRAINT SCHEDULE_RESOURCE_ASSIG_SCHEDULE_FK FOREIGN KEY (
		SCHEDULE_SID
		)
	REFERENCES SCHEDULE (
		SCHEDULE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SCHEDULE_RESOURCE_ASSIG
	ADD CONSTRAINT SCHEDULE_RESOURCE_ASSIG_RESOURCE_FK FOREIGN KEY (
		RESOURCE_SID
		)
	REFERENCES RESOURCE (
		RESOURCE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE TARGET
	ADD CONSTRAINT TARGET_PROJECT_FK FOREIGN KEY (
		PROJECT_SID
		)
	REFERENCES PROJECT (
		PROJECT_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE DELIVERABLE
	ADD CONSTRAINT DELIVERABLE_PROJECT_FK FOREIGN KEY (
		PROJECT_SID
		)
	REFERENCES PROJECT (
		PROJECT_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SKIN_TYPE
	ADD CONSTRAINT SKIN_TYPE_ITEM_BUNDLE_TYPE_FK FOREIGN KEY (
		ITEM_BUNDLE_TYPE_SID
		)
	REFERENCES ITEM_BUNDLE_TYPE (
		ITEM_BUNDLE_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE GLOSSARY
	ADD CONSTRAINT GLOSSARY_GLOSSARY_TYPE_FK FOREIGN KEY (
		GLOSSARY_TYPE_SID
		)
	REFERENCES GLOSSARY_TYPE (
		GLOSSARY_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE GLOSSARY
	ADD CONSTRAINT GLOSSARY_MEMBER_FK FOREIGN KEY (
		MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_BUNDLE_ASS
	ADD CONSTRAINT ITEM_BUNDLE_ASS_ITEM_BUNDLE_FK FOREIGN KEY (
		ITEM_BUNDLE_SID
		)
	REFERENCES ITEM_BUNDLE (
		ITEM_BUNDLE_SID
		)
	ON DELETE CASCADE
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_BUNDLE_ASS
	ADD CONSTRAINT ITEM_BUNDLE_ASS_ITEM_FK FOREIGN KEY (
		ITEM_SID
		)
	REFERENCES ITEM (
		ITEM_SID
		)
	ON DELETE CASCADE
	ON UPDATE NO ACTION
;

ALTER TABLE URL_PROPERTY
	ADD CONSTRAINT URL_PROPERTY_URL_FK FOREIGN KEY (
		URL_SID
		)
	REFERENCES URL (
		URL_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ASSET
	ADD CONSTRAINT ASSET_ITEM_FK FOREIGN KEY (
		ITEM_SID
		)
	REFERENCES ITEM (
		ITEM_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ASSET
	ADD CONSTRAINT ASSET_ASSET_ADDRESS_FK FOREIGN KEY (
		ASSET_ADDRESS_SID
		)
	REFERENCES ASSET_ADDRESS (
		ASSET_ADDRESS_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ASSET
	ADD CONSTRAINT ASS_ASSET_TYPE_FK FOREIGN KEY (
		ASSET_TYPE_SID
		)
	REFERENCES ASSET_TYPE (
		ASSET_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ASSET
	ADD CONSTRAINT ASSET_LANGUAGE_FK FOREIGN KEY (
		LANGUAGE_SID
		)
	REFERENCES LANGUAGE (
		LANGUAGE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE URL
	ADD CONSTRAINT URL_COMMUNITY_FK FOREIGN KEY (
		COMMUNITY_SID
		)
	REFERENCES COMMUNITY (
		COMMUNITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE URL
	ADD CONSTRAINT URL_URL_TYPE_FK FOREIGN KEY (
		URL_TYPE_SID
		)
	REFERENCES URL_TYPE (
		URL_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE URL
	ADD CONSTRAINT URL_IDENTITY_FK FOREIGN KEY (
		IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE URL
	ADD CONSTRAINT URL_GROUP_FK FOREIGN KEY (
		GROUP_SID
		)
	REFERENCES "GROUP" (
		GROUP_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE URL
	ADD CONSTRAINT URL_MEMBER_FK FOREIGN KEY (
		MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE URL
	ADD CONSTRAINT URL_ITEM_BUNDLE_FK FOREIGN KEY (
		ITEM_BUNDLE_SID
		)
	REFERENCES ITEM_BUNDLE (
		ITEM_BUNDLE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE URL
	ADD CONSTRAINT URL_PROJECT_FK FOREIGN KEY (
		PROJECT_SID
		)
	REFERENCES PROJECT (
		PROJECT_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_TYPE_TRANSF
	ADD CONSTRAINT ITEM_TYPE_TRANSF__ITEM_TYPE_TRANSF_FROM_ITEM_TYPE_SID FOREIGN KEY (
		ITEM_TYPE_TRANSF_FROM_ITEM_TYPE_SID
		)
	REFERENCES ITEM_TYPE (
		ITEM_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_TYPE_TRANSF
	ADD CONSTRAINT ITEM_TYPE_TRANSF_ITEM_TYPE_TRANSF_TO_ITEM_TYPE_FK FOREIGN KEY (
		ITEM_TYPE_TRANSF_TO_ITEM_TYPE_SID
		)
	REFERENCES ITEM_TYPE (
		ITEM_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE COMMUNITY_SKIN
	ADD CONSTRAINT COMMUNITY_SKIN_SKIN_FK FOREIGN KEY (
		SKIN_SID
		)
	REFERENCES SKIN (
		SKIN_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE COMMUNITY_SKIN
	ADD CONSTRAINT COMMUNITY_SKIN_COMMUNITY_FK FOREIGN KEY (
		COMMUNITY_SID
		)
	REFERENCES COMMUNITY (
		COMMUNITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE IDENTITY_LANGUAGE
	ADD CONSTRAINT IDENTITY_LANGUAGE_IDENTITY_FK FOREIGN KEY (
		IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE IDENTITY_LANGUAGE
	ADD CONSTRAINT IDENTITY_LANGUAGE_LANGUAGE_FK FOREIGN KEY (
		LANGUAGE_SID
		)
	REFERENCES LANGUAGE (
		LANGUAGE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MAIL
	ADD CONSTRAINT MAIL_IDENTITY_FK FOREIGN KEY (
		IDENTITY_OWNER_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_I18N
	ADD CONSTRAINT ITEM_I18N_ITEM_FK FOREIGN KEY (
		ITEM_SID
		)
	REFERENCES ITEM (
		ITEM_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_I18N
	ADD CONSTRAINT ITEM_I18N_LANGUAGE_FK FOREIGN KEY (
		LANGUAGE_SID
		)
	REFERENCES LANGUAGE (
		LANGUAGE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MAIL_IDENTITY
	ADD CONSTRAINT MAIL_IDENTITY_MAIL_FK FOREIGN KEY (
		MAIL_IDENTITY_TYPE_SID
		)
	REFERENCES MAIL_IDENTITY_TYPE (
		MAIL_IDENTITY_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MAIL_IDENTITY
	ADD CONSTRAINT MAIL_IDENTITY_MAIL_IDENTITY_TYPE_FK FOREIGN KEY (
		MAIL_SID
		)
	REFERENCES MAIL (
		MAIL_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MAIL_IDENTITY
	ADD CONSTRAINT MAIL_IDENTITY_IDENTITY_FK FOREIGN KEY (
		IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ASSET_PHOTO
	ADD CONSTRAINT ASSET_PHOTO_ASSET_FK FOREIGN KEY (
		ASSET_SID
		)
	REFERENCES ASSET (
		ASSET_SID
		)
	ON DELETE CASCADE
	ON UPDATE NO ACTION
;

ALTER TABLE PROJECT_MEMBER
	ADD CONSTRAINT PROJECT_MEMBER_MEMBER_FK FOREIGN KEY (
		MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE PROJECT_MEMBER
	ADD CONSTRAINT PROJECT_MEMBER_PROJECT_FK FOREIGN KEY (
		PROJECT_SID
		)
	REFERENCES PROJECT (
		PROJECT_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE PROJECT_MEMBER
	ADD CONSTRAINT PROJECT_MEMBER_ROLE_FK FOREIGN KEY (
		ROLE_SID
		)
	REFERENCES ROLE (
		ROLE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE THREAD
	ADD CONSTRAINT THREAD_THREAD_TYPE_FK FOREIGN KEY (
		THREAD_TYPE_SID
		)
	REFERENCES THREAD_TYPE (
		THREAD_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE THREAD
	ADD CONSTRAINT THREAD_IDENTITY_FK FOREIGN KEY (
		IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE TARGET_COMMENT
	ADD CONSTRAINT TARGET_COMMENT_TARGET_FK FOREIGN KEY (
		TARGET_SID
		)
	REFERENCES TARGET (
		TARGET_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_BUNDLE
	ADD CONSTRAINT ITEM_BUNDLE_IDENTITY_FK FOREIGN KEY (
		IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_BUNDLE
	ADD CONSTRAINT ITEM_BUNDLE_PROJECT_FK FOREIGN KEY (
		PROJECT_SID
		)
	REFERENCES PROJECT (
		PROJECT_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_BUNDLE
	ADD CONSTRAINT ITEM_BUNDLE_SKIN_FK FOREIGN KEY (
		SKIN_SID
		)
	REFERENCES SKIN (
		SKIN_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_BUNDLE
	ADD CONSTRAINT ITEM_BUNDLE_GROUP_FK FOREIGN KEY (
		GROUP_SID
		)
	REFERENCES "GROUP" (
		GROUP_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_BUNDLE
	ADD CONSTRAINT ITEM_BUNDLE_MEMBER_FK FOREIGN KEY (
		MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_BUNDLE
	ADD CONSTRAINT ITEM_BUNDLE_ITEM_BUNDLE_ALIAS_FK FOREIGN KEY (
		ITEM_BUNDLE_ALIAS_SID
		)
	REFERENCES ITEM_BUNDLE (
		ITEM_BUNDLE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_BUNDLE
	ADD CONSTRAINT ITEM_BUNDLE_TYPE_FK FOREIGN KEY (
		ITEM_BUNDLE_TYPE_SID
		)
	REFERENCES ITEM_BUNDLE_TYPE (
		ITEM_BUNDLE_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE DELIVERABLE_COMMENT
	ADD CONSTRAINT DELIVERABLE_COMMENT_DELIVERABLE_FK FOREIGN KEY (
		DELIVERABLE_SID
		)
	REFERENCES DELIVERABLE (
		DELIVERABLE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MAIL_ATTACHMENT
	ADD CONSTRAINT MAI_Constraint1 FOREIGN KEY (
		MAIL_SID
		)
	REFERENCES MAIL (
		MAIL_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM
	ADD CONSTRAINT ITEM_MEMBER_FK FOREIGN KEY (
		MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM
	ADD CONSTRAINT ITEM_ITEM_PARENT_FK FOREIGN KEY (
		ITEM_PARENT_SID
		)
	REFERENCES ITEM (
		ITEM_SID
		)
	ON DELETE CASCADE
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM
	ADD CONSTRAINT ITEM_ITEM_BUNDLE_FK FOREIGN KEY (
		ITEM_BUNDLE_SID
		)
	REFERENCES ITEM_BUNDLE (
		ITEM_BUNDLE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM
	ADD CONSTRAINT ITEM_ITEM_TYPE_FK FOREIGN KEY (
		ITEM_TYPE_SID
		)
	REFERENCES ITEM_TYPE (
		ITEM_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SKIN
	ADD CONSTRAINT SKIN_SKIN_TYPE_FK FOREIGN KEY (
		SKIN_TYPE_SID
		)
	REFERENCES SKIN_TYPE (
		SKIN_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ASSET_VALUE
	ADD CONSTRAINT ASS_VALUE_ASSET_FK FOREIGN KEY (
		ASSET_SID
		)
	REFERENCES ASSET (
		ASSET_SID
		)
	ON DELETE CASCADE
	ON UPDATE NO ACTION
;

ALTER TABLE MAIL_THREAD
	ADD CONSTRAINT MAIL_THREAD_MAIL_FK FOREIGN KEY (
		MAIL_SID
		)
	REFERENCES MAIL (
		MAIL_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MAIL_THREAD
	ADD CONSTRAINT MAIL_THREAD_THREAD_FK FOREIGN KEY (
		THREAD_SID
		)
	REFERENCES THREAD (
		THREAD_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER_ROLE
	ADD CONSTRAINT MEMBER_ROLE_MEMBER_FK FOREIGN KEY (
		MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER_ROLE
	ADD CONSTRAINT MEMBER_ROLE_ROLE_FK FOREIGN KEY (
		ROLE_SID
		)
	REFERENCES ROLE (
		ROLE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_BUNDLE_I18N
	ADD CONSTRAINT ITEM_BUNDLE_I18N_ITEM_BUNDLE_FK FOREIGN KEY (
		ITEM_BUNDLE_SID
		)
	REFERENCES ITEM_BUNDLE (
		ITEM_BUNDLE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_BUNDLE_I18N
	ADD CONSTRAINT ITEM_BUNDLE_I18N_LANGUAGE_FK FOREIGN KEY (
		LANGUAGE_SID
		)
	REFERENCES LANGUAGE (
		LANGUAGE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_TYPE
	ADD CONSTRAINT ITEM_TYPE_DIFFUSED_ITEM_TYPE_FK FOREIGN KEY (
		DIFFUSED_ITEM_TYPE_SID
		)
	REFERENCES DIFFUSED_ITEM_TYPE (
		DIFFUSED_ITEM_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT_VALUE
	ADD CONSTRAINT EVENT_VALUE_EVENT_FK FOREIGN KEY (
		EVENT_SID
		)
	REFERENCES EVENT (
		EVENT_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE JOB_HISTORY
	ADD CONSTRAINT JOB_HISTORY_JOB_FK FOREIGN KEY (
		JOB_SID
		)
	REFERENCES JOB (
		JOB_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE GROUP_MEMBER
	ADD CONSTRAINT GROUP_MEMBER_GROUP_FK FOREIGN KEY (
		GROUP_SID
		)
	REFERENCES "GROUP" (
		GROUP_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE GROUP_MEMBER
	ADD CONSTRAINT GROUP_MEMBER_MEMBER_FK FOREIGN KEY (
		MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE GROUP_MEMBER
	ADD CONSTRAINT GROUP_MEMBER_ROLE_FK FOREIGN KEY (
		ROLE_SID
		)
	REFERENCES ROLE (
		ROLE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE PERMISSION
	ADD CONSTRAINT PERMISSION_ROLE_FK FOREIGN KEY (
		ROLE_SID
		)
	REFERENCES ROLE (
		ROLE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE PERMISSION
	ADD CONSTRAINT PERMISSION_ACTION_FK FOREIGN KEY (
		ACTION_SID
		)
	REFERENCES ACTION (
		ACTION_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SCHEDULE_INFO
	ADD CONSTRAINT SCHEDULE_INFO_SCHEDULE_FK FOREIGN KEY (
		SCHEDULE_SID
		)
	REFERENCES SCHEDULE (
		SCHEDULE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE DIALOG
	ADD CONSTRAINT DIALOG_TO_SUBSCRIPTION_FK FOREIGN KEY (
		TO_SUBSCRIPTION_SID
		)
	REFERENCES SUBSCRIPTION (
		SUBSCRIPTION_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE DIALOG
	ADD CONSTRAINT DIA_Constraint2 FOREIGN KEY (
		DIALOG_STATUS_SID
		)
	REFERENCES DIALOG_STATUS (
		DIALOG_STATUS_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE DIALOG
	ADD CONSTRAINT DIA_Constraint1 FOREIGN KEY (
		DIALOG_TYPE_SID
		)
	REFERENCES DIALOG_TYPE (
		DIALOG_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE DIALOG
	ADD CONSTRAINT DIALOG_FROM_SUBSCRIPTION_FK FOREIGN KEY (
		FROM_SUBSCRIPTION_SID
		)
	REFERENCES SUBSCRIPTION (
		SUBSCRIPTION_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE IDENTITY_EVENT
	ADD CONSTRAINT IDENTITY_EVENT_IDENTITY_FK FOREIGN KEY (
		IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE IDENTITY_EVENT
	ADD CONSTRAINT IDENTITY_EVENT_EVENT_TYPE_FK FOREIGN KEY (
		EVENT_TYPE_SID
		)
	REFERENCES EVENT_TYPE (
		EVENT_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE INVOICE_ASSET
	ADD CONSTRAINT INVOICE_ASSET_INVOICE_ACCOUNT_FK FOREIGN KEY (
		INVOICE_ACCOUNT_SID
		)
	REFERENCES INVOICE_ACCOUNT (
		INVOICE_ACCOUNT_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SCHEDULE
	ADD CONSTRAINT SCHEDULE_PROJECT_FK FOREIGN KEY (
		PROJECT_SID
		)
	REFERENCES PROJECT (
		PROJECT_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SCHEDULE
	ADD CONSTRAINT SCHEDULE_PARENT_SCHEDULE_FK FOREIGN KEY (
		SCHEDULE_PARENT_SCHEDULE_SID
		)
	REFERENCES SCHEDULE (
		SCHEDULE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SCHEDULE
	ADD CONSTRAINT SCHEDULE_ORGANIZER_FK FOREIGN KEY (
		ORGANIZER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SCHEDULE
	ADD CONSTRAINT SCHEDULE_JOB_FK FOREIGN KEY (
		JOB_SID
		)
	REFERENCES JOB (
		JOB_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SCHEDULE
	ADD CONSTRAINT SCHEDULE_PLACE_RESOURCE_FK FOREIGN KEY (
		PLACE_RESOURCE_SID
		)
	REFERENCES RESOURCE (
		RESOURCE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SCHEDULE
	ADD CONSTRAINT SCHEDULE_PLACE_FK FOREIGN KEY (
		PLACE_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SCHEDULE
	ADD CONSTRAINT SCHEDULE_SCHEDULE_TYPE_FK FOREIGN KEY (
		SCHEDULE_TYPE_SID
		)
	REFERENCES SCHEDULE_TYPE (
		SCHEDULE_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER_ASSOCIATION
	ADD CONSTRAINT MEMBER_ASSOCIATION_MEMBER_FK FOREIGN KEY (
		MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER_ASSOCIATION
	ADD CONSTRAINT MEMBER_ASSOCIATION_MEMBER_ASSOCIATION_SUB_MEMBER_FK FOREIGN KEY (
		MEMBER_ASSOCIATION_SUB_MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER_ASSOCIATION
	ADD CONSTRAINT MEMBER_ASSOCIATION_MEMBER_ASSOCIATION_TYPE_FK FOREIGN KEY (
		MEMBER_ASSOCIATION_TYPE_SID
		)
	REFERENCES MEMBER_ASSOCIATION_TYPE (
		MEMBER_ASSOCIATION_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE RESOURCE
	ADD CONSTRAINT RESOURCE_COMMUNITY_FK FOREIGN KEY (
		COMMUNITY_SID
		)
	REFERENCES COMMUNITY (
		COMMUNITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE INVOICE_ACCOUNTING
	ADD CONSTRAINT INVOICE_ACCOUNTING_INVOICE_ACTION_FK FOREIGN KEY (
		INVOICE_ACTION_SID
		)
	REFERENCES INVOICE_ACTION (
		INVOICE_ACTION_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE INVOICE_ACCOUNTING
	ADD CONSTRAINT INVOICE_ACCOUNTING_INVOICE_ACCOUNT_FK FOREIGN KEY (
		INVOICE_ACCOUNT_SID
		)
	REFERENCES INVOICE_ACCOUNT (
		INVOICE_ACCOUNT_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE DIALOG_COMMENT
	ADD CONSTRAINT DIALOG_DIALOG_COMMENT_FK FOREIGN KEY (
		DIALOG_SID
		)
	REFERENCES DIALOG (
		DIALOG_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE DIFFUSION
	ADD CONSTRAINT DIFFUSION_LANGUAGE_FK FOREIGN KEY (
		LANGUAGE_SID
		)
	REFERENCES LANGUAGE (
		LANGUAGE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE DIFFUSION
	ADD CONSTRAINT DIFFUSION_MEMBER_FK FOREIGN KEY (
		MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER_EVENT
	ADD CONSTRAINT MEMBER_EVENT_EVENT_TYPE_FK FOREIGN KEY (
		EVENT_TYPE_SID
		)
	REFERENCES EVENT_TYPE (
		EVENT_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER_EVENT
	ADD CONSTRAINT MEMBER_EVENT_LOCATION_SOURCE_FK FOREIGN KEY (
		MEMBER_EVENT_LOCATION_SOURCE_SID
		)
	REFERENCES LOCATION_SOURCE (
		LOCATION_SOURCE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER_EVENT
	ADD CONSTRAINT MEMBER_EVENT_LOCALIZED_MEMBER_FK FOREIGN KEY (
		MEMBER_EVENT_LOCATION_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER_EVENT
	ADD CONSTRAINT MEMBER_EVENT_MEMBER_FK FOREIGN KEY (
		MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER_EVENT
	ADD CONSTRAINT MEMBER_EVENT_LOCATION_ACCURACY_FK FOREIGN KEY (
		MEMBER_EVENT_LOCATION_ACCURACY_SID
		)
	REFERENCES LOCATION_ACCURACY (
		LOCATION_ACCURACY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE INVOICE
	ADD CONSTRAINT INVOICE_INVOICE_TYPE_FK FOREIGN KEY (
		INVOICE_TYPE_SID
		)
	REFERENCES INVOICE_TYPE (
		INVOICE_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE INVOICE
	ADD CONSTRAINT INVOICE_DEBITOR_MEMBER_FK FOREIGN KEY (
		DEBITOR_MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE INVOICE
	ADD CONSTRAINT INVOICE_CREDITOR_MEMBER_FK FOREIGN KEY (
		CREDITOR_MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE INVOICE
	ADD CONSTRAINT INVOICE_PROJECT_FK FOREIGN KEY (
		PROJECT_SID
		)
	REFERENCES PROJECT (
		PROJECT_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SCHEDULE_PARTICIPANT
	ADD CONSTRAINT SCHEDULE_PARTICIPANT_MEMBER_FK FOREIGN KEY (
		MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SCHEDULE_PARTICIPANT
	ADD CONSTRAINT SCHEDULE_PARTICIPANT_SCHEDULE_FK FOREIGN KEY (
		SCHEDULE_SID
		)
	REFERENCES SCHEDULE (
		SCHEDULE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SUBSCRIPTION
	ADD CONSTRAINT SUBSCRIPTION_GROUP_FK FOREIGN KEY (
		GROUP_SID
		)
	REFERENCES "GROUP" (
		GROUP_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SUBSCRIPTION
	ADD CONSTRAINT SUBSCRIPTION_ITEM_BUNDLE_FK FOREIGN KEY (
		ITEM_BUNDLE_SID
		)
	REFERENCES ITEM_BUNDLE (
		ITEM_BUNDLE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SUBSCRIPTION
	ADD CONSTRAINT SUBSCRIPTION_MEMBER_FK FOREIGN KEY (
		MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SUBSCRIPTION
	ADD CONSTRAINT SUBSCRIPTION_COMMUNITY_FK FOREIGN KEY (
		COMMUNITY_SID
		)
	REFERENCES COMMUNITY (
		COMMUNITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SUBSCRIPTION
	ADD CONSTRAINT SUBSCRIPTION_DRIVER_FK FOREIGN KEY (
		DRIVER_SID
		)
	REFERENCES DRIVER (
		DRIVER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SUBSCRIPTION
	ADD CONSTRAINT SUBSCRIPTION_PROJECT_FK FOREIGN KEY (
		PROJECT_SID
		)
	REFERENCES PROJECT (
		PROJECT_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SUBSCRIPTION_FILTER
	ADD CONSTRAINT SUBSCRIPTION_FILTER_SUBSCRIPTION_FK FOREIGN KEY (
		SUBSCRIPTION_SID
		)
	REFERENCES SUBSCRIPTION (
		SUBSCRIPTION_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE SUBSCRIPTION_FILTER
	ADD CONSTRAINT SUBSCRIPTION_FILTER_TAG__FK FOREIGN KEY (
		TAG_SID
		)
	REFERENCES TAG (
		TAG_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVENT_DIFFUSION_FK FOREIGN KEY (
		DIFFUSION_SID
		)
	REFERENCES DIFFUSION (
		DIFFUSION_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVENT_IDENTITY_FK FOREIGN KEY (
		IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVENT_GROUP_FK FOREIGN KEY (
		GROUP_SID
		)
	REFERENCES "GROUP" (
		GROUP_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVENT_DIFFUSED_ITEM_FK FOREIGN KEY (
		DIFFUSED_ITEM_SID
		)
	REFERENCES DIFFUSED_ITEM (
		DIFFUSED_ITEM_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVENT_MEMBER_FK FOREIGN KEY (
		MEMBER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVENT_ITEM_BUNDLE_FK FOREIGN KEY (
		ITEM_BUNDLE_SID
		)
	REFERENCES ITEM_BUNDLE (
		ITEM_BUNDLE_SID
		)
	ON DELETE CASCADE
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVENT_PROJECT_FK FOREIGN KEY (
		PROJECT_SID
		)
	REFERENCES PROJECT (
		PROJECT_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVENT_SCHEDULE_FK FOREIGN KEY (
		SCHEDULE_SID
		)
	REFERENCES SCHEDULE (
		SCHEDULE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVENT_EVENT_TYPE_FK FOREIGN KEY (
		EVENT_TYPE_SID
		)
	REFERENCES EVENT_TYPE (
		EVENT_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVENT_JOB_FK FOREIGN KEY (
		JOB_SID
		)
	REFERENCES JOB (
		JOB_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE EVENT
	ADD CONSTRAINT EVENT_ITEM_FK FOREIGN KEY (
		ITEM_SID
		)
	REFERENCES ITEM (
		ITEM_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE IDENTITY_VALUE
	ADD CONSTRAINT IDENTITY_VALUE_IDENTITY_FK FOREIGN KEY (
		IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_TAG
	ADD CONSTRAINT ITEM_TAG_TAG_FK FOREIGN KEY (
		TAG_SID
		)
	REFERENCES TAG (
		TAG_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ITEM_TAG
	ADD CONSTRAINT ITEM_TAG_ITEM_FK FOREIGN KEY (
		ITEM_SID
		)
	REFERENCES ITEM (
		ITEM_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE DIFFUSED_ITEM
	ADD CONSTRAINT DIFFUSED_ITEM_DIFFUSION_FK FOREIGN KEY (
		DIFFUSION_SID
		)
	REFERENCES DIFFUSION (
		DIFFUSION_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE DIFFUSED_ITEM
	ADD CONSTRAINT DIFFUSED_ITEM_DIFFUSED_ITEM_TYPE_FK FOREIGN KEY (
		DIFFUSED_ITEM_TYPE_SID
		)
	REFERENCES DIFFUSED_ITEM_TYPE (
		DIFFUSED_ITEM_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE DIFFUSED_ITEM
	ADD CONSTRAINT DIFFUSED_ITEM_SENDER_FK FOREIGN KEY (
		DIFFUSED_ITEM_SENDER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE DIFFUSED_ITEM
	ADD CONSTRAINT DIFFUSED_ITEM_ITEM_FK FOREIGN KEY (
		ITEM_SID
		)
	REFERENCES ITEM (
		ITEM_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE DIFFUSED_ITEM
	ADD CONSTRAINT DIFFUSED_ITEM_RECEIVER_FK FOREIGN KEY (
		DIFFUSED_ITEM_RECEIVER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER_FOLLOW
	ADD CONSTRAINT MEMBER_FOLLOW_MEMBER_FOLLOWED_FK FOREIGN KEY (
		MEMBER_FOLLOWED_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER_FOLLOW
	ADD CONSTRAINT MEMBER_FOLLOW_MEMBER_FOLLOWER_FK FOREIGN KEY (
		MEMBER_FOLLOWER_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE TELEPHONE
	ADD CONSTRAINT TELEPHONE_IDENTITY_FK FOREIGN KEY (
		IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER
	ADD CONSTRAINT MEMBER_IDENTITY_LOCATION_FK FOREIGN KEY (
		IDENTITY_LOCATION_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER
	ADD CONSTRAINT MEMBER_COMMUNITY_FK FOREIGN KEY (
		COMMUNITY_SID
		)
	REFERENCES COMMUNITY (
		COMMUNITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER
	ADD CONSTRAINT LOCATION_SOURCE_FK FOREIGN KEY (
		LOCATION_SOURCE_SID
		)
	REFERENCES LOCATION_SOURCE (
		LOCATION_SOURCE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER
	ADD CONSTRAINT LOCATION_ACCURACY_FK FOREIGN KEY (
		LOCATION_ACCURACY_SID
		)
	REFERENCES LOCATION_ACCURACY (
		LOCATION_ACCURACY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER
	ADD CONSTRAINT MEMBER_IDENTITY_FK FOREIGN KEY (
		IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER
	ADD CONSTRAINT MEMBER_MEMBER_STATUS_FK FOREIGN KEY (
		MEMBER_STATUS_SID
		)
	REFERENCES MEMBER_STATUS (
		MEMBER_STATUS_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER
	ADD CONSTRAINT MEMBER_MEMBER_LOCATION_FK FOREIGN KEY (
		MEMBER_LOCATION_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE MEMBER
	ADD CONSTRAINT MEMBER_MEMBER_ACCESS_TYPE_FK FOREIGN KEY (
		MEMBER_ACCESS_TYPE_SID
		)
	REFERENCES MEMBER_ACCESS_TYPE (
		MEMBER_ACCESS_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE IDENTITY_INFO
	ADD CONSTRAINT IDENTITY_INFO_IDENTITY_FK FOREIGN KEY (
		IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE JOB_ASSIGNMENT
	ADD CONSTRAINT JOB_ASSIGNMENT_JOB_ASSIGNMENT_PARENT_FK FOREIGN KEY (
		JOB_ASSIGNMENT_PARENT_SID
		)
	REFERENCES JOB (
		JOB_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE JOB_ASSIGNMENT
	ADD CONSTRAINT JOB_ASSIGNMENT_JOB_ASSIGNMENT_CHILD_FK FOREIGN KEY (
		JOB_ASSIGNMENT_CHILD_SID
		)
	REFERENCES JOB (
		JOB_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE COMMUNITY_EVENT
	ADD CONSTRAINT COMMUNITY_EVENT_TYPE_FK FOREIGN KEY (
		EVENT_TYPE_SID
		)
	REFERENCES EVENT_TYPE (
		EVENT_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE COMMUNITY_EVENT
	ADD CONSTRAINT COMMUNITY_EVENT_COMMUNITY_FK FOREIGN KEY (
		COMMUNITY_SID
		)
	REFERENCES COMMUNITY (
		COMMUNITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE INVOICE_ACTION
	ADD CONSTRAINT INVOICE_ACTION_INVOICE_FK FOREIGN KEY (
		INVOICE_SID
		)
	REFERENCES INVOICE (
		INVOICE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE "IDENTITY"
	ADD CONSTRAINT IDENTITY_COUNTRY_FK FOREIGN KEY (
		COUNTRY_SID
		)
	REFERENCES COUNTRY (
		COUNTRY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE "IDENTITY"
	ADD CONSTRAINT IDENTITY_TIMEZONE_FK FOREIGN KEY (
		TIMEZONE_SID
		)
	REFERENCES TIMEZONE (
		TIMEZONE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE "IDENTITY"
	ADD CONSTRAINT IDENTITY_LANGUAGE_FK FOREIGN KEY (
		LANGUAGE_SID
		)
	REFERENCES LANGUAGE (
		LANGUAGE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE "IDENTITY"
	ADD CONSTRAINT IDENTITY_IDENTITY_ACCESS_TYPE_FK FOREIGN KEY (
		IDENTITY_ACCESS_TYPE_SID
		)
	REFERENCES IDENTITY_ACCESS_TYPE (
		IDENTITY_ACCESS_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE "IDENTITY"
	ADD CONSTRAINT IDENTITY_IDENTITY_TYPE_FK FOREIGN KEY (
		IDENTITY_TYPE_SID
		)
	REFERENCES IDENTITY_TYPE (
		IDENTITY_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE PROJECT
	ADD CONSTRAINT PROJECT_COMMUNITY_FK FOREIGN KEY (
		COMMUNITY_SID
		)
	REFERENCES COMMUNITY (
		COMMUNITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE PROJECT
	ADD CONSTRAINT PROJECT_PROJECT_ACCESS_TYPE_FK FOREIGN KEY (
		PROJECT_ACCESS_TYPE_SID
		)
	REFERENCES PROJECT_ACCESS_TYPE (
		PROJECT_ACCESS_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE PROJECT
	ADD CONSTRAINT PROJECT_PROJECT_TYPE_FK FOREIGN KEY (
		PROJECT_TYPE_SID
		)
	REFERENCES PROJECT_TYPE (
		PROJECT_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE "GROUP"
	ADD CONSTRAINT GROUP_COMMUNITY_FK FOREIGN KEY (
		COMMUNITY_SID
		)
	REFERENCES COMMUNITY (
		COMMUNITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE "GROUP"
	ADD CONSTRAINT GROUP_GROUP_ACCESS_TYPE_FK FOREIGN KEY (
		GROUP_ACCESS_TYPE_SID
		)
	REFERENCES GROUP_ACCESS_TYPE (
		GROUP_ACCESS_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE "GROUP"
	ADD CONSTRAINT GROUP_GROUP_TYPE_FK FOREIGN KEY (
		GROUP_TYPE_SID
		)
	REFERENCES GROUP_TYPE (
		GROUP_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE RELATION
	ADD CONSTRAINT RELATION_IDENTITY_FK FOREIGN KEY (
		IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE RELATION
	ADD CONSTRAINT RELATION_RELATION_TYPE_FK FOREIGN KEY (
		RELATION_TYPE_SID
		)
	REFERENCES RELATION_TYPE (
		RELATION_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE RELATION
	ADD CONSTRAINT RELATION_RELATION_SUB_IDENTITY_FK FOREIGN KEY (
		RELATION_SUB_IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE JOB
	ADD CONSTRAINT JOB_JOB_EXECUTANT_FK FOREIGN KEY (
		JOB_EXECUTANT_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE JOB
	ADD CONSTRAINT JOB_JOB_PARENT_JOB_FK FOREIGN KEY (
		JOB_PARENT_JOB_SID
		)
	REFERENCES JOB (
		JOB_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE JOB
	ADD CONSTRAINT JOB_PROJECT_FK FOREIGN KEY (
		PROJECT_SID
		)
	REFERENCES PROJECT (
		PROJECT_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE JOB
	ADD CONSTRAINT JOB_JOB_CREATOR_FK FOREIGN KEY (
		JOB_CREATOR_SID
		)
	REFERENCES MEMBER (
		MEMBER_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE DRIVER
	ADD CONSTRAINT DRIVER_DRIVER_TYPE_FK FOREIGN KEY (
		DRIVER_TYPE_SID
		)
	REFERENCES DRIVER_TYPE (
		DRIVER_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE COMMUNITY
	ADD CONSTRAINT COMMUNITY_COMMUNITY_API_SID FOREIGN KEY (
		COMMUNITY_API_SID
		)
	REFERENCES COMMUNITY_API (
		COMMUNITY_API_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE COMMUNITY
	ADD CONSTRAINT COMMUNITY_COMMUNITY_ACCESS_TYPE_FK FOREIGN KEY (
		COMMUNITY_ACCESS_TYPE_SID
		)
	REFERENCES COMMUNITY_ACCESS_TYPE (
		COMMUNITY_ACCESS_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE COMMUNITY
	ADD CONSTRAINT COMMUNITY_COMMUNITY_TYPE_FK FOREIGN KEY (
		COMMUNITY_TYPE_SID
		)
	REFERENCES COMMUNITY_TYPE (
		COMMUNITY_TYPE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE COMMUNITY
	ADD CONSTRAINT COMMUNITY_SKIN_FK FOREIGN KEY (
		SKIN_SID
		)
	REFERENCES SKIN (
		SKIN_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE PROJECT_INFO
	ADD CONSTRAINT PROJECT_INFO_PROJECT_FK FOREIGN KEY (
		PROJECT_SID
		)
	REFERENCES PROJECT (
		PROJECT_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE ADDRESS
	ADD CONSTRAINT ADDRESS_IDENITY_FK FOREIGN KEY (
		IDENTITY_SID
		)
	REFERENCES "IDENTITY" (
		IDENTITY_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE JOB_RESOURCE_ASSIG
	ADD CONSTRAINT JOB_RESOURCE_ASSIG_JOB_FK FOREIGN KEY (
		JOB_SID
		)
	REFERENCES JOB (
		JOB_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;

ALTER TABLE JOB_RESOURCE_ASSIG
	ADD CONSTRAINT JOB_RESOURCE_ASSIG_RESOURCE_FK FOREIGN KEY (
		RESOURCE_SID
		)
	REFERENCES RESOURCE (
		RESOURCE_SID
		)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;