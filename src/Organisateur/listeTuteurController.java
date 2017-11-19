package Organisateur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class listeTuteurController implements Initializable{
	    @FXML
	    private VBox listeTu;
	    
	    public static VBox listeTuP;
	    
	 public void initialize(URL url, ResourceBundle rb) {
		 listeTuP=listeTu;
	 }

}
