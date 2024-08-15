package org.dnyanyog.addpatient;

import org.apache.http.HttpStatus;
import org.dnyanyog.addcases.AddCaseRequest;
import org.dnyanyog.addcases.CaseResponse;
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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AddPatientController {
	
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

	  public void save(ActionEvent event) {

		    AddPatientRequest addPatientRequest = new AddPatientRequest();
		    
		    addPatientRequest.setPatientName(patientNameEnglish.getText());
		    addPatientRequest.setPatientNameMarathi(patientNameMarathi.getText());
		    addPatientRequest.setMobileNumber(mobileNo.getText());
		    addPatientRequest.setGender(gender.getValue());
		    addPatientRequest.setBirthDate(birthDate.getValue().toString());
		    addPatientRequest.setFirstExaminationDate(firstExaminationDate.getValue().toString());
		    addPatientRequest.setAddress(address.getText());
		    

		    PatientResponse response =
		        apiClient.sendPostRequest(
		            ApiEndPoint.ADDPATIENT,
		            RequestMapper.convertToJsonString(addPatientRequest),
		            PatientResponse.class);

		    System.out.print("*********************" + response);

		    try {

		      int statusCode = Integer.parseInt(response.getStatus());
		      if (statusCode >= 200 && statusCode < 300) {
		        System.out.println("*****Success*******");
		        System.out.println(response.getPatientData().getStatus());
		        
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
