package application.controller;

import java.io.IOException;

import application.model.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class RegisterController {

	@FXML TextField username;
	@FXML TextField password;
	@FXML TextField confpassword;
	
	DB db = DB.getDB();
	
	public void HandleRegisterButtonClick(ActionEvent event) throws Exception {
		try {
			if (password.getText().equals(confpassword.getText())) {
				db.addAccount(username.getText(), password.getText());
				Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml")); 
				Stage stage = new Stage();
				stage.setTitle("Login");
				stage.setScene(new Scene(root, 450, 450));
				stage.show();
			} else {
				
			}
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}