/*
 * Insert some dummy data in the schema
 *
 */

 -- Facilities
INSERT INTO Facility
(fid, fac_name, classification, no_of_departments, capacity)
VALUES (1000, 'Wolf Hospital', '03', 3, 300);

INSERT INTO Address
(addr_id, addr_number, addr_street, addr_city, addr_state,addr_zip)
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

-- 