-- /*
--  * Insert some dummy data in the schema
--  *
--  */

-- Facilities
INSERT INTO Facility
(fid, fac_name, classification, no_of_departments, capacity)
VALUES (1000, 'Wolf Hospital', '03', 3, 300);

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state, addr_zip)
VALUES (50, 2650, 'Wolf Village Way Box 7220', 'Raleigh', 'NC', '27695');

INSERT INTO Facility_has_address
(fid, addr_id)
VALUES(1000, 50);

INSERT INTO Facility
(fid, fac_name, classification, no_of_departments, capacity)
VALUES (1001, 'California Health Care', '02', 2, 150);

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip)
VALUES (51, 2500, 'Sacramento', 'Santa Cruz', 'CA', '90021');

INSERT INTO Facility_has_address
(fid, addr_id)
VALUES(1001, 51);

INSERT INTO Facility
(fid, fac_name, classification, no_of_departments, capacity)
VALUES (1002, 'Suny Medical Center', '01', 1, 10);

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip)
VALUES (52, 489, 'First Avenue', 'New York', 'NY', '10001');

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
INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('true-false', 'True', 1);
INSERT INTO Scale_parameter
(scale_name, param, severity)
VALUES ('true-false', 'False', 0);

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
(user_id, Fname, Lname, ph_no, DoB)
VALUES(89001, 'Medical', 'Robot', '1234567890', TO_DATE('04/19/1989', 'MM/DD/YYYY'));

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(89001, 'Y',TO_DATE('06/21/2019', 'MM/DD/YYYY'), 'OP000');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip)
VALUES (53, 83, 'Vernon St.', 'Scotch Plains', 'NJ', '07076');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(89001, 53);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(89001, 'OP000');

INSERT INTO Facility_has_User
(fid, user_id)
VALUES(1000, 89001);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB)
VALUES(93001, 'Musical', 'Robert', '1234567890', TO_DATE('01/29/1993', 'MM/DD/YYYY'));

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(93001, 'Y',TO_DATE('08/29/2018', 'MM/DD/YYYY'), 'ER000');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip)
VALUES (54, 69, 'Holly Drive', 'Blacksburg', 'VA', '24060');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(93001, 54);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(93001, 'ER000');

INSERT INTO Facility_has_User
(fid, user_id)
VALUES(1001, 93001);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB)
VALUES(67001, 'Muscular', 'Rob', '1234567890', TO_DATE('12/09/1967', 'MM/DD/YYYY'));

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(67001, 'Y',TO_DATE('10/12/1983', 'MM/DD/YYYY'), 'GP000');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip)
VALUES (55, 7540, 'Plymouth Court', 'Derry,', 'NH', '03038');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(67001, 55);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(67001, 'GP000');

INSERT INTO Facility_has_User
(fid, user_id)
VALUES(1000, 67001);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB)
VALUES(88001, 'Mechanical', 'Roboto', '1234567890', TO_DATE('05/18/1988', 'MM/DD/YYYY'));

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(88001, 'Y',TO_DATE('06/21/2019', 'MM/DD/YYYY'), 'GP000');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip)
VALUES (56, 8196, 'Big Rock Cove Road', 'Lutherville Timonium', 'MD', '20193');

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
(fid, user_id)
VALUES(1000, 88001);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB)
VALUES(91001, 'Millennium', 'Roberten', '1234567890', TO_DATE('06/28/1991', 'MM/DD/YYYY'));

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(91001, 'Y',TO_DATE('09/20/2018', 'MM/DD/YYYY'), 'GP001');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip)
VALUES (57, 697, 'Lawrence Ave.', 'Teaneck', 'NJ', '07666');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(91001, 57);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(91001, 'GP001');

INSERT INTO Facility_has_User
(fid, user_id)
VALUES(1001, 91001);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB)
VALUES(66001, 'Missionary', 'Robinson', '1234567890', TO_DATE('07/08/1966', 'MM/DD/YYYY'));

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(66001, 'Y',TO_DATE('10/01/1993', 'MM/DD/YYYY'), 'ER000');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip)
VALUES (58, 685, 'South Chapel Lane', 'Branford', 'CT', '06405');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(66001, 58);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(66001, 'ER000');

INSERT INTO Facility_has_User
(fid, user_id)
VALUES(1001, 66001);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB)
VALUES(89002, 'Miscellaneous', 'Robotor', '1234567890', TO_DATE('04/19/1989', 'MM/DD/YYYY'));

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(89002, 'N',TO_DATE('08/19/2014', 'MM/DD/YYYY'), 'SE000');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip)
VALUES (59, 7056, 'W. Piper Dr.', 'Macon,', 'GA', '31204');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(89002, 59);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(89002, 'SE000');

INSERT INTO Facility_has_User
(fid, user_id)
VALUES(1000, 89002);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB)
VALUES(93002, 'Musician', 'Root', '1234567890', TO_DATE('01/29/1993', 'MM/DD/YYYY'));

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(93002, 'N',TO_DATE('10/18/2017', 'MM/DD/YYYY'), 'SE000');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip)
VALUES (60, 40, 'N. Peachtree Drive', 'Sunnyside', 'NY', '11104');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(93002, 60);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(93002, 'SE000');

INSERT INTO Facility_has_User
(fid, user_id)
VALUES(1000, 93002);

INSERT INTO Login_user
(user_id, Fname, Lname, ph_no, DoB)
VALUES(67002, 'Massaging', 'Robin', '1234567890', TO_DATE('12/09/1967', 'MM/DD/YYYY'));

INSERT INTO Staff
(user_id, medical, hiredate, primary_dept)
VALUES(67002, 'Y',TO_DATE('12/10/1990', 'MM/DD/YYYY'), 'ER001');

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip)
VALUES (61, 22, 'Sutor St.', 'Laurel', 'MD', '20707');

INSERT INTO User_has_address
(user_id, addr_id)
VALUES(67002, 61);

INSERT INTO Works_in
(user_id, dept_code)
VALUES(67002, 'ER001');

INSERT INTO Facility_has_User
(fid, user_id)
VALUES(1002, 67002);

INSERT INTO Body_part
(code, part_name)
VALUES('Left Arm', 'ARM000')

INSERT INTO Body_part
(code, part_name)
VALUES('Right Arm', 'ARM001');

INSERT INTO Body_part
(code, part_name)
VALUES('Abdominal', 'ABD000');

INSERT INTO Body_part
(code, part_name)
VALUES('Eye', 'EYE000');

INSERT INTO Body_part
(code, part_name)
VALUES('Heart', 'HRT000');

INSERT INTO Body_part
(code, part_name)
VALUES('Chest', 'CST000');

INSERT INTO Body_part
(code, part_name)
VALUES('Head', 'HED000');

