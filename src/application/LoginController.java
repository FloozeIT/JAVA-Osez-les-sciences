package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXDialog.DialogTransition;

import Organisateur.SidePanelContentController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.ResourceBundle;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginController implements Initializable {
	
    @FXML
    private JFXButton buttonConnexion;
    
    @FXML
    private JFXButton buttonCreate;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private Label lblStatus;

    @FXML
    private JFXPasswordField txtPassword;
    
    @FXML
    private StackPane stackPane;
    
    @FXML
    private JFXDrawer drawerCreate;
    
    @FXML
    private AnchorPane paneColor;

    @FXML
    private AnchorPane paneInfo;
    
    @FXML
    private AnchorPane rip;
    
    @FXML
    private Pane trans;
   
    @FXML
	public static JFXDrawer drawerCreateP;
    public static AnchorPane paneInfoP, paneColorP;
    public static Stage primaryStage;
    public static JFXTextField txtUsernameP;
    public static String name;
    public static String username;
    

    @FXML
    void LoginJfoenix(ActionEvent event) throws Exception{
    	txtUsernameP=txtUsername;
		
    	Workbook workbook = null;
    	String User = System.getProperty("user.name");
		try {
			File ins = new File(Main.chemin);
			if(ins.isFile()==false){
				lblStatus.setText("You are the first User ! Please, create an account");
			}
			else{
				workbook = Workbook.getWorkbook(new File(Main.chemin));
				Sheet sheet = workbook.getSheet(0);
				
				String checkUsername, checkPassword;
				for (int i=1;i<sheet.getColumn(0).length;i++){
					checkUsername = sheet.getColumn(1)[i].getContents();
					checkPassword = sheet.getColumn(2)[i].getContents();
					checkPassword = decrypt(checkPassword, "KEYCRYPT");
					if(txtUsername.getText().equals(checkUsername) && txtPassword.getText().equals(checkPassword)){
						name= sheet.getColumn(3)[i].getContents();
						username =sheet.getColumn(4)[i].getContents();
						lblStatus.setText("Login success !");

						if(SidePanelContentController.primaryStage!=null)
							SidePanelContentController.primaryStage.close();
						if(sheet.getColumn(6)[i].getContents().equals("Organisateur")){					        
					        Stage primaryStage = Main.getStage();
							Parent root = FXMLLoader.load(getClass().getResource("/Organisateur/InterfaceGlobaleOrga.fxml"));
							Scene scene = new Scene(root,1180,702);
							scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							primaryStage.setScene(scene);
							primaryStage.show();
							LoginController.primaryStage = primaryStage;
							
							
						}
						if(sheet.getColumn(6)[i].getContents().equals("Etudiant")){
							Stage primaryStage = Main.getStage();
							Parent root = FXMLLoader.load(getClass().getResource("/Etudiant/InterfaceGlobaleEtud.fxml"));
							Scene scene = new Scene(root,1180,702);
							scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							primaryStage.setScene(scene);
							primaryStage.show();
							LoginController.primaryStage = primaryStage;
						}											
						if(sheet.getColumn(6)[i].getContents().equals("Tuteur")){
							Stage primaryStage = Main.getStage();
							Parent root = FXMLLoader.load(getClass().getResource("/Tuteur/InterfaceGlobaleTut.fxml"));
							Scene scene = new Scene(root,1180,702);
							scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							primaryStage.setScene(scene);
							primaryStage.show();
							LoginController.primaryStage = primaryStage;
						}
					}
					else
						lblStatus.setText("Login failed ! Please, try again !");	
				}
			}
		}
				 
		catch (BiffException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			if(workbook!=null){
				workbook.close(); 
			}
		}	
   }

    public void initialize(URL url, ResourceBundle rb){
	    //JFXRippler a = new JFXRippler(rip);
	    //cehck.getChildren().add(a);
    }
    
    public void buttonPressed(KeyEvent e){
    	if(e.getCode().toString().equals("ENTER")){
    		buttonConnexion.fire();
    }}
    
    public void create(ActionEvent event) throws Exception{
		AnchorPane contenuCreate = FXMLLoader.load(getClass().getResource("contenuCreateCount.fxml"));
		drawerCreate.setSidePane(contenuCreate);
		drawerCreate.open();
		drawerCreate.setStyle("-fx-translate-y:695");
		paneColor.setOpacity(0.1);
		paneInfo.setOpacity(0.1);
		drawerCreateP=drawerCreate;
		paneColorP=paneColor;
		paneInfoP=paneInfo;
    }
    
    private static String decrypt(String password,String key){
		try{
			Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE,clef);
			return new String(cipher.doFinal(password.getBytes()));
		}
		catch (Exception e){
			System.out.println(e);
			return null;
		}
	}
}


