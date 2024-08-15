package org.dnyanyog.deleteUser;

import java.util.Optional;

import org.dnyanyog.appointment.Appointment;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.common.ApiEndPoint;
import org.dnyanyog.common.RestAPIClient;
import org.dnyanyog.dashboard.Dashboard;
import org.dnyanyog.login.Login;
import org.dnyanyog.patient.Patient;
import org.dnyanyog.updatecases.CaseResponse;
import org.dnyanyog.updateusers.UserData;
import org.dnyanyog.updateusers.UserResponse;
import org.dnyanyog.users.Users;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class DeleteUserController {
  @FXML private Button patient;

  @FXML private Button cases;

  @FXML private Button appointments;

  @FXML private Button dashboard;

  @FXML private Button logout;

  @FXML private Button cancel;

  @FXML private Button Delete;

  @FXML private TextField userName;

  @FXML private TextField password;

  @FXML private TextField email;

  @FXML private TextField mobileNumber;

  @FXML private ComboBox<String> role;

  @FXML private TextField confirmpassword;

  @FXML private AnchorPane mypane;

  @FXML private AnchorPane successfulMessage;

  @FXML private AnchorPane conformationMessage;

  @FXML private TextField userId;

  @FXML private Button search;

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

    role.setItems(
        FXCollections.observableArrayList("", "DOCTOR", "ASSISTANT_DOCTOR", "RECEPTIONIST"));
    role.getSelectionModel().select(0);
  }

  public void search(ActionEvent event) {
    String searchValue = userId.getText().trim();
    String endpoint;

    if (!searchValue.isEmpty()) {
      endpoint = ApiEndPoint.GET_USERBY_USERID.replace("{userId}", searchValue);
    } else {
      System.out.println("Please provide either patient ID or patient ID.");
      return;
    }

    try {
      UserResponse response = apiClient.sendGetRequest(endpoint, UserResponse.class);

      if (response == null) {
        System.out.println("API response is null");
        return;
      }

      int statusCode = Integer.parseInt(response.getStatus());

      if (statusCode >= 200 && statusCode < 300) {
        System.out.println("*****Success*******");
        UserData userData = response.getUserData();

        if (userData == null) {
          System.out.println("Patient data is null");
          return;
        }

        userName.setText(userData.getUserName());
        email.setText(userData.getEmail());
        mobileNumber.setText(userData.getMobileNumber());
        role.setValue(userData.getRole());
        password.setText(userData.getPassword());

      } else {
        System.out.println("*****Error*******");
      }
    } catch (Exception e) {
      System.out.println("Exception occurred: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void delete(ActionEvent event) {
    String endpoint;

    String searchValue = userId.getText().trim();

    if (searchValue.isEmpty()) {
      System.out.println("Please provide a UserId .");
      return;
    }

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Inactive");
    alert.setHeaderText("Delete User");
    alert.setContentText("Do you want to delete User ");
    ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
    ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    alert.getButtonTypes().setAll(okButton, cancelButton);
    Optional<ButtonType> result = alert.showAndWait();

    if (result.isPresent() && result.get() == okButton) {

      endpoint = ApiEndPoint.DELETE_USER.replace("{userId}", searchValue);

      UserResponse response = apiClient.sendDeleteRequest(endpoint, UserResponse.class);

      int statusCode = Integer.parseInt(response.getStatus());
      if (statusCode >= 200 && statusCode < 300) {
        System.out.println("****Success******");
        successfulMessage.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> successfulMessage.setVisible(false));
        pause.play();

      } else {
        System.out.println("****Error******");
      }
    }
  }
}
