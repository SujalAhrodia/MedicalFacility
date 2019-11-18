-- /*
--  * Insert some dummy data in the schema
--  *
--  */

-- Facilities
INSERT INTO Facility
(fid, fac_name, classification, no_of_departments, capacity)
VALUES (1000, 'Wolf Hospital', '03', 3, 300);

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state, addr_zip, addr_country)
VALUES (50, 2650, 'Wolf Village Way Box 7220', 'Raleigh', 'NC', '27695', 'USA');

INSERT INTO Facility_has_address
(fid, addr_id)
VALUES(1000, 50);

INSERT INTO Facility
(fid, fac_name, classification, no_of_departments, capacity)
VALUES (1001, 'California Health Care', '02', 2, 150);

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip, addr_country)
VALUES (51, 2500, 'Sacramento', 'Santa Cruz', 'CA', '90021', 'USA');

INSERT INTO Facility_has_address
(fid, addr_id)
VALUES(1001, 51);

INSERT INTO Facility
(fid, fac_name, classification, no_of_departments, capacity)
VALUES (1002, 'Suny Medical Center', '01', 1, 10);

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip, addr_country)
VALUES (52, 489, 'First Avenue', 'New York', 'NY', '10001', 'USA');

INSERT INTO Facility_has_address
(fid, addr_id)
VALUES(1002, 52);

INSERT INTO Certification
(acronym, cert_name, date_certified, date_of_expiration)
VALUES('CER001', 'Comprehensive Stroke certification', TO_DATE('11/12/1990', 'MM/DD/YYYY'), TO_DATE('11/11/2025', 'MM/DD/YYYY'));

INSERT INTO Certification
(acronym, cert_name, date_certified, date_of_expiration)
VALUES('CER002', 'ISO Certification', TO_DATE('5/9/2011', 'MM/DD/YYYY'), TO_DATE('2/8/2024', 'MM/DD/YYYY'));

INSERT INTO Certification
(acronym, cert_name, date_certified, date_of_expiration)
VALUES('CER003', 'Primary Stroke Certification', TO_DATE('1/1/2018', 'MM/DD/YYYY'), TO_DATE('12/31/2028', 'MM/DD/YYYY'));

INSERT INTO Certified
(fid, acronym)
VALUES(1000, 'CER001');

INSERT INTO Certified
(fid, acronym)
VALUES(1001, 'CER002');

INSERT INTO Certified
(fid, acronym)
VALUES(1002, 'CER002');

INSERT INTO Certified
(fid, acronym)
VALUES(1002, 'CER003');

INSERT INTO Symptom_scale
(scale_name)
VALUES ('1-10');

INSERT INTO Symptom_scale
(scale_name)
VALUES ('Normal/Severe');
INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('Normal/Severe', 'Normal', 1);
INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('Normal/Severe', 'Severe', 2);

INSERT INTO Symptom_scale
(scale_name)
VALUES ('Low/High');
INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('Low/High', 'Low', 1);
INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('Low/High', 'High', 2);

INSERT INTO Symptom_scale
(scale_name)
VALUES ('Normal/Premium');
INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('Normal/Premium', 'Normal', 1);
INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('Normal/Premium', 'Premium', 2);

INSERT INTO Symptom_scale
(scale_name)
VALUES ('Moderate/Heavy');
INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('Moderate/Heavy', 'Moderate', 1);
INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('Moderate/Heavy', 'Heavy', 2);

INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('1-10', '1', 1);

INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('1-10', '2', 2);

INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('1-10', '3', 3);

INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('1-10', '4', 4);

INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('1-10', '5', 5);

INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('1-10', '6', 6);

INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('1-10', '7', 7);

INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('1-10', '8', 8);

INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('1-10', '9', 9);

INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('1-10', '10', 10);

-- Service Department

INSERT INTO Service_department
(dept_code, dept_name, director)
VALUES ('ER000', 'Emergency room', 93001);

INSERT INTO Service_department
(dept_code, dept_name, director)
VALUES ('GP000', 'General practice department', 67001);

INSERT INTO Service_department
(dept_code, dept_name, director)
VALUES ('GP001', 'General practice department', 91001);

INSERT INTO Service_department
(dept_code, dept_name, director)
VALUES ('OP000', 'Optometry', 89001);

INSERT INTO Service_department
(dept_code, dept_name, director)
VALUES ('SE000', 'Security', 89002);

INSERT INTO Service_department
(dept_code, dept_name, director)
VALUES ('ER001', 'Emergency room', 67002);

INSERT INTO Contains
(fid, dept_code)
VALUES(1000, 'GP000');

