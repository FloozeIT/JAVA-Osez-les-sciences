package Etudiant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class dialogController implements Initializable{
	
	public void initialize(URL url, ResourceBundle rb){
	}
	@FXML
	public void close(ActionEvent event){
		Etudiant.SidePanelContentControllerEtud.dialogP.close();
	}
}
