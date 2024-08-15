package org.dnyanyog.common;

public class ApiEndPoint {
	
	private final static String DIRECTORY_BASE = "http://localhost:8080";
	private final static String CASES_BASE="http://localhost:8080";
	private final static String PATIENT_BASE="http://localhost:8080";
	private final static String APPOINTMENT_BASE="http://localhost:8080";	
	
	// Directory Service
	public final static String LOGIN = DIRECTORY_BASE + "/api/v1/directory/validate";
	public final static String ADDUSERS = DIRECTORY_BASE + "/api/v1/directory/add";
	public final static String UPDATEUSERS=DIRECTORY_BASE + "/api/v1/directory/update";
	public final static String GET_USERBY_USERID=DIRECTORY_BASE+ "/api/v1/directory/{userId}";
	public final static String DELETE_USER=DIRECTORY_BASE+"/api/v1/directory/{userId}";
	


	
	//Case-service
	public final static String ADDCASES=CASES_BASE + "/api/v1/case/add";
	public final static String UPDATECASES=CASES_BASE+ "/api/v1/case/update";
	public final static String SEARCHCASE_BYCASENUMBER=CASES_BASE+"/api/v1/case/caseNumber/{caseNumber}";
	public final static String SEARCHCASE_BYPATIENTID=CASES_BASE + "/api/v1/case/patientId/{patientId}";
	public final static String DELETE_CASE_BYCASENUMBER=CASES_BASE+"/api/v1/case/{caseNumber}";
	
	
	
	//patient-service
	public final static String ADDPATIENT= PATIENT_BASE +"/api/v1/patient/add";
	public final static String UPDATEPATIENT=PATIENT_BASE+"/api/v1/patient/update";
	public final static String SEARCHPATIENT_BYPATIENTID=PATIENT_BASE +"/api/v1/patient/{patientId}";
	public final static String DELETE_PATIENT_BY_PATIENTID=PATIENT_BASE+"/api/v1/patient/{patientId}";
	public final static String SEARCH_PATIENT_BY_PATIENTNAME=PATIENT_BASE+"/api/v1/patient/patientName/{patientName}";
	
	
	//APPOINTMENT-SERVICE
	public final static String ADDAPPOINTMENT=APPOINTMENT_BASE+"/api/v1/appointment/add";
	public final static String UPDATEAPPOINTMENT=APPOINTMENT_BASE+"/api/v1/appointment/update";
	public final static String SEARCHAPPOINTMENT_BYAPPOINTMENTID=APPOINTMENT_BASE+"/api/v1/appointment/appointmentId/{appointmentId}";
	public final static String SEARCHAPPOINTMENT_BYPATIENTID=APPOINTMENT_BASE+"/api/v1/appointment/patientId/{patientId}";
	public final static String DELETE_APPOINTMENT_BYAPPOINTMENTID=APPOINTMENT_BASE+"/api/v1/appointment/{appointmentId}";
	
	
	
	
	
}
