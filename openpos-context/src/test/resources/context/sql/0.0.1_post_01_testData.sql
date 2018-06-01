
INSERT into CTX_NODE(NODE_ID, NODE_TYPE, DESCRIPTION, ADDRESS1, CITY, NODE_STATE, POSTAL_CODE, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY)
VALUES ('100-1', 'WORKSTATION', 'Store 100 Register 1', '4100 Regent St', 'Columbus', 'OH', '43219',
    'N_AMERICA', 'US', 'OH', '100', 'REGULAR', 'WORKSTATION', 'Metl', 'POS', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test');    

INSERT into CTX_NODE(NODE_ID, NODE_TYPE, DESCRIPTION, ADDRESS1, CITY, NODE_STATE, POSTAL_CODE, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY)
VALUES ('500-1', 'WORKSTATION', 'Store 500 Register 1', '1600 Amphitheatre Parkway', 'Mountain View', 'CA', '94043',
    'N_AMERICA', 'US', 'CA', '500', 'POPUP', 'WORKSTATION', 'Metl', 'POS', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test');        
INSERT into CTX_NODE(NODE_ID, NODE_TYPE, DESCRIPTION, ADDRESS1, CITY, NODE_STATE, POSTAL_CODE, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY)
VALUES ('500-2', 'WORKSTATION', 'Store 500 Register 2', '1600 Amphitheatre Parkway', 'Mountain View', 'CA', '94043',
    'N_AMERICA', 'US', 'CA', '500', 'POPUP', 'WORKSTATION', 'Metl', 'POS', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test');            

INSERT into CTX_NODE(NODE_ID, NODE_TYPE, DESCRIPTION, ADDRESS1, CITY, NODE_STATE, POSTAL_CODE, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY)
VALUES ('900-10', 'WORKSTATION', 'Store 900 Register 10', 'Plot 1577 Ggaba Road', 'Kampala', null, '94043',
    'EAST_AFRICA', 'UG', '', '900', 'REGULAR', 'MOBILE_POS', 'Metl', 'POS', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test');        
    
INSERT into CTX_NODE(NODE_ID, NODE_TYPE, DESCRIPTION, ADDRESS1, CITY, NODE_STATE, POSTAL_CODE, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY)
VALUES ('00039-011', 'WORKSTATION', 'Store 00039 Register 11', '', '', null, '',
    'EAST_AFRICA', 'UG', '', '00039', 'REGULAR', 'WORKSTATION', 'Metl', 'POS', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test');        


TRUNCATE TABLE CTX_CONFIG;

INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('pos.welcome.text', {ts '2000-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            '*', '*', '*', '*', '*', '*', '*', '*',
            'Welcome global!' 
        );

INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('pos.welcome.text', {ts '2000-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            'N_AMERICA', '*', '*', '*', '*', '*', '*', '*',
            'Welcome North America!' 
        );        
INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('pos.welcome.text', {ts '2000-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            'N_AMERICA', 'US', '*', '*', '*', '*', '*', '*',
            'Welcome USA!' 
        );
INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('pos.welcome.text', {ts '2000-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            'N_AMERICA', 'US', 'OH', '*', '*', '*', '*', '*',
            'Welcome OH, USA!' 
        );             
INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('pos.welcome.text', {ts '2000-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            'N_AMERICA', 'US', 'OH', '100', '*', '*', '*', '*',
            'Welcome store 100 in OH, USA!' 
        );                     
INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('pos.welcome.text', {ts '2000-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            '*', '*', '*', '*', 'OUTLET', '*', '*', '*',
            'Welcome outlet stores!' 
        );                             
INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('pos.welcome.text', {ts '2000-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            '*', '*', '*', '*', '*', 'MOBILE_POS', '*', '*',
            'Welcome mobile POS!' 
        );                          
INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('pos.welcome.text', {ts '2000-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            '*', '*', '*', '*', '*', '*', 'METL', '*',
            'Welcome Metl brand!' 
        );                                     

INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('pos.welcome.text', {ts '2000-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            '*', '*', '*', '*', '*', '*', '*', 'POS',
            'Welcome POS app!' 
        );                                         
INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('pos.welcome.text', {ts '2000-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            'EAST_AFRICA', 'UG', '*', '*', 'REGULAR', '*', 'Metl', 'POS',
            'Welcome UG POS app!' 
        );               
INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('pos.welcome.text', {ts '2000-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            'EAST_AFRICA', 'UG', '*', '00039', 'REGULAR', 'WORKSTATION', 'Metl', 'POS',
            'Welcome super specific!' 
        );

INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('pos.login.timeout', {ts '2000-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            '*', '*', '*', '*', '*', '*', '*', '*',
            'global login timeout' 
        );

INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('pos.login.retry.attempts', {ts '2000-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            'N_AMERICA', 'US', 'OH', '*', '*', '*', '*', '*',
            '10' 
        );     

INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('pos.login.retry.attempts', {ts '2000-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            'EAST_AFRICA', 'UG', '*', '*', '*', '*', '*', '*',
            '15' 
        );                

INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('test.expired', {ts '2000-01-01 00:00:00.000'}, {ts '2010-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            '*', '*', '*', '*', '*', '*', '*', '*',
            'Value is expired.' 
        );                    

INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('test.expired.one.active', {ts '2000-01-01 00:00:00.000'}, {ts '2010-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            '*', '*', '*', '*', '*', '*', '*', '*',
            'Value is expired.' 
        );                                        
INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('test.expired.one.active', {ts '2010-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            '*', '*', '*', '*', '*', '*', '*', '*',
            'Value is active.' 
        );                                

INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('test.effective', {ts '2020-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            '*', '*', '*', '*', '*', '*', '*', '*',
            'Value is not effective yet' 
        );     
INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('test.one.effective', {ts '2020-01-01 00:00:00.000'}, {ts '2030-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            '*', '*', '*', '*', '*', '*', '*', '*',
            'Value is not effective yet' 
        );            
INSERT INTO CTX_CONFIG(CONFIG_NAME, EFFECTIVE_DATE, EXPIRATION_DATE, ENABLED_FLAG, CREATE_TIME, CREATE_BY, LAST_UPDATE_TIME, LAST_UPDATE_BY, 
    TAG_REGION, TAG_COUNTRY, TAG_STATE, TAG_STORE_NUMBER, TAG_STORE_TYPE, TAG_DEVICE_TYPE, TAG_BRAND_ID, TAG_APP_PROFILE,
    CONFIG_VALUE)
    VALUES ('test.one.effective', {ts '2010-01-01 00:00:00.000'}, {ts '2020-01-01 00:00:00.000'}, 1, CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test',
            '*', '*', '*', '*', '*', '*', '*', '*',
            'this one is effective' 
        );                        
