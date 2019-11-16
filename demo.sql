
-- query 1 -- 1. Find all patients that were discharged but had negative experiences at any facility, list their names, facility, check-in date, discharge date and negative experiences

SELECT lu.lname,
     f.fac_name,
     p.checkin_time_end,
     p.checkout_time,
     ne.description
FROM Login_user lu,
     Patient p,
     Negative_experience ne,
     Facility f
WHERE p.user_id IN
(SELECT user_id FROM patient_has_report phr WHERE phr.rid IN
	 (SELECT rid FROM report_has_negative rhn)
)
AND ne.ne_code IN
(SELECT ne_code FROM report_has_negative rhn WHERE rhn.rid IN
	 (SELECT rid FROM patient_has_report phr WHERE phr.user_id = p.user_id)
)
AND lu.user_id = p.user_id
AND f.fid IN (SELECT fid FROM Facility_has_user fhu WHERE fhu.user_id = p.user_id);

-- query 2 -- Find facilities that did not have a negative experience for a specific period (to be given)
SELECT f.fac_name
FROM Facility f
WHERE f.fid NOT IN (
      SELECT fid FROM Facility_has_user fhu WHERE fhu.user_id IN (
      	     SELECT user_id FROM Patient p, Report r WHERE p.user_id IN (
	     	    SELECT user_id FROM patient_has_report phr WHERE phr.rid = r.rid
	     )
	     AND r.rid IN (
	     	    SELECT rid FROM report_has_negative rhn
	     )
	     AND p.checkout_time >= TO_DATE('2014/12/17', 'YYYY/MM/DD')
	     AND p.checkout_time <= TO_DATE('2018/12/17', 'YYYY/MM/DD')
      )
);

-- query 3 -- For each facility, find the facility that is sends the most referrals to.
SELECT f.fac_name, MAX(totalcount.excount)
FROM Facility f, (
      SELECT fhu.fid, COUNT(fhu.fid) excount
      FROM Facility_has_user fhu WHERE fhu.user_id IN (
      	     SELECT user_id FROM Patient p, Report r WHERE p.user_id IN (
	     	    SELECT user_id FROM patient_has_report phr WHERE phr.rid = r.rid
	     )
	     AND r.rid IN (
	     	    SELECT rid FROM report_has_ref rhr WHERE rhr.rid IN (
		    	   SELECT rid FROM Referral_status rs WHERE rs.fid = fhu.fid
		    )
	     )
      )
      GROUP BY fhu.fid
) totalcount
WHERE f.fid = totalcount.fid
GROUP BY f.fac_name;
-- for each facility
-- get the patients of the facility
-- get the report for the patient
-- get the ref stat for the report
-- count the ref stat's fid
-- take the max

-- query 4 -- Find facilities that had no negative experience for patients with cardiac symptoms
SELECT f.fac_name
FROM Facility f
WHERE f.fid NOT IN (
      SELECT fid FROM Facility_has_user fhu WHERE fhu.user_id IN (
      	     SELECT user_id FROM Patient p, Report r WHERE p.user_id IN (
	     	    SELECT user_id FROM patient_has_report phr WHERE phr.rid = r.rid
	     )
	     AND r.rid IN (
	     	    SELECT rid FROM report_has_negative rhn
	     )
	     AND fhu.user_id IN (
	     	    SELECT patient FROM has_symptom hs WHERE hs.part = 'HRT000'
	     )
      )
);

-- query 5 -- Find the facility with the most number of negative experiences (overall i.e. of either kind).
SELECT f.fac_name, MAX(totalcount.excount)
FROM Facility f, (
      SELECT fhu.fid, COUNT(fhu.fid) excount
      FROM Facility_has_user fhu WHERE fhu.user_id IN (
      	     SELECT user_id FROM Patient p, Report r WHERE p.user_id IN (
	     	    SELECT user_id FROM patient_has_report phr WHERE phr.rid = r.rid
	     )
	     AND r.rid IN (
	     	    SELECT rid FROM report_has_negative rhn
	     )
      )
      GROUP BY fhu.fid
) totalcount
WHERE f.fid = totalcount.fid
GROUP BY f.fac_name;

-- query 6 -- Find each facility, list the patient encounters with the top five longest check-in phases (i.e. time from begin check-in to when treatment phase begins (list the name of patient, date, facility, duration and list of symptoms).

SELECT f.fid, user_id, checkin_time_end, checkin_time_start
FROM Facility f, (
     SELECT * FROM (
     	      SELECT p.user_id, p.checkin_time_end, p.checkin_time_start
	      FROM Patient p       	     
       	      ORDER BY p.checkin_time_end - p.checkin_time_start
     ) WHERE ROWNUM <= 5
)
WHERE f.fid IN (
      SELECT fid FROM Facility_has_user fhu WHERE fhu.user_id = user_id
)
ORDER BY f.fid, checkin_time_end - checkin_time_start;
