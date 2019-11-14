/*
 * Insert some dummy data in the schema
 *
 */

INSERT INTO address
(addr_id, addr_number, addr_street, addr_city, addr_state, addr_country)
VALUES (0, 1337, 'kingsford lane', 'Raleigh', 'NC', 'Wake');

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

INSERT INTO Facility
(fid, fac_name, classification, no_of_departments, capacity)
VALUES (0, 'Fac1', 'Hospital', 0, 100);

INSERT INTO Facility
(fid, fac_name, classification, no_of_departments, capacity)
VALUES (1, 'Fac2', 'ER', 0, 25);