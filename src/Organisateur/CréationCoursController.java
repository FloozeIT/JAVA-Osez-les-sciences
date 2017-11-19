package Organisateur;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;


public class CréationCoursController implements Initializable {

    @FXML
    private JFXDrawer drawer;
    
    @FXML
    private JFXDrawer drawer2;

    @FXML
    private JFXHamburger hamburger;
    
    @FXML
    private Label lblOrga;
    
    @FXML
    private JFXButton lundi;
    
    @FXML
    private JFXHamburger hamburger2;
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private Line ligne;
    
    @FXML
    private JFXDrawer drawer3;
    
    @FXML
    protected static JFXDrawer drawerRefresh;
    
    @FXML
    private Pane paneTop;
    
    @FXML
    private GridPane gridDay;
    
	  @FXML
	  private StackPane stackPane;
    
    public static AnchorPane rootP;
    public static Label lblOrgaP;
    public static JFXDrawer drawer3P;
    public static String drawerNameP="Lundi";
    public static GridPane gridDayP;
    public static StackPane stackPaneP;
    
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        rootP = root;
        lblOrgaP=lblOrga;
        gridDayP=gridDay;
        stackPaneP=stackPane;
        
		 FlowPane contenu;
		try {
			contenu = FXMLLoader.load(getClass().getResource("contenuCréation.fxml"));
			drawer3.setSidePane(contenu);	             
			drawer3.open();
            drawer3P=drawer3;
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
            drawer.open();
        } catch (IOException ex) {
            Logger.getLogger(CréationCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();
            
            if(drawer.getStyle().equals("-fx-translate-x:310")){
            	drawer.close();
            	drawer.setStyle("-fx-translate-x:-310");
            }
            else{
            	drawer.open();
            	drawer.setStyle("-fx-translate-x:310");
            	hamburger.toFront();
            }
        });
        
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 
        try {
            HBox box2 = FXMLLoader.load(getClass().getResource("SidePanelContent2.fxml"));
            drawer2.setSidePane(box2);
        } catch (IOException ex) {
            Logger.getLogger(CréationCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
        hamburger2.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            if(drawer2.isShown())
            {
            	drawer2.close();
            	drawer2.toBack();
            }else
                drawer2.open();
            	drawer2.toFront();
        });
        drawerRefresh=drawer3;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////    
    @FXML
    private void clic2(ActionEvent event) throws Exception {
        JFXButton btn = (JFXButton) event.getSource();
        
        switch(btn.getText())
        {
            case "LUNDI":drawerNameP="Lundi"; 
            			 ligne.setStartX(-117.9);
            			 ligne.setEndX(117.9);
            			 
            			 if(lblOrga.getText().equals("PROPOSITION D'ENSEIGNEMENTS")){
                			 FlowPane contenuLP = FXMLLoader.load(getClass().getResource("contenuCréation.fxml"));
                			 drawer3.setSidePane(contenuLP);	             
        		             drawer3.open();
            			 }
            			 if(lblOrga.getText().equals("CONSULTATION DES RESERVATIONS")){
            				 Pane contenuLC = FXMLLoader.load(getClass().getResource("contenuConsultReser.fxml"));
            				 drawer3.setSidePane(contenuLC);
            				 drawer3.open();
            			 }
    		              	
    		             
            break;
            case "MARDI":drawerNameP="Mardi";
            			 ligne.setStartX(117.9);
			 			 ligne.setEndX(353.7);
			 			 
            			 if(lblOrgaP.getText().equals("PROPOSITION D'ENSEIGNEMENTS")){
                			 FlowPane contenuMAP = FXMLLoader.load(getClass().getResource("contenuCréation.fxml"));
                			 drawer3.setSidePane(contenuMAP);	             
        		             drawer3.open();
            			 }
            			 if(lblOrgaP.getText().equals("CONSULTATION DES RESERVATIONS")){
            				 Pane contenuMAC = FXMLLoader.load(getClass().getResource("contenuConsultReser.fxml"));
            				 drawer3.setSidePane(contenuMAC);
            				 drawer3.open();
            			 }
    		            
    		             
            break;
            case "MERCREDI": drawerNameP="Mercredi";
            				ligne.setStartX(353.7);
			 			 	ligne.setEndX(589.5);
			 			 	
	            			 if(lblOrgaP.getText().equals("PROPOSITION D'ENSEIGNEMENTS")){
	                			 FlowPane contenuMEP = FXMLLoader.load(getClass().getResource("contenuCréation.fxml"));
	                			 drawer3.setSidePane(contenuMEP);	             
	        		             drawer3.open();
	            			 }
	            			 if(lblOrgaP.getText().equals("CONSULTATION DES RESERVATIONS")){
	            				 Pane contenuMEC = FXMLLoader.load(getClass().getResource("contenuConsultReser.fxml"));
	            				 drawer3.setSidePane(contenuMEC);
	            				 drawer3.open();
	            			 }
	    		             
	    		             
			break;
			case "JEUDI": drawerNameP="Jeudi";
						 ligne.setStartX(589.5);
						 ligne.setEndX(825.3);
						 
            			 if(lblOrgaP.getText().equals("PROPOSITION D'ENSEIGNEMENTS")){
                			 FlowPane contenuJP = FXMLLoader.load(getClass().getResource("contenuCréation.fxml"));
                			 drawer3.setSidePane(contenuJP);	             
        		             drawer3.open();
            			 }
            			 if(lblOrgaP.getText().equals("CONSULTATION DES RESERVATIONS")){
            				 Pane contenuJC = FXMLLoader.load(getClass().getResource("contenuConsultReser.fxml"));
            				 drawer3.setSidePane(contenuJC);
            				 drawer3.open();
            			 }
    		             
    		             
			break;
			case "VENDREDI": drawerNameP="Vendredi";
							ligne.setStartX(825.3);
			 			 	ligne.setEndX(1061.8);
			 			 	
	            			 if(lblOrgaP.getText().equals("PROPOSITION D'ENSEIGNEMENTS")){
	                			 FlowPane contenuVP = FXMLLoader.load(getClass().getResource("contenuCréation.fxml"));
	                			 drawer3.setSidePane(contenuVP);	             
	        		             drawer3.open();
	            			 }
	            			 if(lblOrgaP.getText().equals("CONSULTATION DES RESERVATIONS")){
	            				 Pane contenuVC = FXMLLoader.load(getClass().getResource("contenuConsultReser.fxml"));
	            				 drawer3.setSidePane(contenuVC);
	            				 drawer3.open();
	            			 }	    		            
	    		             
	    		             
			break;
                
        }

    }
    
}
