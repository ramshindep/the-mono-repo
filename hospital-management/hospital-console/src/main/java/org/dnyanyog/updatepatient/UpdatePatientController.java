package org.dnyanyog.updatepatient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.http.HttpStatus;
import org.dnyanyog.appointment.Appointment;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.common.ApiEndPoint;
import org.dnyanyog.common.RequestMapper;
import org.dnyanyog.common.RestAPIClient;
import org.dnyanyog.dashboard.Dashboard;
import org.dnyanyog.login.Login;
import org.dnyanyog.patient.Patient;
import org.dnyanyog.updateappointment.AppointmentData;
import org.dnyanyog.updateappointment.AppointmentResponse;
import org.dnyanyog.updateappointment.UpdateAppointmentRequest;
import org.dnyanyog.users.Users;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class UpdatePatientController {
	
	@FXML private Button patient;

	  @FXML private Button cases;

	  @FXML private Button appointments;

	  @FXML private Button dashboard;

	  @FXML private Button logout;

	  @FXML private Button cancel;

	  @FXML private Button save;

	  @FXML private TextField patientNameEnglish;
	  
	  @FXML private TextField patientNameMarathi;

	  @FXML private DatePicker firstExaminationDate;
	  
	  @FXML private DatePicker birthDate;

	  @FXML private TextField mobileNo;

	  @FXML private ComboBox<String> gender;

	  @FXML private TextField address;

	  @FXML private AnchorPane mypane;
	  
	  @FXML private TextField patientId;
	  
	  @FXML private TextField patientIdsearch;
	  
	  @FXML private TextField patientNamesearch;
	  
	  @FXML private Button search;
	  @FXML private AnchorPane successfulMessage; 
	  
	  

	  public void patient(ActionEvent event) {
	    new Patient().show();
	  }

	  public void cases(ActionEvent event) {
	    new Cases().show();
	  }

	  public void appointments(ActionEvent event) {
	    new Appointment().show();
	  }

	  public void dashboard(ActionEvent event) {
	    new Dashboard().show();
	  }

	  public void logout(ActionEvent event) {
	    new Login().show();
	  }

	  public void cancel(ActionEvent event) {
	    new Patient().show();
	  }
	  
	  public void users(ActionEvent event) {
		  new Users().show();
	  }
	  public void initialize() {
			

		  gender.setItems(FXCollections.observableArrayList("", "MALE", "FEMALE"));
		  gender.getSelectionModel().select(0);
		}
	  
 private RestAPIClient<PatientResponse> apiClient = new RestAPIClient<>();
	  
	  
	  
 public void search(ActionEvent event) {
	    String searchValue = patientIdsearch.getText().trim();
	    String endpoint;

	    if (!searchValue.isEmpty()) {
	        endpoint = ApiEndPoint.SEARCHPATIENT_BYPATIENTID.replace("{patientId}", searchValue);
	    } else {
	        searchValue = patientNamesearch.getText().trim();
	        if (!searchValue.isEmpty()) {
	            endpoint = ApiEndPoint.SEARCH_PATIENT_BY_PATIENTNAME.replace("{patientName}", searchValue);
	        } else {
	            System.out.println("Please provide either PatientName or patient ID.");
	            return;
	        }
	    }

	    try {
	        PatientResponse response = apiClient.sendGetRequest(endpoint, PatientResponse.class);

	        if (response == null) {
	            System.out.println("API response is null");
	            return;
	        }

	        int statusCode = Integer.parseInt(response.getStatus());

	        if (statusCode >= 200 && statusCode < 300) {
	            System.out.println("*****Success*******");
	            PatientData patientData = response.getPatientData();

	            if (patientData == null) {
	                System.out.println("Patient data is null");
	                return;
	            }

	            patientId.setText(patientData.getPatientId());
	            patientNameEnglish.setText(patientData.getPatientName());
	            patientNameMarathi.setText(patientData.getPatientNameMarathi());
	            mobileNo.setText(patientData.getMobileNumber());
	            gender.setValue(patientData.getGender());
	            address.setText(patientData.getAddress());

	            String examinationDateStr = patientData.getFirstExaminationDate();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	            if (examinationDateStr != null && !examinationDateStr.isEmpty()) {
	                LocalDate examinationDate = LocalDate.parse(examinationDateStr, formatter);
	                firstExaminationDate.setValue(examinationDate);
	            } else {
	                firstExaminationDate.setValue(null);
	            }

	            String birthDateStr = patientData.getBirthDate();
	            if (birthDateStr != null && !birthDateStr.isEmpty()) {
	                LocalDate birthDateValue = LocalDate.parse(birthDateStr, formatter);
	                birthDate.setValue(birthDateValue);
	            } else {
	                birthDate.setValue(null);
	            }
	        } else {
	            System.out.println("*****Error*******");
	           
	        }
	    } catch (Exception e) {
	        System.out.println("Exception occurred: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


	 
	  
	  public void save(ActionEvent event) {

		    UpdatePatientRequest updatePatientRequest = new UpdatePatientRequest();
		    updatePatientRequest.setPatientId(patientId.getText());
		    updatePatientRequest.setPatientName(patientNameEnglish.getText());
		    updatePatientRequest.setPatientNameMarathi(patientNameMarathi.getText());
		    updatePatientRequest.setMobileNumber(mobileNo.getText());
		    updatePatientRequest.setBirthDate(birthDate.getValue().toString());
		    updatePatientRequest.setAddress(address.getText());
		    updatePatientRequest.setFirstExaminationDate(firstExaminationDate.getValue().toString());
		    updatePatientRequest.setGender(gender.getValue());
		    
		 
		    PatientResponse response =
		        apiClient.sendPostRequest(
		            ApiEndPoint.UPDATEPATIENT,
		            RequestMapper.convertToJsonString(updatePatientRequest),
		            PatientResponse.class);

		    System.out.print("*********************" + response);

		    try {

		      int statusCode = Integer.parseInt(response.getStatus());
		      if (statusCode >= 200 && statusCode < 300) {
		        System.out.println("*****Success*******");
		        System.out.println(response.getPatientData().getStatus());
		        successfulMessage.setVisible(true); 

		      } else if ((statusCode == HttpStatus.SC_CONFLICT)) {
		    	  successfulMessage.setVisible(true);
		    	  PauseTransition pause = new PauseTransition(Duration.seconds(2));
			        pause.setOnFinished(e -> successfulMessage.setVisible(false)); 
			        pause.play(); 
		      } else {
		        System.out.println("*****Error*******");
		        System.out.println(response.getMessage());
		      }
		    } catch (NumberFormatException e) {
		      System.out.println("Invalid status code format: " + response.getStatus());
		    }
		  }
	  
}
