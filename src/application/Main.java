package application;

import java.io.File;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	public static Stage primaryStage;
	public static String chemin;
	public static String cheminTemp;

	@Override //test3
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/LoginJfoenix.fxml"));
			Scene scene = new Scene(root,1180,702);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			Main.primaryStage = primaryStage;

			Main.chemin = System.getProperty("user.dir").toString().replaceAll("\\\\", "\\\\\\\\")+"\\\\Base.xls";
			Main.cheminTemp = System.getProperty("user.dir").toString().replaceAll("\\\\", "\\\\\\\\")+"\\\\temp.xls";

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getStage(){
		return primaryStage;
	}
}
//Main
