CREATE TABLE Address(
       addr_id int,
       addr_number int,
       addr_street char,
       addr_city char,
       addr_state char,
       addr_country char,
       PRIMARY KEY(addr_id)
);

CREATE TABLE Login_user(
       uid int,
       Fname char,
       Lname char,
       ph_no char,
       DoB DATE,
       PRIMARY KEY(uid)
);

CREATE TABLE Facility(
       fid int,
       fac_name char,
       classification char,
       no_of_departments int,
       capacity int,
       PRIMARY KEY(fid)
);

CREATE TABLE Certification(
       acronym char,
       cert_name char,
       PRIMARY KEY(acronym)
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

CREATE TABLE Body_part(
       code char,
       part_name char,
       PRIMARY KEY(code)
);

CREATE TABLE Symptom_scale(
       scale_name char,
       PRIMARY KEY(scale_name)
);

CREATE TABLE Assessment(
       assessment_id int,
       category char,
       PRIMARY KEY(assessment_id)
);

CREATE TABLE Vital_signals(
       vital_id int,
       temperature int,
       blood_pressure char,
       PRIMARY KEY(vital_id)
);

CREATE TABLE Negative_experience(
       ne_code int,
       description char,
       PRIMARY KEY(ne_code)
);

CREATE TABLE Report(
       rid int,
       treatment char,
       discharge_status char,
       PRIMARY KEY(rid)
);

/* ------ begin foreign key members ------ */

CREATE TABLE User_has_address(
       uid int,
       FOREIGN KEY (uid) REFERENCES Login_user(uid),
       addr_id int,
       FOREIGN KEY (addr_id) REFERENCES Address(addr_id),
       PRIMARY KEY(uid)
);

CREATE TABLE Facility_has_address(
       fid int,
       FOREIGN KEY (fid) REFERENCES Facility(fid),
       addr_id int,
       FOREIGN KEY (addr_id) REFERENCES Address(addr_id),
       PRIMARY KEY (fid)
);

CREATE TABLE Certified(
       fid int,
       FOREIGN KEY (fid) REFERENCES Facility(fid),
       acronym char,
       date_certified DATE,
       date_of_expiration DATE,
       PRIMARY KEY(fid)
);

CREATE TABLE Patient(
       uid int,
       FOREIGN KEY (uid) REFERENCES Login_user(uid),
       checkin_time DATE,
       checkout_time DATE,
       PRIMARY KEY(uid)
);

CREATE TABLE Service_department(
       dept_id int,
       dept_name char,
       director int,
       /*FOREIGN KEY REFERENCES (director) Staff(uid),*/
       PRIMARY KEY(dept_id)
);

CREATE TABLE Staff(
       uid int,
       FOREIGN KEY (uid) REFERENCES Login_user(uid),
       designation char,
       hiredate DATE,
       primary_dept int,
       FOREIGN KEY (primary_dept) REFERENCES Service_department(dept_id),
       PRIMARY KEY(uid)
);

CREATE TABLE Provides(
       service char,
       FOREIGN KEY (service) REFERENCES Service(code),
       dept_id int,
       FOREIGN KEY (dept_id) REFERENCES Service_department(dept_id),
       PRIMARY KEY(service)
);

CREATE TABLE Service_has_equipment(
       service char,
       FOREIGN KEY (service) REFERENCES Service(code),
       equipment char,
       FOREIGN KEY (equipment) REFERENCES Equipment(equip_name),
       PRIMARY KEY(service, equipment)
);

CREATE TABLE Associated_to(
       service char,
       FOREIGN KEY (service) REFERENCES Service(code),
       part char,
       FOREIGN KEY (part) REFERENCES Body_part(code),
       PRIMARY KEY(service, part)
);

CREATE TABLE Symptom(
       symptom_name char,
       code char,
       priority int,
       symptom_scale char,
       FOREIGN KEY (symptom_scale) REFERENCES Symptom_scale(scale_name),
       PRIMARY KEY(code)
);

CREATE TABLE Implies(
       symptom char,
       FOREIGN KEY (symptom) REFERENCES Symptom(code),
       part char,
       FOREIGN KEY (part) REFERENCES Body_part(code),
       PRIMARY KEY(symptom)
);

CREATE TABLE Has_symptom(
       symptom char,
       FOREIGN KEY (symptom) REFERENCES Symptom(code),
       patient int,
       FOREIGN KEY (patient) REFERENCES Patient(uid),
       value char,
       duration char,
       incident char,
       recurring BOOLEAN,
       PRIMARY KEY(symptom, patient)
);

CREATE TABLE Scale_parameter(
       scale_name char,
       FOREIGN KEY (scale_name) REFERENCES Symptom_scale(scale_name),
       param char,
       severity int,
       PRIMARY KEY(scale_name, param)
);

CREATE TABLE Evaluate(
       uid int,
       FOREIGN KEY (uid) REFERENCES Patient(uid),
       assessment_id int,
       FOREIGN KEY (assessment_id) REFERENCES Assessment(assessment_id),
       PRIMARY KEY(uid)
);

CREATE TABLE Consists_of(
       assessment_id int,
       FOREIGN KEY (assessment_id) REFERENCES Assessment(assessment_id),
       symptom char,
       FOREIGN KEY (symptom) REFERENCES Symptom(code),
       part char,
       FOREIGN KEY (part) REFERENCES Body_part(code),
       severity char,
       FOREIGN KEY (severity) REFERENCES Symptom_scale(scale_name),
       PRIMARY KEY(assessment_id)
);

CREATE TABLE Patient_has_report(
       uid int,
       FOREIGN KEY (uid) REFERENCES Patient(uid),
       rid int,
       FOREIGN KEY (rid) REFERENCES Report(rid),
       PRIMARY KEY(uid, rid)
);

CREATE TABLE Report_has_negative(
       rid int,
       FOREIGN KEY (rid) REFERENCES Report(rid),
       ne_code int,
       FOREIGN KEY (ne_code) REFERENCES Negative_experience(ne_code),
       PRIMARY KEY(rid, ne_code)
);

CREATE TABLE Referral_status(
       rs_id int,
       fid int,
       FOREIGN KEY (fid) REFERENCES Facility(fid),
       referrer int,
       FOREIGN KEY (referrer) REFERENCES Staff(uid),
       PRIMARY KEY(rs_id)
);

CREATE TABLE Report_has_ref(
       rid int,
       FOREIGN KEY (rid) REFERENCES Report(rid),
       rs_id int,
       FOREIGN KEY (rs_id) REFERENCES Referral_status(rs_id),
       PRIMARY KEY(rid, rs_id)
);

CREATE TABLE Reason(
       reason_code char,
       service_name char,
       FOREIGN KEY (service_name) REFERENCES Service(code),
       description char,
       PRIMARY KEY(reason_code)
);

CREATE TABLE Report_has_reason(
       rid int,
       FOREIGN KEY (rid) REFERENCES Report(rid),
       reason char,
       FOREIGN KEY (reason) REFERENCES Reason(reason_code),
       PRIMARY KEY(rid)
);

CREATE TABLE Vital_recordings(
       vital_id int,
       FOREIGN KEY (vital_id) REFERENCES Vital_signals(vital_id),
       patient int,
       FOREIGN KEY (patient) REFERENCES Patient(uid),
       staff int,
       FOREIGN KEY (staff) REFERENCES Staff(uid),
       PRIMARY KEY(vital_id, patient, staff)
);
