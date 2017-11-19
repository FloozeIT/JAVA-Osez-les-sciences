package Organisateur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class listeLycéenController implements Initializable{
	    @FXML
	    private VBox listeLy;
	    
	    public static VBox listeLyP;
	    
	 public void initialize(URL url, ResourceBundle rb) {
		 listeLyP=listeLy;
	 }

}
