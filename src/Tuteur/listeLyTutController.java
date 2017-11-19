package Tuteur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class listeLyTutController implements Initializable{
	    @FXML
	    private VBox listeLyTut;
	    
	    public static VBox listeLyTutP;
	    
	 public void initialize(URL url, ResourceBundle rb) {
		 listeLyTutP=listeLyTut;
	 }

}
