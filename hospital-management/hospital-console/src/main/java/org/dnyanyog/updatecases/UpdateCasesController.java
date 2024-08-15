package org.dnyanyog.updatecases;

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
import org.dnyanyog.users.Users;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class UpdateCasesController {
	
	
	  @FXML private Button patient;

	  @FXML private Button cases;

	  @FXML private Button appointments;

	  @FXML private Button dashboard;

	  @FXML private Button logout;
	  
	  @FXML private Button users;

	  @FXML private Button cancel;

	  @FXML private Button save;

	  @FXML private TextField patientName;

	  @FXML private DatePicker ExaminationDate;

	  @FXML private TextField patientId;

	  @FXML private TextField prescription;

	  @FXML private TextField symptoms;

	  @FXML private TextField caseNumber;

	  @FXML private AnchorPane mypane;
	  
	  @FXML private TextField caseNumberSearch;
	  
	  @FXML private TextField patientIdSearch;
	  
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
	    new Cases().show();
	    
	  }
	  
	  public void users(ActionEvent event) {
		  new Users().show();
	  }

	  private RestAPIClient<CaseResponse> apiClient = new RestAPIClient<>();

	  
	  
	  
	  public void search(ActionEvent event) {
		  
		    String searchValue = patientIdSearch.getText().trim();
		    String endpoint;

		    if (!searchValue.isEmpty()) {
		        endpoint = ApiEndPoint.SEARCHCASE_BYPATIENTID.replace("{patientId}", searchValue);
		    } else {
		        searchValue = caseNumberSearch.getText().trim();
		        if (!searchValue.isEmpty()) {
		            endpoint = ApiEndPoint.SEARCHCASE_BYCASENUMBER.replace("{caseNumber}", searchValue);
		        } else {
		            System.out.println("Please provide either case ID or patient ID.");
		            return;
		        }
		    }

		    try {
		    	CaseResponse response = apiClient.sendGetRequest(endpoint, CaseResponse.class);

		        if (response == null) {
		            System.out.println("API response is null");
		            return;
		        }

		        int statusCode = Integer.parseInt(response.getStatus());

		        if (statusCode >= 200 && statusCode < 300) {
		            System.out.println("*****Success*******");
		            CaseData caseData = response.getCaseData();

		            if (caseData == null) {
		                System.out.println("Patient data is null");
		                return;
		            }

		            patientName.setText(caseData.getPatientName());
		            patientId.setText(caseData.getPatientId());
		            caseNumber.setText(caseData.getCaseNumber());
		            symptoms.setText(caseData.getSymptoms());
		            prescription.setText(caseData.getPrescription());
		            
		             
		            String examinationDateStr = caseData.getExaminationDate();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		            if (examinationDateStr != null && !examinationDateStr.isEmpty()) {
		                LocalDate examinationDate = LocalDate.parse(examinationDateStr, formatter);
		                ExaminationDate.setValue(examinationDate);
		            } else {
		            	ExaminationDate.setValue(null);
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

	    UpdateCaseRequest updateCaseRequest = new UpdateCaseRequest();
	    updateCaseRequest.setCaseNumber(caseNumber.getText());
	    updateCaseRequest.setPatientName(patientName.getText());
	    updateCaseRequest.setPatientId(patientId.getText());
	    updateCaseRequest.setExaminationDate(ExaminationDate.getValue().toString());
	    
	    updateCaseRequest.setPrescription(prescription.getText());
	    updateCaseRequest.setSymptoms(symptoms.getText());

	    CaseResponse response =
	        apiClient.sendPostRequest(
	            ApiEndPoint.UPDATECASES,
	            RequestMapper.convertToJsonString(updateCaseRequest),
	            CaseResponse.class);

	    System.out.print("*********************" + response);

	    try {

	      int statusCode = Integer.parseInt(response.getStatus());
	      if (statusCode >= 200 && statusCode < 300) {
	        System.out.println("*****Success*******");
	        System.out.println(response.getCaseData().getCaseStatus());
	        successfulMessage.setVisible(true);
	        
	        PauseTransition pause = new PauseTransition(Duration.seconds(2));
	        pause.setOnFinished(e -> successfulMessage.setVisible(false)); 
	        pause.play(); 

	      } else if ((statusCode == HttpStatus.SC_CONFLICT)) {
	    	  successfulMessage.setVisible(false);
	      } else {
	        System.out.println("*****Error*******");
	        System.out.println(response.getMessage());
	      }
	    } catch (NumberFormatException e) {
	      System.out.println("Invalid status code format: " + response.getStatus());
	    }
	  } 
}
