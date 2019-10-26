CREATE TABLE Address(
       addr_id INT,
       addr_number INT,
       addr_street CHAR,
       addr_city CHAR,
       addr_state CHAR,
       addr_country CHAR,
       PRIMARY KEY(addr_id);
);

CREATE TABLE Facility_has_address(
       fid INT,
       addr_id INT,
       PRIMARY KEY(fid) FOREIGN KEY REFERENCES(Address);
);

CREATE TABLE User_has_address(
       Uid INT FOREIGN KEY REFERENCES(Users),
       addr_id INT FOREIGN KEY REFERENCES(Address),
       PRIMARY KEY(Uid);
);

CREATE TABLE Facility(
       fid INT,
       fac_name CHAR,
       classification CHAR,
       no_of_departments INT,
       capacity INT,
       PRIMARY KEY(fid);
);

CREATE TABLE Certification(
       acronym CHAR,
       cert_name CHAR,
       PRIMARY KEY(acronym);
);

CREATE TABLE Certified(
       fid INT FOREIGN KEY REFERENCES(Facility),
       acronym CHAR,
       date_certified DATE,
       date_of_expiration DATE,
       PRIMARY KEY(fid);
);

CREATE TABLE Service_department(
       dept_id INT,
       dept_name CHAR,
       director INT FOREIGN KEY REFERENCES(Staff),
       PRIMARY KEY(dept_id);
);

CREATE TABLE Provides(
       service CHAR FOREIGN KEY REFERENCES(Service),
       dept_id INT FOREIGN KEY REFERENCES(Service_department),
       PRIMARY KEY(service);
);

CREATE TABLE Service(
       service_name CHAR,
       code CHAR,
       PRIMARY KEY(code);
);

CREATE TABLE Equipment(
       equip_name CHAR,
       PRIMARY KEY(equip_name);
);

CREATE TABLE Service_has_equipment(
       service CHAR FOREIGN KEY REFERENCES(Service),
       equipment CHAR FOREIGN KEY REFERENCES(Equipment),
       PRIMARY KEY(service, equipment);
);

CREATE TABLE Body_part(
       code CHAR,
       part_name CHAR,
       PRIMARY KEY(code);
);

CREATE TABLE Associated_to(
       service CHAR FOREIGN KEY REFERS(Service),
       part CHAR FOREIGN KEY REFERES(Body_part),
       PRIMARY KEY(service, part);
);

CREATE TABLE Symptom(
       symptom_name CHAR,
       code CHAR,
       priority INT,
       symptom_scale CHAR FOREIGN KEY REFERENCES(Symptom_scale),
       PRIMARY KEY(code);
);

CREATE TABLE Implies(
       symptom CHAR FOREIGN KEY REFERENCES(Symptom),
       part CHAR FOREIGN KEY REFERENCES(Body_part),
       PRIMARY KEY(symptom);
);

CREATE TABLE Has_symptom(
       symptom CHAR FOREIGN KEY REFERENCES(Symptom),
       patient CHAR FOREIGN KEY REFERENCES(Patient),
       value CHAR,
       duration CHAR,
       incident CHAR,
       recurring BOOLEAN,
       PRIMARY KEY(symptom, patient);
);

CREATE TABLE Symptom_scale(
       scale_name CHAR,
       PRIMARY KEY(scale_name);
);

CREATE TABLE Scale_parameter(
       scale_name CHAR FOREIGN KEY REFERENCES(Symptom_scale),
       param CHAR,
       severity INT,
       PRIMARY KEY(scale_name, param);
);

CREATE TABLE User(
       uid INT,
       Fname CHAR,
       Lname CHAR,
       ph_no CHAR,
       DoB DATE,
       PRIMARY KEY(uid);
);

CREATE TABLE Staff(
       uid INT FOREIGN KEY REFERENCES(User),
       designation CHAR,
       hiredate DATE,
       primary_dept FOREIGN KEY REFERENCES(Service_department),
       PRIMARY KEY(uid);
);

CREATE TABLE Patient(
       uid INT FOREIGN KEY REFERENCES(User),
       checkin_time DATE,
       checkout_time DATE,
       PRIMARY KEY(uid);
);

CREATE TABLE Assessment(
       assessment_id INT,
       category CHAR,
       PRIMARY KEY(assessment_id);
);

CREATE TABLE Evaluate(
       uid INT FOREIGN KEY REFERENCES(Patient),
       assessment_id INT FOREIGN KEY REFERENCES(Assessment),
       PRIMARY KEY(uid);
);

CREATE TABLE Consists_of(
       assessment_id INT FOREIGN KEY REFERENCES(Assessment),
       symptom CHAR FOREIGN KEY REFERENCES(Symptom),
       part CHAR FOREIGN KEY REFERENCES(Body_part),
       severity CHAR FOREIGN KEY REFERENCES(Symptom_scale),
       PRIMARY KEY(assessment_id);
);

CREATE TABLE Patient_has_report(
       uid INT FOREIGN KEY REFERENCES(Patient),
       rid INT FOREIGN KEY REFERENCES(Report),
);

CREATE TABLE Report(
       rid INT,
       treatment CHAR,
       discharge_status CHAR,
       PRIMARY KEY(rid);
);

CREATE TABLE Report_has_negative(
       rid INT FOREIGN KEY REFERENCES(Report),
       ne_code CHAR FOREIGN KEY REFERENCES(Negative_experience),
       PRIMARY KEY(rid, ne_code);
);

CREATE TABLE Negative_experience(
       ne_code INT,
       description CHAR,
       PRIMARY KEY(ne_code);
);


CREATE TABLE Report_has_ref(
       rid INT FOREIGN KEY REFERENCES(Report),
       rs_id CHAR FOREIGN KEY REFERENCES(Referral_status),
       PRIMARY KEY(rid, rs_id);
);

CREATE TABLE Referral_status(
       rs_id INT,
       fid INT FOREIGN KEY REFERENCES(Facility),
       referrer CHAR FOREIGN KEY REFERENCES(Staff),
       PRIMARY KEY(rs_id);
);

CREATE TABLE Report_has_reason(
       rid INT FOREIGN KEY REFERENCES(Report),
       reason CHAR FOREIGN KEY REFERENCES(Reason),
       PRIMARY KEY(rid, ne_code);
);

CREATE TABLE Reason(
       reason_code CHAR,
       service_name CHAR FOREIGN KEY REFERENCES(Service),
       description CHAR,
       PRIMARY KEY(reason_code);
);

CREATE TABLE Vital_signals(
       vital_id INT,
       temperature INT,
       blood_pressure CHAR,
       PRIMARY KEY(vital_id);
);

CREATE TABLE Vital_recordings(
       vital_id INT FOREIGN KEY REFERENCES(Vital_signals),
       patient INT FOREIGN KEY REFERENCES(Patient),
       staff INT FOREIGN KEY REFERENCES(Staff),
       PRIMARY KEY(vital_id, patient, staff);
);
