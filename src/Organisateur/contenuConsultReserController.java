package Organisateur;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXToggleButton;

import application.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class contenuConsultReserController implements Initializable{

      @FXML
      private FlowPane main2;
    
	  @FXML
	  private JFXDrawer drawerTab2; 
	  
	  public static JFXDrawer drawerTab2P;
	  
      @FXML
      private ComboBox<String> choiceMat;
      
      @FXML
      private ComboBox<String> choiceHeure;
      
      public static ComboBox<String> choiceMatP;
      
      public static ComboBox<String> choiceHeureP;
      
      @FXML
      private JFXToggleButton heure;
      
      @FXML
      private JFXToggleButton mat;
 
      private ObservableList<String> list = FXCollections.observableArrayList();
      private ObservableList<String> list2 = FXCollections.observableArrayList();
      
	  public void initialize(URL url, ResourceBundle rb) {
		  CréationCoursController.gridDayP.setDisable(false);
			try {				
				String User = System.getProperty("user.name");
			    Workbook workbook = null;
				try {
					workbook = Workbook.getWorkbook(new File(Main.chemin));
				} catch (BiffException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Sheet sheet = workbook.getSheet("Inscription_"+CréationCoursController.drawerNameP);
				for(int i=1; i<sheet.getColumn(0).length;i++){
					if(!list.contains(sheet.getCell(2,i).getContents()))
						list.add(sheet.getCell(2,i).getContents());	
					if(!list2.contains(sheet.getCell(1,i).getContents()))
						list2.add(sheet.getCell(1,i).getContents());}
		        choiceMat.setItems(list);
		        choiceMat.setDisable(true);
		        choiceHeure.setItems(list2);
		        choiceHeure.setDisable(true);
				drawerTab2P=drawerTab2;
				FlowPane tab2 = FXMLLoader.load(getClass().getResource("TabEns2.fxml"));
				drawerTab2.setSidePane(tab2);
				drawerTab2.open();	
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	  }
	  
	  public void select(){
		  if(mat.isSelected()){
			  choiceMat.setDisable(false);
		  }else{
			  choiceMat.setDisable(true);
			  choiceMat.setValue(null);
		  }
		  if(heure.isSelected()){
			  choiceHeure.setDisable(false);
		  }else{
			  choiceHeure.setDisable(true);
			  choiceHeure.setValue(null);
		  }
      }

	  public void filtre(ActionEvent event) throws IOException {
		  if(mat.isSelected() && heure.isSelected()){
			  choiceHeureP=choiceHeure;
			  choiceMatP=choiceMat;
			  FlowPane tab5 = FXMLLoader.load(getClass().getResource("TabEns5.fxml"));
			  drawerTab2.setSidePane(tab5);
			  drawerTab2.open();	
		  }
		  if(mat.isSelected()){
			  choiceMatP=choiceMat;
			  FlowPane tab3 = FXMLLoader.load(getClass().getResource("TabEns3.fxml"));
			  drawerTab2.setSidePane(tab3);
			  drawerTab2.open();	
		  }
		  if(heure.isSelected()){
			  choiceHeureP=choiceHeure;
			  FlowPane tab4 = FXMLLoader.load(getClass().getResource("TabEns4.fxml"));
			  drawerTab2.setSidePane(tab4);
			  drawerTab2.open();	
		  }
		  if(mat.isSelected()==false && heure.isSelected()==false){
			  FlowPane tab2 = FXMLLoader.load(getClass().getResource("TabEns2.fxml"));
			  drawerTab2.setSidePane(tab2);
			  drawerTab2.open(); 
		  }
	  }  
}
