package org.dnyanyog.addusers;

import org.apache.http.HttpStatus;
import org.dnyanyog.appointment.Appointment;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.common.ApiEndPoint;
import org.dnyanyog.common.RequestMapper;
import org.dnyanyog.common.RestAPIClient;
import org.dnyanyog.common.Style;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AddUsersController {

  @FXML private Button patient;

  @FXML private Button cases;

  @FXML private Button appointments;

  @FXML private Button dashboard;

  @FXML private Button logout;

  @FXML private Button cancel;

  @FXML private Button save;

  @FXML private TextField userName;

  @FXML private TextField password;

  @FXML private TextField email;

  @FXML private TextField mobileNumber;

  @FXML private ComboBox<String> role;

  @FXML private TextField confirmpassword;

  @FXML private AnchorPane mypane;
  
  @FXML private AnchorPane successfulMessage; 
  
  @FXML private AnchorPane conformationMessage;

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
    new Users().show();
  }

  private RestAPIClient<UserResponse> apiClient = new RestAPIClient<>();
  
  
  public void initialize() {
	

	  role.setItems(FXCollections.observableArrayList("", "DOCTOR", "ASSISTANT_DOCTOR", "RECEPTIONIST"));
	  role.getSelectionModel().select(0);
	}

  public void save(ActionEvent event) {
    email.setStyle(null);
    userName.setStyle(null);
    password.setStyle(null);
    confirmpassword.setStyle(null);
    role.setStyle(null);
    mobileNumber.setStyle(null);

    if (email.getText().isEmpty()) {
      email.setStyle(Style.emptyTextfield);
    }
    if (userName.getText().isEmpty()) {
      userName.setStyle(Style.emptyTextfield);
    }
    if (mobileNumber.getText().isEmpty()) {
      mobileNumber.setStyle(Style.emptyTextfield);
    }

    if (password.getText().isEmpty()) {
      password.setStyle(Style.emptyTextfield);
    }
    if (confirmpassword.getText().isEmpty()) {
      confirmpassword.setStyle(Style.emptyTextfield);
    }

    if (role.getSelectionModel().getSelectedIndex() == 0) {
      role.setStyle(Style.emptyTextfield);
    }

    if (!userName.getText().trim().isEmpty()
        && !password.getText().trim().isEmpty()
        && !email.getText().trim().isEmpty()
        && !confirmpassword.getText().trim().isEmpty()
        && !(role.getSelectionModel().getSelectedIndex() == 0)) {

      String passwordValue = password.getText();
      String confirmPasswordValue = confirmpassword.getText();

      if (passwordValue.equals(confirmPasswordValue)) {

        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setEmail(email.getText());
        addUserRequest.setUserName(userName.getText());
        addUserRequest.setPassword(password.getText());
        addUserRequest.setMobileNumber(mobileNumber.getText());
        addUserRequest.setRole((String) role.getValue());

        UserResponse response =
            apiClient.sendPostRequest(
                ApiEndPoint.ADDUSERS,
                RequestMapper.convertToJsonString(addUserRequest),
                UserResponse.class);
        
        System.out.print("*********************" + response);
        
        try {

          int statusCode = Integer.parseInt(response.getStatus());
          if (statusCode >= 200 && statusCode < 300) {
            System.out.println("*****Success*******");
            System.out.println(response.getUserData().getStatus());
            successfulMessage.setVisible(true);
            conformationMessage.setVisible(false);
            
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
	        pause.setOnFinished(e -> successfulMessage.setVisible(false)); 
	        pause.play(); 

          } else if ((statusCode == HttpStatus.SC_CONFLICT)) {
        	  successfulMessage.setVisible(false);
              conformationMessage.setVisible(false);
          } else {
            System.out.println("*****Error*******");
            System.out.println(response.getMessage());
          }
        } catch (NumberFormatException e) {
          System.out.println("Invalid status code format: " + response.getStatus());
        }
      } else {
    	  conformationMessage.setVisible(true);
    	  PauseTransition pause = new PauseTransition(Duration.seconds(2));
	        pause.setOnFinished(e -> conformationMessage.setVisible(false)); 
	        pause.play(); 
      }
    }
  }
}
