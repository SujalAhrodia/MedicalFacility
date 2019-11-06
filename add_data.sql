/*
 * Insert some dummy data in the schema
 *
 */

INSERT INTO address
(addr_id, addr_number, addr_street, addr_city, addr_state, addr_country)
VALUES (0, 1337, 'kingsford lane', 'Raleigh', 'NC', 'Wake'),
(1, 91, 'large blvd', 'Charlotte', 'NC', 'Big south'),
(2, 42, 'unfortunate st', 'Greensboro', 'NC', 'nowhere');

INSERT INTO login_user
(user_id, Fname, Lname, ph_no, dob)
VALUES (0, 'carl', 'wheezer', 'ph_no', TO_DATE('17/12/2015', 'DD/MM/YYYY')),
(1, 'jimmy', 'neutron', 'ph_no', TO_DATE('17/12/2015', 'DD/MM/YYYY'));


INSERT INTO Facility
(fid, fac_name, classification, no_of_departments, capacity)
VALUES (0, 'Fac1', 'Hospital', 0, 100),
(1, 'Fac2', 'ER', 0, 25);


INSERT INTO certification
(acronym, cert_name)
VALUES ('PRS', 'Prosthetics'),
('CRD', 'Cardiology'),
('XRY', 'X-ray');

INSERT INTO Service
(code, service_name)
VALUES ('CST', 'Cast'),
('DFB', 'Defibrillator'),
('XRY', 'X-ray');

INSERT INTO Equipment
(equip_name)
VALUES ('X-ray machine'),
('3D Printer');

INSERT INTO Body_part
(code, part_name)
VALUES ('LEG', 'leg'),
('EAR', 'ear'),
('HED', 'head'),
('EYE', 'eye'),
('STO', 'stomach'),
('ALL', 'entire body');

INSERT INTO Symptom_scale
(scale_name)
VALUES ('1-10'),
('true-false');

INSERT INTO Assessment
(assessment_id, category)
VALUES (0, 'Dead'),
(1, 'Near death'),
(2, 'You good');

INSERT INTO Vital_signals
(vital_id, temperature, blood_pressure)
VALUES (0, '98', '80');

INSERT INTO Negative_experience
(ne_code, description)
VALUES (0, 'Misdiagnosis. Doctor is a quack');

INSERT INTO Report
(rid, treatment, discharge_status)
VALUES (0, 'Prescribed motrin', 'Discharged');

/* ---- now for the FKs ---- */

INSERT INTO User_has_address
(user_id, addr_id)
VALUES (0, 2);

INSERT INTO Facility_has_address
(fid, addr_id)
VALUES (0, 1),
(1, 0);

INSERT INTO Certified
(fid, acronym, date_certified, date_of_expiration)
VALUES (0, 'SRG', TO_DATE('17/12/2015', 'DD/MM/YYYY'), TO_DATE('17/12/2016', 'DD/MM/YYYY'));

INSERT INTO Patient
(user_id, checkin_time, checkout_time)
VALUES (1, TO_DATE('17/12/2015', 'DD/MM/YYYY'), TO_DATE('17/12/2016', 'DD/MM/YYYY'));

/* update the circular dependency in a transaction */
BEGIN;

INSERT INTO Service_department
(dept_id, dept_name, director)
VALUES (0, 'Big dept', 0);

INSERT INTO Staff
(user_id, designation, hiredate, primary_dept)
VALUES (0, 'Medical', TO_DATE('17/12/2016', 'DD/MM/YYYY'), 0);

COMMIT;

INSERT INTO Provides
(service, dept_id)
VALUES ('XRY', 0);

INSERT INTO Service_has_equipment
(service, equipment)
VALUES ('XRY', 'X-ray machine');

INSERT INTO Associated_to
(service, part)
VALUES ('XRY', 'ALL');

INSERT INTO Symptom
(symptom_name, code, priority, symptom_scale)
VALUES ('Headache', 'HDCH', 2, '1-10');

INSERT INTO Implies
(symptom, part)
VALUES ('HDCH', 'HED');

INSERT INTO Has_symptom
(symptom, patient, duration, incident, recurring)
VALUES ('HDCH', 1, '3 days', 'Dehydrated', TRUE);

INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('1-10', '1', 1),
('1-10', '2', 2),
('1-10', '3', 3),
('1-10', '4', 4),
('1-10', '5', 5),
('1-10', '6', 6),
('1-10', '7', 7),
('1-10', '8', 8),
('1-10', '9', 9),
('1-10', '10', 10),
('true-false', 'True', 1),
('true-false', 'False', 0);

INSERT INTO Evaluate
(user_id, assessment_id)
VALUES (1, 0);

INSERT INTO Consists_of
(assessment_id, symptom, part, sympt_scale, severity)
VALUES (0, 'HDCH', 'HED', '1-10', '4');

INSERT INTO Patient_has_report
(user_id, rid)
VALUES (1, 0);

INSERT INTO Report_has_negative
(rid, ne_code)
VALUES (0, 0);

INSERT INTO Referral_status
(rs_id, fid, referrer)
VALUES
(0, 0, 0);

INSERT INTO Report_has_ref
(rid, rs_id)
VALUES (0, 0);

INSERT INTO Reason
(reason_code, service_name, description)
VALUES ('X', 'XRY', 'Broke a bone');

INSERT INTO Report_has_reason
(rid, reason)
VALUES (0, 'X');

INSERT INTO Vital_recordings
(vital_id, patient, staff)
VALUES (0, 1, 0);



