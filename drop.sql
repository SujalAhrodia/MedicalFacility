-- first drop the constraint
ALTER TABLE service_department DROP CONSTRAINT director_fkey;

DROP TABLE Login_user cascade constraints;
DROP TABLE Address cascade constraints;
DROP TABLE Facility cascade constraints;
DROP TABLE Certification cascade constraints;
DROP TABLE Service cascade constraints;
DROP TABLE Equipment cascade constraints;
DROP TABLE Body_part cascade constraints;
DROP TABLE Symptom_scale cascade constraints;
DROP TABLE Assessment cascade constraints;
DROP TABLE Vital_signals cascade constraints;
DROP TABLE Negative_experience cascade constraints;
DROP TABLE Report cascade constraints;
DROP TABLE User_has_address cascade constraints;
DROP TABLE Facility_has_address cascade constraints;
DROP TABLE Certified cascade constraints;
DROP TABLE Patient cascade constraints;
DROP TABLE Service_department cascade constraints;
DROP TABLE Staff cascade constraints;
DROP TABLE Provides cascade constraints;
-- DROP TABLE Service_has_equipment cascade constraints;
DROP TABLE Associated_to cascade constraints;
DROP TABLE Symptom cascade constraints;
DROP TABLE Implies cascade constraints;
DROP TABLE Has_symptom cascade constraints;
DROP TABLE Scale_parameter cascade constraints;
DROP TABLE Evaluate cascade constraints;
DROP TABLE Consists_of cascade constraints;
DROP TABLE Patient_has_report cascade constraints;
DROP TABLE Report_has_negative cascade constraints;
DROP TABLE Referral_status cascade constraints;
DROP TABLE Report_has_ref cascade constraints;
DROP TABLE Reason cascade constraints;
DROP TABLE Vital_recordings cascade constraints;
DROP TABLE Works_in cascade constraints;
DROP TABLE Facility_has_User cascade constraints;
DROP TABLE Contains cascade constraints;

drop sequence seq;

commit ;
