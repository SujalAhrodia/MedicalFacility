
-- Create a test patient
INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB)
VALUES(80, 'A', 'Demo', 9007004567, TO_DATE('1/1/1990', 'MM/DD/YYYY'));

INSERT INTO Patient
(user_id, checkin_time_start, checkin_time_end, checkout_time, isTreated)
VALUES (80, TO_DATE('2015/12/17', 'YYYY/MM/DD'), TO_DATE('2015/12/17', 'YYYY/MM/DD'), TO_DATE('2016/12/17', 'YYYY/MM/DD'), 'Y');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(80, 63);

INSERT INTO Facility_has_User
(fid, user_id)
VALUES(1000, 80);

-- Create discharge report
INSERT INTO Report
(rid, treatment, discharge_status)
VALUES (0, 'Prescribed motrin', 'Discharged');

INSERT INTO patient_has_report
(user_id, rid)
VALUES (80, 0);

-- negative experiences
-- 1 misdiagnosis
-- 2 patient got infection

INSERT INTO report_has_negative
(ne_code, rid, user_desc)
VALUES (1, 0, 'Infection was bad');

-- Reports and Referrals Data
INSERT INTO REPORT (RID, DISCHARGE_STATUS,TREATMENT ) VALUES (301,'Successfull Treatment', 'Prescribed advil');
INSERT INTO REPORT (RID, DISCHARGE_STATUS,TREATMENT ) VALUES (302,'Referred', 'No treatment');
INSERT INTO REPORT (RID, DISCHARGE_STATUS,TREATMENT ) VALUES (303,'Deceased', 'Prescribed advil');
INSERT INTO REPORT (RID, DISCHARGE_STATUS,TREATMENT ) VALUES (304,'Referred', 'Temporary Painkillers');

INSERT INTO PATIENT_HAS_REPORT (USER_ID, RID) VALUES (1,301);
INSERT INTO PATIENT_HAS_REPORT (USER_ID, RID) VALUES (2,302);
INSERT INTO PATIENT_HAS_REPORT (USER_ID, RID) VALUES (3,303);
INSERT INTO PATIENT_HAS_REPORT (USER_ID, RID) VALUES (4,304);

INSERT INTO REFERRAL_STATUS (RS_ID, FID, REFERRER) VALUES (402,1001,89001);
INSERT INTO REFERRAL_STATUS (RS_ID, FID, REFERRER) VALUES (404,1000,67002);

INSERT INTO REPORT_HAS_REF (RID, RS_ID) VALUES (302,402);
INSERT INTO REPORT_HAS_REF (RID, RS_ID) VALUES (304,404);

INSERT INTO REASON (REASON_CODE, SERVICE_NAME, RS_ID, DESCRIPTION) VALUES (1,'SER01',402,'Emergency rooms full');
INSERT INTO REASON (REASON_CODE, SERVICE_NAME, RS_ID, DESCRIPTION) VALUES (2,'SGP01',402,'Relevant Equipment Unavailable');
INSERT INTO REASON (REASON_CODE, SERVICE_NAME, RS_ID, DESCRIPTION) VALUES (3,'SGP01',402,'Equipmnet Damaged');

INSERT INTO REASON (REASON_CODE, SERVICE_NAME, RS_ID, DESCRIPTION) VALUES (1,'SER01',404,'Cannot Accomodate Patient');
INSERT INTO REASON (REASON_CODE, SERVICE_NAME, RS_ID, DESCRIPTION) VALUES (1,'SER01',404,'No Emergency Equipment');
INSERT INTO REASON (REASON_CODE, SERVICE_NAME, RS_ID, DESCRIPTION) VALUES (1,'SER01',404,'Short on Emergency care staff');

INSERT INTO REPORT_HAS_NEGATIVE (RID, NE_CODE, USER_DESC) VALUES (301,1,'Misdiagnosed Tapeworm as Overeating');
INSERT INTO REPORT_HAS_NEGATIVE (RID, NE_CODE, USER_DESC) VALUES (301,2,'got infected from stethoscope');
INSERT INTO REPORT_HAS_NEGATIVE (RID, NE_CODE, USER_DESC) VALUES (301,2,'dirty equipmet caused infections');

INSERT INTO REPORT_HAS_NEGATIVE (RID, NE_CODE, USER_DESC) VALUES (302,2,'used infected needle');
INSERT INTO REPORT_HAS_NEGATIVE (RID, NE_CODE, USER_DESC) VALUES (303,2,'infected by the other patients');
INSERT INTO REPORT_HAS_NEGATIVE (RID, NE_CODE, USER_DESC) VALUES (304,1,'misdiagnosed and gave wrong med');

INSERT INTO Has_symptom (symptom, part, patient, value, duration, incident, recurring)
VALUES('SYM001', 'HRT000', 1, 'High', '1','Unknown','N');

INSERT INTO Has_symptom (symptom, part, patient, value, duration, incident, recurring)
VALUES('SYM001', 'HRT000', 2, 'High', '2','Unknown','Y');1

COMMIT;
