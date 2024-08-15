package org.dnyanyog.patient;

import org.dnyanyog.addpatient.AddPatient;
import org.dnyanyog.appointment.Appointment;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.dashboard.Dashboard;
import org.dnyanyog.deletePatient.DeletePatient;
import org.dnyanyog.login.Login;
import org.dnyanyog.updatepatient.UpdatePatient;
import org.dnyanyog.users.Users;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class PatientController {
	@FXML
	private Button patient;

	@FXML
	private Button cases;

	@FXML
	private Button appointments;

	@FXML
	private Button dashboard;

	@FXML
	private Button logout;
	
	@FXML
	private Button users;
	
	@FXML
	private Button addPatient;
	
	@FXML
	private Button updatePatient;
	
	@FXML
	private Button deletePatient;
	
	@FXML
	private Button searchPatient;
	
	@FXML
	private AnchorPane mypane;

	
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
	
	public void users(ActionEvent event) {
		new Users().show();
	}

	
	public void addPatient(ActionEvent event) {
		new AddPatient().show();
	}

	public void updatePatient(ActionEvent event) {
		new UpdatePatient().show();
	}
	
	public void deletePatient(ActionEvent event) {
		new DeletePatient().show();
	}
	
	
}