INSERT INTO Contains
(fid, dept_code)
VALUES(1000, 'OP000');

INSERT INTO Contains
(fid, dept_code)
VALUES(1000, 'SE000');

INSERT INTO Contains
(fid, dept_code)
VALUES(1001, 'ER000');

INSERT INTO Contains
(fid, dept_code)
VALUES(1001, 'GP001');

INSERT INTO Contains
(fid, dept_code)
VALUES(1001, 'ER001');

--  Service

INSERT INTO Service
(service_name, code)
VALUES('Emergency', 'SER01');

INSERT INTO Equipment
(equip_name, service)
VALUES('ER combo rack', 'SER01');

INSERT INTO Provides
(service, dept_code)
VALUES('SER01', 'ER000');

INSERT INTO Service
(service_name, code)
VALUES('General practice', 'SGP01');

INSERT INTO Equipment
(equip_name, service)
VALUES('Blood pressure monitor', 'SGP01');

INSERT INTO Equipment
(equip_name, service)
VALUES('thermometer', 'SGP01');

INSERT INTO Provides
(service, dept_code)
VALUES('SGP01', 'GP000');

INSERT INTO Provides
(service, dept_code)
VALUES('SGP01', 'GP001');

INSERT INTO Service
(service_name, code)
VALUES('Vision', 'VIS01');

INSERT INTO Equipment
(equip_name, service)
VALUES('Vision Screener', 'VIS01');

INSERT INTO Provides
(service, dept_code)
VALUES('VIS01', 'OP000');

-- Staff

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB, isPatient)
VALUES(89001, 'Medical', 'Robot', '1234567890', TO_DATE('04/19/1989', 'MM/DD/YYYY'), 'N');

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(89001, 'Y',TO_DATE('06/21/2019', 'MM/DD/YYYY'), 'OP000');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip, addr_country)
VALUES (53, 83, 'Vernon St.', 'Scotch Plains', 'NJ', '07076', 'USA');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(89001, 53);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(89001, 'OP000');

INSERT INTO Facility_has_User
(fid, user_id, fhu_id)
VALUES(1000, 89001, 89001);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB, isPatient)
VALUES(93001, 'Musical', 'Robert', '1234567890', TO_DATE('01/29/1993', 'MM/DD/YYYY'), 'N');

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(93001, 'Y',TO_DATE('08/29/2018', 'MM/DD/YYYY'), 'ER000');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip, addr_country)
VALUES (54, 69, 'Holly Drive', 'Blacksburg', 'VA', '24060', 'USA');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(93001, 54);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(93001, 'ER000');

INSERT INTO Facility_has_User
(fid, user_id, fhu_id)
VALUES(1001, 93001, 93001);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB, isPatient)
VALUES(67001, 'Muscular', 'Rob', '1234567890', TO_DATE('12/09/1967', 'MM/DD/YYYY'), 'N');

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(67001, 'Y',TO_DATE('10/12/1983', 'MM/DD/YYYY'), 'GP000');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip, addr_country)
VALUES (55, 7540, 'Plymouth Court', 'Derry', 'NH', '03038', 'USA');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(67001, 55);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(67001, 'GP000');

INSERT INTO Facility_has_User
(fid, user_id, fhu_id)
VALUES(1000, 67001, 67001);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB, isPatient)
VALUES(88001, 'Mechanical', 'Roboto', '1234567890', TO_DATE('05/18/1988', 'MM/DD/YYYY'), 'N');

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(88001, 'Y',TO_DATE('06/21/2019', 'MM/DD/YYYY'), 'GP000');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip, addr_country)
VALUES (56, 8196, 'Big Rock Cove Road', 'Lutherville Timonium', 'MD', '20193', 'USA');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(88001, 56);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(88001, 'GP000');

INSERT INTO Works_in
(user_id, dept_code)
VALUES(88001, 'OP000');

INSERT INTO Facility_has_User
(fid, user_id, fhu_id)
VALUES(1000, 88001, 8801);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB, isPatient)
VALUES(91001, 'Millennium', 'Roberten', '1234567890', TO_DATE('06/28/1991', 'MM/DD/YYYY'), 'N');

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(91001, 'Y',TO_DATE('09/20/2018', 'MM/DD/YYYY'), 'GP001');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip, addr_country)
VALUES (57, 697, 'Lawrence Ave.', 'Teaneck', 'NJ', '07666', 'USA');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(91001, 57);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(91001, 'GP001');

