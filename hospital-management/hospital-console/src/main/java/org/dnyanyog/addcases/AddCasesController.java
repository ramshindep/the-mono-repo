package org.dnyanyog.addcases;

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

public class AddCasesController {

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

  private RestAPIClient<CaseResponse> apiClient = new RestAPIClient<>();

  public void save(ActionEvent event) {

    AddCaseRequest addCaseRequest = new AddCaseRequest();
    addCaseRequest.setPatientName(patientName.getText());
    addCaseRequest.setPatientId(patientId.getText());
    addCaseRequest.setExaminationDate(ExaminationDate.getValue().toString());
    
    addCaseRequest.setPrescription(prescription.getText());
    addCaseRequest.setSymptoms(symptoms.getText());

    CaseResponse response =
        apiClient.sendPostRequest(
            ApiEndPoint.ADDCASES,
            RequestMapper.convertToJsonString(addCaseRequest),
            CaseResponse.class);

    System.out.print("*********************" + response);

    try {

      int statusCode = Integer.parseInt(response.getStatus());
      if (statusCode >= 200 && statusCode < 300) {
        System.out.println("*****Success*******");
        System.out.println(response.getCaseData().getCaseStatus());
        successfulMessage.setVisible(true);

      } else if ((statusCode == HttpStatus.SC_CONFLICT)) {
    	  successfulMessage.setVisible(false);
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
