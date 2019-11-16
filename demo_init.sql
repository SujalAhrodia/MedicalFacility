
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


COMMIT;