INSERT INTO Facility_has_User
(fid, user_id, fhu_id)
VALUES(1001, 91001, 91001);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB, isPatient)
VALUES(66001, 'Missionary', 'Robinson', '1234567890', TO_DATE('07/08/1966', 'MM/DD/YYYY'), 'N');

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(66001, 'Y',TO_DATE('10/01/1993', 'MM/DD/YYYY'), 'ER000');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip, addr_country)
VALUES (58, 685, 'South Chapel Lane', 'Branford', 'CT', '06405', 'USA');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(66001, 58);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(66001, 'ER000');

INSERT INTO Facility_has_User
(fid, user_id, fhu_id)
VALUES(1001, 66001, 66001);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB, isPatient)
VALUES(89002, 'Miscellaneous', 'Robotor', '1234567890', TO_DATE('04/19/1989', 'MM/DD/YYYY'), 'N');

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(89002, 'N',TO_DATE('08/19/2014', 'MM/DD/YYYY'), 'SE000');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip, addr_country)
VALUES (59, 7056, 'W. Piper Dr.', 'Macon', 'GA', '31204', 'USA');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(89002, 59);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(89002, 'SE000');

INSERT INTO Facility_has_User
(fid, user_id, fhu_id)
VALUES(1000, 89002, 89002);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB, isPatient)
VALUES(93002, 'Musician', 'Root', '1234567890', TO_DATE('01/29/1993', 'MM/DD/YYYY'), 'N');

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(93002, 'N',TO_DATE('10/18/2017', 'MM/DD/YYYY'), 'SE000');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip, addr_country)
VALUES (60, 40, 'N. Peachtree Drive', 'Sunnyside', 'NY', '11104', 'USA');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(93002, 60);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(93002, 'SE000');

INSERT INTO Facility_has_User
(fid, user_id, fhu_id)
VALUES(1000, 93002, 93002);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB, isPatient)
VALUES(67002, 'Massaging', 'Robin', '1234567890', TO_DATE('12/09/1967', 'MM/DD/YYYY'), 'N');

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(67002, 'Y',TO_DATE('12/10/1990', 'MM/DD/YYYY'), 'ER001');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip, addr_country)
VALUES (61, 22, 'Sutor St.', 'Laurel', 'MD', '20707', 'USA');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(67002, 61);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(67002, 'ER001');

INSERT INTO Facility_has_User
(fid, user_id, fhu_id)
VALUES(1002, 67002, 67002);

-- Body Part
INSERT INTO Body_part
(code, part_name)
VALUES('None','None');

INSERT INTO Body_part
(code, part_name)
VALUES('ARM000','Left Arm');

INSERT INTO Body_part
(code, part_name)
VALUES('ARM001','Right Arm');

INSERT INTO Body_part
(code, part_name)
VALUES('ABD000', 'Abdominal');

INSERT INTO Body_part
(code, part_name)
VALUES('EYE000','Eye');

INSERT INTO Body_part
(code, part_name)
VALUES('HRT000', 'Heart');

INSERT INTO Body_part
(code, part_name)
VALUES('CST000', 'Chest');

INSERT INTO Body_part
(code, part_name)
VALUES('HED000', 'Head');

-- Symptoms

INSERT INTO Symptom
(symptom_name, code, symptom_scale)
VALUES('Pain', 'SYM001', '1-10');

INSERT INTO Symptom
(symptom_name, code, symptom_scale)
VALUES('Diarrhea', 'SYM002', 'Normal/Severe');

INSERT INTO Implies
(symptom, part)
VALUES ('SYM002', 'ABD000');

INSERT INTO Symptom
(symptom_name, code, symptom_scale)
VALUES('Fever', 'SYM003', 'Low/High');

INSERT INTO Symptom
(symptom_name, code, symptom_scale)
VALUES('Physical Exam', 'SYM004', 'Normal/Premium');

INSERT INTO Symptom
(symptom_name, code, symptom_scale)
VALUES('Lightheadedness', 'SYM005', 'Normal/Severe');

INSERT INTO Implies
(symptom, part)
VALUES ('SYM005', 'HED000');

INSERT INTO Symptom
(symptom_name, code, symptom_scale)
VALUES('Blurred vision', 'SYM006', 'Normal/Severe');

INSERT INTO Implies
(symptom, part)
VALUES ('SYM006', 'EYE000');

INSERT INTO Symptom
(symptom_name, code, symptom_scale)
VALUES('Bleeding', 'SYM007', 'Moderate/Heavy');

-- Example Rules

INSERT INTO Assessment
(assessment_id, category)
VALUES (0, 'N');

INSERT INTO Assessment
(assessment_id, category)
VALUES (1, 'H');

