TRUNCATE TABLE DEV_DEVICE;

INSERT into DEV_DEVICE(DEVICE_ID, APP_ID, DEVICE_TYPE, DESCRIPTION,
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_PRICE_ZONE, TAG_APP_PROFILE, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, BUSINESS_UNIT_ID)
VALUES ('09999', 'server', 'SERVER', 'Store 09999 Server',
    '*', '*', '*', '*', '*', '*', '*', '*', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test', '00145');

INSERT into DEV_DEVICE(DEVICE_ID, APP_ID, DEVICE_TYPE, DESCRIPTION,
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_PRICE_ZONE, TAG_APP_PROFILE, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, BUSINESS_UNIT_ID)
VALUES ('00145-001', 'pos', 'WORKSTATION', 'Store 00145 Register 1',
    'N_AMERICA', 'US', 'OH', '100', 'REGULAR', 'WORKSTATION', 'Metl', 'POS', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test', '00145');    

INSERT into DEV_DEVICE(DEVICE_ID, APP_ID, DEVICE_TYPE, DESCRIPTION,
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_PRICE_ZONE, TAG_APP_PROFILE, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, BUSINESS_UNIT_ID, TIMEZONE_OFFSET)
VALUES ('00100-001', 'pos', 'WORKSTATION', 'Store 100 Register 1',
    'N_AMERICA', 'US', 'OH', '100', 'REGULAR', 'WORKSTATION', 'Metl', 'POS', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test', '100', '-4:00');    

INSERT into DEV_DEVICE(DEVICE_ID, APP_ID, DEVICE_TYPE, DESCRIPTION,
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_PRICE_ZONE, TAG_APP_PROFILE, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, BUSINESS_UNIT_ID, TIMEZONE_OFFSET)
VALUES ('00500-001', 'pos', 'WORKSTATION', 'Store 500 Register 1',
    'N_AMERICA', 'US', 'CA', '500', 'POPUP', 'WORKSTATION', 'Metl', 'POS', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test', '500', '-4:00');        
INSERT into DEV_DEVICE(DEVICE_ID, APP_ID, DEVICE_TYPE, DESCRIPTION,
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_PRICE_ZONE, TAG_APP_PROFILE, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, BUSINESS_UNIT_ID, TIMEZONE_OFFSET)
VALUES ('00500-002', 'pos', 'WORKSTATION', 'Store 500 Register 2',
    'N_AMERICA', 'US', 'CA', '500', 'POPUP', 'WORKSTATION', 'Metl', 'POS', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test', '500', '-4:00');            

INSERT into DEV_DEVICE(DEVICE_ID, APP_ID, DEVICE_TYPE, DESCRIPTION,
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_PRICE_ZONE, TAG_APP_PROFILE, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, BUSINESS_UNIT_ID, TIMEZONE_OFFSET)
VALUES ('00900-010', 'pos', 'WORKSTATION', 'Store 900 Register 10',
    'EAST_AFRICA', 'UG', '', '900', 'REGULAR', 'MOBILE_POS', 'Metl', 'POS', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test', '900', '-4:00');        
    
INSERT into DEV_DEVICE(DEVICE_ID, APP_ID, DEVICE_TYPE, DESCRIPTION,
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_PRICE_ZONE, TAG_APP_PROFILE, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, BUSINESS_UNIT_ID, TIMEZONE_OFFSET)
VALUES ('00039-011', 'pos', 'WORKSTATION', 'Store 00039 Register 11',
    'EAST_AFRICA', 'UG', '', '00039', 'REGULAR', 'WORKSTATION', 'Metl', 'POS', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test', '00039', '-4:00');        
    
INSERT into DEV_DEVICE(DEVICE_ID, APP_ID, DEVICE_TYPE, DESCRIPTION,
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_PRICE_ZONE, TAG_APP_PROFILE, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, BUSINESS_UNIT_ID, TIMEZONE_OFFSET)
VALUES ('05243-013', 'pos', 'WORKSTATION', 'Store 00039 Register 11',
    'EAST_AFRICA', 'UG', '', '00039', 'REGULAR', 'WORKSTATION', 'Metl', 'POS', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test', '05243', '-4:00');  

INSERT into DEV_DEVICE(DEVICE_ID, APP_ID, DEVICE_TYPE, DESCRIPTION,
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_PRICE_ZONE, TAG_APP_PROFILE, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, BUSINESS_UNIT_ID, TIMEZONE_OFFSET)
VALUES ('00418-002', 'pos', 'WORKSTATION', 'Store 481 Register 2',
    'N_AMERICA', 'US', 'OH', '100', 'REGULAR', 'WORKSTATION', 'Metl', 'POS', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test', '00418', '-4:00');

INSERT into DEV_DEVICE(DEVICE_ID, APP_ID, DEVICE_TYPE, DESCRIPTION,
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_PRICE_ZONE, TAG_APP_PROFILE, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, BUSINESS_UNIT_ID, TIMEZONE_OFFSET)
VALUES ('11111-111', 'server', 'WORKSTATION', 'Store 481 Register 2',
    'N_AMERICA', 'US', 'OH', '100', 'REGULAR', 'WORKSTATION', 'Metl', 'POS', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test', '09999', '-4:00');

TRUNCATE TABLE DEV_DEVICE_AUTH;

INSERT into DEV_DEVICE_AUTH(DEVICE_ID, APP_ID, AUTH_TOKEN, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY)
VALUES ('00145-001', 'pos', '123456789', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test');