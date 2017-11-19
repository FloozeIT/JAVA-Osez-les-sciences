package Etudiant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import Organisateur.Cr�ationCoursController;
import application.LoginController;
import application.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class SidePanelContentControllerEtud implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2; 
    
    @FXML 
    private Label util;
    
    @FXML 
    public static JFXDialog dialogP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //util.setText(LoginController.username +" "+LoginController.username);
    }    
    
    public static Stage primaryStage;
    
    @FXML
    private void aide(ActionEvent event) throws IOException {
		  FXMLLoader loader = new FXMLLoader();
		  loader.setLocation(getClass().getResource("dialog.fxml"));
		  loader.load();
		  Parent dialogBody = loader.getRoot();
		  JFXDialogLayout content = new JFXDialogLayout();
		  content.setBody(dialogBody);
		  JFXDialog dialog = new JFXDialog(InterfaceGlobaleEtudController.stackPaneP, content, JFXDialog.DialogTransition.CENTER);
		  dialogP=dialog;
		  dialog.show();
		  
    	/*Alert alert = new Alert(AlertType.INFORMATION);
    	
    	alert.setTitle("Fen�tre d'aide");
    	alert.setHeaderText("\t\t\t\t\t\t\t\t\tGuide d'aide pour l'utilisateur\t\t\t\t\t\t\t\t\t");
    	alert.setContentText("\t\tPOUR L'ORGANISATEUR :\n\n\tVous pouvez cr�er et supprimer des cours suivant le jour s�lectionn�. Les cours d�j� cr��s pour ce jour sont pr�sents dans le tableau \"Enseignements enregistr�s\".\n\n\tVous pouvez basculer sur une autre interface gr�ce � l'hamburger situ� en haut � gauche de l'application.\n\n\tPour conna�tre la liste des �tudiants inscris � vos cours, il faut choisir \"Consultation des r�servations\" dans l'hamburger.\n\n\tLa liste des �tudiants qui se sont inscrits appara�t dans un tableau. Vous pouvez filtrer ces r�servations en fonction de l'horaire et/ou de la mati�re.\n\n\tEnfin, vous pouvez cr�er des comptes tuteurs et affecter chaque lyc�en � un tuteur. Pour cela, il faut aller sur \"Affectation Lyc�en / Tuteurs\" dans l'hamburger. Cette interface est assez intuitive.\n\n\t\tPOUR L'�TUDIANT :\n\n\tVous pouvez vous inscrire aux cours que vous souhaitez en fonction du jour. Pour cela, s�lectionner le cr�neau qui vous convient, ainsi que l'intitul� du cours. Merci de renseigner sur l'application si vous mangez au RU ou non et cliquez sur Valider.\n\n\tVotre nouvelle inscription, ainsi que toutes vos inscriptions se retrouvent dans le tableau \"Consultation des r�servations\".\n\n\t\tPOUR LE TUTEUR :\n\n\tVous pouvez voir la liste des �tudiants qui vous ont �t� attribu�s, ainsi que leur adresse mail pour pouvoir les contacter en cas de besoin.\n\n\tPour visualiser l'emploi du temps d'un lyc�en, il suffit de cliquer sur le nom d'un lyc�en pr�sent dans la liste.");
    	alert.showAndWait();*/
    }
    
    @FXML
    private void deco(ActionEvent event) throws IOException {
    	Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/LoginJfoenix.fxml"));
		Scene scene = new Scene(root,1180,702);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		SidePanelContentControllerEtud.primaryStage = Main.getStage();
    }

    @FXML
    private void clic(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        switch(btn.getId())
        {           
            case "b1":
            	b1.setStyle("-fx-background-color:#c6ced3; -fx-border-color: #F5DA81");
				InterfaceGlobaleEtudController.lblEtudP.setText("INSCRIPTION AUX ENSEIGNEMENTS");
           		FlowPane contenuprop;
				try {
					contenuprop = FXMLLoader.load(getClass().getResource("contenuInscription.fxml"));
					InterfaceGlobaleEtudController.drawer3P.setSidePane(contenuprop);	             
					InterfaceGlobaleEtudController.drawer3P.open();
				} catch (IOException e) {
					e.printStackTrace();
				}
            break;           
        }
    }   
}
