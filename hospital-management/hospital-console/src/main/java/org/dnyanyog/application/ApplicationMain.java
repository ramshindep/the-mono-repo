package org.dnyanyog.application;


import org.dnyanyog.deleteUser.DeleteUser;
import org.dnyanyog.login.Login;
import org.dnyanyog.stage.StageMaster;
import org.dnyanyog.updateusers.UpdateUser;

import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationMain extends Application {

	public static void main(String args[]) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		StageMaster.setStage(primaryStage);
		new Login().show();
	
	}
}