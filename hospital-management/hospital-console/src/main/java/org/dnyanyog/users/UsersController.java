package org.dnyanyog.users;

import org.dnyanyog.addusers.AddUser;

import org.dnyanyog.appointment.Appointment;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.dashboard.Dashboard;
import org.dnyanyog.deleteUser.DeleteUser;
import org.dnyanyog.login.Login;
import org.dnyanyog.patient.Patient;
import org.dnyanyog.updateusers.UpdateUser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class UsersController {
	
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
	private Button addUser;
	
	@FXML
	private Button updateUser;
	
	@FXML
	private Button deleteUser;
	
	@FXML
	private Button searchUser;
	
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

	
	public void addUser(ActionEvent event) {
		new AddUser().show();
	}

	public void updateUser(ActionEvent event) {
		new UpdateUser().show();
	}
	
	public void deleteUser(ActionEvent event) {
		new DeleteUser().show();
	}
	
	
}
