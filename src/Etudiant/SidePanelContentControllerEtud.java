package Etudiant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import Organisateur.CréationCoursController;
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
    	
    	alert.setTitle("Fenêtre d'aide");
    	alert.setHeaderText("\t\t\t\t\t\t\t\t\tGuide d'aide pour l'utilisateur\t\t\t\t\t\t\t\t\t");
    	alert.setContentText("\t\tPOUR L'ORGANISATEUR :\n\n\tVous pouvez créer et supprimer des cours suivant le jour sélectionné. Les cours déjà créés pour ce jour sont présents dans le tableau \"Enseignements enregistrés\".\n\n\tVous pouvez basculer sur une autre interface grâce à l'hamburger situé en haut à gauche de l'application.\n\n\tPour connaître la liste des étudiants inscris à vos cours, il faut choisir \"Consultation des réservations\" dans l'hamburger.\n\n\tLa liste des étudiants qui se sont inscrits apparaît dans un tableau. Vous pouvez filtrer ces réservations en fonction de l'horaire et/ou de la matière.\n\n\tEnfin, vous pouvez créer des comptes tuteurs et affecter chaque lycéen à un tuteur. Pour cela, il faut aller sur \"Affectation Lycéen / Tuteurs\" dans l'hamburger. Cette interface est assez intuitive.\n\n\t\tPOUR L'ÉTUDIANT :\n\n\tVous pouvez vous inscrire aux cours que vous souhaitez en fonction du jour. Pour cela, sélectionner le créneau qui vous convient, ainsi que l'intitulé du cours. Merci de renseigner sur l'application si vous mangez au RU ou non et cliquez sur Valider.\n\n\tVotre nouvelle inscription, ainsi que toutes vos inscriptions se retrouvent dans le tableau \"Consultation des réservations\".\n\n\t\tPOUR LE TUTEUR :\n\n\tVous pouvez voir la liste des étudiants qui vous ont été attribués, ainsi que leur adresse mail pour pouvoir les contacter en cas de besoin.\n\n\tPour visualiser l'emploi du temps d'un lycéen, il suffit de cliquer sur le nom d'un lycéen présent dans la liste.");
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
