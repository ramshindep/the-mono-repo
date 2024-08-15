package org.dnyanyog.cases;

import org.dnyanyog.addcases.AddCases;

import org.dnyanyog.appointment.Appointment;
import org.dnyanyog.dashboard.Dashboard;
import org.dnyanyog.deletecase.DeleteCase;
import org.dnyanyog.login.Login;
import org.dnyanyog.patient.Patient;
import org.dnyanyog.updatecases.UpdateCases;
import org.dnyanyog.users.Users;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class CasesController {
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
	private Button addCases;
	
	@FXML
	private Button updateCases;
	
	@FXML
	private Button deleteCases;
	
	@FXML
	private Button searchCases;
	
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

	
	public void addCases(ActionEvent event) {
		new AddCases().show();
	}

	public void updateCases(ActionEvent event) {
		new UpdateCases().show();
	}
	
	public void deleteCases(ActionEvent event) {
		new DeleteCase().show();
	}
}
