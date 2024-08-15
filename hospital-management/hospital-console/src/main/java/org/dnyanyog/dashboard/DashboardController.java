package org.dnyanyog.dashboard;

import org.dnyanyog.appointment.Appointment;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.login.Login;
import org.dnyanyog.patient.Patient;
import org.dnyanyog.users.Users;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashboardController {
	
	  @FXML private Button patient;

	  @FXML private Button cases;

	  @FXML private Button appointments;

	  @FXML private Button dashboard;

	  @FXML private Button logout;
	  
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
		  public void users(ActionEvent event) {
			  new Users().show();
		  }

		  public void logout(ActionEvent event) {
		    new Login().show();
		  }
}


