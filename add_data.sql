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
(service_name, code)
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
('STO', 'stomach');

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