INSERT INTO Consists_of
(assessment_id, symptom, part, sympt_scale, threshold, direction)
VALUES (1, 'SYM001', 'CST000', '1-10', '7', '>');
INSERT INTO Consists_of
(assessment_id, symptom, part, sympt_scale, threshold, direction)
VALUES (1, 'SYM003', 'None', 'Low/High', 'High', '==');

INSERT INTO Assessment
(assessment_id, category)
VALUES (2, 'H');

INSERT INTO Consists_of
(assessment_id, symptom, part, sympt_scale, threshold, direction)
VALUES (2, 'SYM001', 'HED000', '1-10', '7', '>');
INSERT INTO Consists_of
(assessment_id, symptom, part, sympt_scale, threshold, direction)
VALUES (2, 'SYM006', 'EYE000', 'Normal/Severe', 'Normal', '>=');
INSERT INTO Consists_of
(assessment_id, symptom, part, sympt_scale, threshold, direction)
VALUES (2, 'SYM005', 'HED000', 'Normal/Severe', 'Normal', '>=');

INSERT INTO Assessment
(assessment_id, category)
VALUES (3, 'N');

INSERT INTO Consists_of
(assessment_id, symptom, part, sympt_scale, threshold, direction)
VALUES (3, 'SYM001', 'HED000', '1-10', '7', '<=');
INSERT INTO Consists_of
(assessment_id, symptom, part, sympt_scale, threshold, direction)
VALUES (3, 'SYM006', 'EYE000', 'Normal/Severe', 'Normal', '>=');

-- Patient

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB, isPatient)
VALUES(1, 'John', 'Smith', 9007004567, TO_DATE('1/1/1990', 'MM/DD/YYYY'), 'Y');

INSERT INTO Facility_has_User
(fid, user_id, fhu_id)
VALUES(1000, 1, 1);

INSERT INTO Patient
(user_id)
VALUES(1);

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state, addr_zip, addr_country)
VALUES (62,100, 'Avent Ferry Road', 'Raleigh', 'NC', '27606','USA');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(1, 62);

INSERT INTO Has_symptom
(symptom, part, patient, value, duration, incident, recurring)
VALUES('SYM003', 'None', 1, 'High', '1','Unknown','N');


INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB, isPatient)
VALUES(2, 'Jane', 'Doe', 9192453245, TO_DATE('2/29/2000', 'MM/DD/YYYY'), 'Y');

INSERT INTO Facility_has_User
(fid, user_id, fhu_id)
VALUES(1000, 2, 2);

INSERT INTO Patient
(user_id)
VALUES(2);

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state, addr_zip, addr_country)
VALUES (63,1016, 'Lexington Road', 'New York', 'NY', '12005','USA');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(2, 63);

INSERT INTO Has_symptom
(symptom, part, patient, value, duration, incident, recurring)
VALUES('SYM001', 'ARM000', 2, '5', '3','Fell off bike','Y');


INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB, isPatient)
VALUES(3, 'Rock', 'Start', 5403127893, TO_DATE('8/31/1970', 'MM/DD/YYYY'), 'Y');

INSERT INTO Facility_has_User
(fid, user_id, fhu_id)
VALUES(1000, 3, 3);

INSERT INTO Patient
(user_id)
VALUES(3);

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state, addr_zip, addr_country)
VALUES (64,1022, 'Amphitheatre Parkway', 'Mountain View', 'CA', '90021','USA');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(3, 64);


INSERT INTO Has_symptom
(symptom, part, patient, value, duration, incident, recurring)
VALUES('SYM002', 'None', 3, 'Severe', '1','Pepper challenge','N');

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB, isPatient)
VALUES(4, 'Sheldon', 'Cooper', 6184628437, TO_DATE('05/26/1984', 'MM/DD/YYYY'), 'Y');

INSERT INTO Negative_experience (ne_code, description) VALUES (1,'Misdiagnosis');
INSERT INTO Negative_experience (ne_code, description) VALUES (2,'Patient acquired infection during hospital stay.');

INSERT INTO Facility_has_User
(fid, user_id, fhu_id)
VALUES(1000, 4, 4);

INSERT INTO Patient
(user_id)
VALUES(4);

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state, addr_zip, addr_country)
VALUES (65,1210, 'Sacramento', 'Santa Cruz', 'CA', '90021','USA');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(4, 65);


INSERT INTO Has_symptom
(symptom, part, patient, value, duration, incident, recurring)
VALUES('SYM006', 'EYE000', 4, 'Normal', '1','Unknown','N');

INSERT INTO Associated_to
(service, part)
VALUES ('VIS01', 'EYE000');

INSERT INTO Associated_to
(service, part)
VALUES ('SER01', 'EYE000');

INSERT INTO Associated_to
(service, part)
VALUES ('SER01', 'HED000');
commit;
