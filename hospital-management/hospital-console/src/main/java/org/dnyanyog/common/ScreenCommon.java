package org.dnyanyog.common;

import java.io.IOException;

import org.dnyanyog.stage.StageMaster;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class ScreenCommon {
	public void show() {
		try{
			System.out.println("FXML File Name and Path"+getClass().getResource(getClass().getSimpleName()+".fxml"));
			Parent actorGroup =FXMLLoader.load(getClass().getResource(getClass().getSimpleName()+".fxml"));
			StageMaster.getStage().setScene(new Scene(actorGroup));
			StageMaster.getStage().show();
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
}
