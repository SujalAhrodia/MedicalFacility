/*
 * Insert some dummy data in the schema
 *
 */

INSERT INTO address
(addr_id, addr_number, addr_street, addr_city, addr_state, addr_country)
VALUES (seq.NEXTVAL, 2650, 'Wolf Village Way Box 7220', 'Raleigh', 'NC', 'Wake');

INSERT INTO address
(addr_id, addr_number, addr_street, addr_city, addr_state, addr_country)
VALUES (1, 91, 'large blvd', 'Charlotte', 'NC', 'Big south');

INSERT INTO address
(addr_id, addr_number, addr_street, addr_city, addr_state, addr_country)
VALUES (2, 42, 'unfortunate st', 'Greensboro', 'NC', 'nowhere');

INSERT INTO login_user
(user_id, Fname, Lname, ph_no, dob)
VALUES (0, 'carl', 'wheezer', 'ph_no', TO_DATE('17/12/2015', 'DD/MM/YYYY'));

INSERT INTO login_user
(user_id, Fname, Lname, ph_no, dob)
VALUES (1, 'jimmy', 'neutron', 'ph_no', TO_DATE('17/12/2015', 'DD/MM/YYYY'));

INSERT INTO login_user
(user_id, Fname, Lname, ph_no, dob)
VALUES (2, 'sheen', 'estevez', 'ph_no', TO_DATE('17/12/2015', 'DD/MM/YYYY'));

INSERT INTO Assessment
(assessment_id, category)
VALUES (0, 'N');

INSERT INTO Facility
(fid, fac_name, classification, no_of_departments, capacity)
VALUES (1000, 'Wolf Hospital', '03', 3, 300);

INSERT INTO Facility
(fid, fac_name, classification, no_of_departments, capacity)
VALUES (1001, 'California Health Care', '02', 2, 150);

INSERT INTO Facility
(fid, fac_name, classification, no_of_departments, capacity)
VALUES (1002, 'Suny Medical Center', '01', 1, 10);

/* update the circular dependency in a transaction */

INSERT INTO Service_department
(dept_id, dept_name, director)
VALUES (0, 'Big dept', 0);

INSERT INTO Staff
(user_id, designation, hiredate, primary_dept)
VALUES (0, 'Medical', TO_DATE('17/12/2016', 'DD/MM/YYYY'), 0);

INSERT INTO Body_part
(code, part_name)
VALUES ('HED', 'HEAD');

INSERT INTO Symptom_scale
(scale_name)
VALUES ('1-10');

INSERT INTO Symptom
(symptom_name, code, priority, symptom_scale)
VALUES ('Headache', 'HDCH', 2, '1-10');

INSERT INTO Implies
(symptom, part)
VALUES ('HDCH', 'HED');

INSERT INTO Patient
(user_id, checkin_time_start, checkin_time_end, checkout_time)
VALUES (1, TO_DATE('17/12/2015', 'DD/MM/YYYY'), TO_DATE('17/12/2015', 'DD/MM/YYYY'), TO_DATE('17/12/2016', 'DD/MM/YYYY'));

INSERT INTO Patient
(user_id, checkin_time_start, checkin_time_end, checkout_time)
VALUES (2, TO_DATE('17/12/2015', 'DD/MM/YYYY'), TO_DATE('17/12/2015', 'DD/MM/YYYY'), null);

COMMIT;
