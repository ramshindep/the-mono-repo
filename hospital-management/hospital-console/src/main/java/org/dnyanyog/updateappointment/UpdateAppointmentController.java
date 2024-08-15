package org.dnyanyog.updateappointment;

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

public class UpdateAppointmentController {
	
	 @FXML private Button patient;

	  @FXML private Button cases;

	  @FXML private Button appointments;

	  @FXML private Button dashboard;

	  @FXML private Button logout;

	  @FXML private Button cancel;

	  @FXML private Button save;

	  @FXML private TextField patientName;

	  @FXML private DatePicker ExaminationDate;

	  @FXML private TextField patientId;

	  @FXML private TextField appointmentTime;
	  
	  @FXML private TextField appointmentId;
	  
	  @FXML private Button search;
	  
	  @FXML private TextField patientIdsearch;
	  
	  @FXML private TextField appointmentIdtext;
	  
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
	    new Appointment().show();
	  }
	  
	  public void users(ActionEvent event) {
		  new Users().show();
	  }

	  private RestAPIClient<AppointmentResponse> apiClient = new RestAPIClient<>();
	  
	  
	  
	  public void search(ActionEvent event) {
		    String searchValue = appointmentId.getText().trim();
		    String endpoint;

	
		    if (!searchValue.isEmpty()) {
		        
		    	 endpoint = ApiEndPoint.SEARCHAPPOINTMENT_BYAPPOINTMENTID.replace("{appointmentId}", searchValue);
		    } else {
		      
		        searchValue = patientIdsearch.getText().trim();
		        if (!searchValue.isEmpty()) {
		        	 endpoint = ApiEndPoint.SEARCHAPPOINTMENT_BYPATIENTID.replace("{patientId}", searchValue);

		        } else {
		         
		            System.out.println("Please provide either appointment ID or patient ID.");
		            return;
		        }
		    }

		    AppointmentResponse response = apiClient.sendGetRequest(endpoint, AppointmentResponse.class);

		    int statusCode = Integer.parseInt(response.getStatus());

		    if (statusCode >= 200 && statusCode < 300) {
		        System.out.println("*****Success*******");
		      AppointmentData    appointmentData = response.getAppointmentData();
		        
		      
		        patientName.setText(appointmentData.getPatientName());
		        String examinationDateStr = appointmentData.getExaminationDate();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        LocalDate examinationDate = LocalDate.parse(examinationDateStr, formatter);
		        ExaminationDate.setValue(examinationDate);
		        patientId.setText(appointmentData.getPatientId());
		        appointmentTime.setText(appointmentData.getAppointmentTime());
		        appointmentIdtext.setText(appointmentData.getAppointmentId());

		    } else {
		        System.out.println("*****Error*******");
		       
		    }
		}

	 
	  
	  public void save(ActionEvent event) {

		    UpdateAppointmentRequest updateAppointmentRequest = new UpdateAppointmentRequest();
		    updateAppointmentRequest.setAppointmentId(appointmentIdtext.getText());
		    updateAppointmentRequest.setPatientName(patientName.getText());
		    updateAppointmentRequest.setPatientId(patientId.getText());
		    updateAppointmentRequest.setExaminationDate(ExaminationDate.getValue().toString());
		    updateAppointmentRequest.setAppointmentTime(appointmentTime.getText());
		    
		   
		    

		    AppointmentResponse response =
		        apiClient.sendPostRequest(
		            ApiEndPoint.UPDATEAPPOINTMENT,
		            RequestMapper.convertToJsonString(updateAppointmentRequest),
		            AppointmentResponse.class);

		    System.out.print("*********************" + response);

		    try {

		      int statusCode = Integer.parseInt(response.getStatus());
		      if (statusCode >= 200 && statusCode < 300) {
		        System.out.println("*****Success*******");
		        System.out.println(response.getAppointmentData().getAppointmentStatus());
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
