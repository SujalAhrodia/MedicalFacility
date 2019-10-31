CREATE TABLE Address(
       addr_id int,
       addr_number int,
       addr_street char,
       addr_city char,
       addr_state char,
       addr_country char,
       PRIMARY KEY(addr_id)
);

CREATE TABLE User_has_address(
       Uid int FOREIGN KEY REFERENCES(Users),
       addr_id int FOREIGN KEY REFERENCES(Address),
       PRIMARY KEY(Uid)
);

CREATE TABLE Facility(
       fid int,
       fac_name char,
       classification char,
       no_of_departments int,
       capacity int,
       PRIMARY KEY(fid)
);

CREATE TABLE Facility_has_address(
       fid int,
       addr_id int,
       PRIMARY KEY (fid),
       FOREIGN KEY (fid) REFERENCES Facility(fid)
       FOREIGN KEY (addr_id) REFERENCES Address(addr_id)
);

CREATE TABLE Certification(
       acronym char,
       cert_name char,
       PRIMARY KEY(acronym)
);

CREATE TABLE Certified(
       fid int FOREIGN KEY REFERENCES(Facility),
       acronym char,
       date_certified DATE,
       date_of_expiration DATE,
       PRIMARY KEY(fid)
);

CREATE TABLE Service_department(
       dept_id int,
       dept_name char,
       director int FOREIGN KEY REFERENCES(Staff),
       PRIMARY KEY(dept_id)
);

CREATE TABLE Provides(
       service char FOREIGN KEY REFERENCES(Service),
       dept_id int FOREIGN KEY REFERENCES(Service_department),
       PRIMARY KEY(service)
);

CREATE TABLE Service(
       service_name char,
       code char,
       PRIMARY KEY(code)
);

CREATE TABLE Equipment(
       equip_name char,
       PRIMARY KEY(equip_name)
);

CREATE TABLE Service_has_equipment(
       service char FOREIGN KEY REFERENCES(Service),
       equipment char FOREIGN KEY REFERENCES(Equipment),
       PRIMARY KEY(service, equipment)
);

CREATE TABLE Body_part(
       code char,
       part_name char,
       PRIMARY KEY(code)
);

CREATE TABLE Associated_to(
       service char FOREIGN KEY REFERENCES(Service),
       part char FOREIGN KEY REFERENCES(Body_part),
       PRIMARY KEY(service, part)
);

CREATE TABLE Symptom(
       symptom_name char,
       code char,
       priority int,
       symptom_scale char FOREIGN KEY REFERENCES(Symptom_scale),
       PRIMARY KEY(code)
);

CREATE TABLE Implies(
       symptom char FOREIGN KEY REFERENCES(Symptom),
       part char FOREIGN KEY REFERENCES(Body_part),
       PRIMARY KEY(symptom)
);

CREATE TABLE Has_symptom(
       symptom char FOREIGN KEY REFERENCES(Symptom),
       patient char FOREIGN KEY REFERENCES(Patient),
       value char,
       duration char,
       incident char,
       recurring BOOLEAN,
       PRIMARY KEY(symptom, patient)
);

CREATE TABLE Symptom_scale(
       scale_name char,
       PRIMARY KEY(scale_name)
);

CREATE TABLE Scale_parameter(
       scale_name char FOREIGN KEY REFERENCES(Symptom_scale),
       param char,
       severity int,
       PRIMARY KEY(scale_name, param)
);

CREATE TABLE User(
       uid int,
       Fname char,
       Lname char,
       ph_no char,
       DoB DATE,
       PRIMARY KEY(uid)
);

CREATE TABLE Staff(
       uid int FOREIGN KEY REFERENCES(User),
       designation char,
       hiredate DATE,
       primary_dept FOREIGN KEY REFERENCES(Service_department),
       PRIMARY KEY(uid)
);

CREATE TABLE Patient(
       uid int FOREIGN KEY REFERENCES(User),
       checkin_time DATE,
       checkout_time DATE,
       PRIMARY KEY(uid)
);

CREATE TABLE Assessment(
       assessment_id int,
       category char,
       PRIMARY KEY(assessment_id)
);

CREATE TABLE Evaluate(
       uid int FOREIGN KEY REFERENCES(Patient),
       assessment_id int FOREIGN KEY REFERENCES(Assessment),
       PRIMARY KEY(uid)
);

CREATE TABLE Consists_of(
       assessment_id int FOREIGN KEY REFERENCES(Assessment),
       symptom char FOREIGN KEY REFERENCES(Symptom),
       part char FOREIGN KEY REFERENCES(Body_part),
       severity char FOREIGN KEY REFERENCES(Symptom_scale),
       PRIMARY KEY(assessment_id)
);

CREATE TABLE Patient_has_report(
       uid int FOREIGN KEY REFERENCES(Patient),
       rid int FOREIGN KEY REFERENCES(Report),
);

CREATE TABLE Report(
       rid int,
       treatment char,
       discharge_status char,
       PRIMARY KEY(rid)
);

CREATE TABLE Report_has_negative(
       rid int FOREIGN KEY REFERENCES(Report),
       ne_code char FOREIGN KEY REFERENCES(Negative_experience),
       PRIMARY KEY(rid, ne_code)
);

CREATE TABLE Negative_experience(
       ne_code int,
       description char,
       PRIMARY KEY(ne_code)
);


CREATE TABLE Report_has_ref(
       rid int FOREIGN KEY REFERENCES(Report),
       rs_id char FOREIGN KEY REFERENCES(Referral_status),
       PRIMARY KEY(rid, rs_id)
);

CREATE TABLE Referral_status(
       rs_id int,
       fid int FOREIGN KEY REFERENCES(Facility),
       referrer char FOREIGN KEY REFERENCES(Staff),
       PRIMARY KEY(rs_id)
);

CREATE TABLE Report_has_reason(
       rid int FOREIGN KEY REFERENCES(Report),
       reason char FOREIGN KEY REFERENCES(Reason),
       PRIMARY KEY(rid, ne_code)
);

CREATE TABLE Reason(
       reason_code char,
       service_name char FOREIGN KEY REFERENCES(Service),
       description char,
       PRIMARY KEY(reason_code)
);

CREATE TABLE Vital_signals(
       vital_id int,
       temperature int,
       blood_pressure char,
       PRIMARY KEY(vital_id)
);

CREATE TABLE Vital_recordings(
       vital_id int FOREIGN KEY REFERENCES(Vital_signals),
       patient int FOREIGN KEY REFERENCES(Patient),
       staff int FOREIGN KEY REFERENCES(Staff),
       PRIMARY KEY(vital_id, patient, staff)
);
