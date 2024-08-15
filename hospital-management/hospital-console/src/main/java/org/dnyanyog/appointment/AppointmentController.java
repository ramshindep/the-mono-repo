package org.dnyanyog.appointment;

import org.dnyanyog.addappointment.AddAppointment;
import org.dnyanyog.addpatient.AddPatient;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.dashboard.Dashboard;
import org.dnyanyog.deleteappointment.DeleteAppointment;
import org.dnyanyog.login.Login;
import org.dnyanyog.patient.Patient;
import org.dnyanyog.updateappointment.UpdateAppointment;
import org.dnyanyog.users.Users;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class AppointmentController {
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
	private Button addAppointment;
	
	@FXML
	private Button updateAppointment;
	
	@FXML
	private Button deleteAppointment;
	
	@FXML
	private Button searchAppointment;
	
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

	
	public void addAppointment(ActionEvent event) {
		new AddAppointment().show();
	}

	public void updateAppointment(ActionEvent event) {
		new UpdateAppointment().show();
	}
	
	public void deleteAppointment(ActionEvent event) {
		new DeleteAppointment().show();
	}
	
}
