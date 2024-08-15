package org.dnyanyog.login;

import org.apache.http.HttpStatus;
import org.dnyanyog.addappointment.AddAppointmentRequest;
import org.dnyanyog.addappointment.AppointmentResponse;
import org.dnyanyog.common.ApiEndPoint;
import org.dnyanyog.common.RequestMapper;
import org.dnyanyog.common.RestAPIClient;
import org.dnyanyog.dashboard.Dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	
	@FXML
	private TextField mobileNumber;

	@FXML
	private Button next;

	@FXML
	private PasswordField password;
	
	 
	 private RestAPIClient<LoginResponse> apiClient = new RestAPIClient<>();
	  
	  public void next(ActionEvent event) {

		    LoginRequest loginRequest = new LoginRequest();
		    
		    loginRequest.setMobileNumber(mobileNumber.getText());
		    loginRequest.setPassword(password.getText());
		   
		    
		   
		    LoginResponse response =
		        apiClient.sendPostRequest(
		            ApiEndPoint.LOGIN,
		            RequestMapper.convertToJsonString(loginRequest),
		            LoginResponse.class);

		    System.out.print("*********************" + response);

		    try {

		      int statusCode = Integer.parseInt(response.getStatus());
		      if (statusCode >= 200 && statusCode < 300) {
		        System.out.println("*****Success*******");
		        new Dashboard().show();
		        

		      } else if ((statusCode == HttpStatus.SC_CONFLICT)) {

		      } else {
		        System.out.println("*****Error*******");
		        System.out.println(response.getMessage());
		      }
		    } catch (NumberFormatException e) {
		      System.out.println("Invalid status code format: " + response.getStatus());
		    }
		  }
}
