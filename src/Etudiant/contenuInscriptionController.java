package Etudiant;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import application.LoginController;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class contenuInscriptionController implements Initializable{
	
	  @FXML
	  private JFXDrawer drawerTab;
	  
	  @FXML
	  private JFXTextField mention, enseignant;
	  
	  @FXML
	  private ComboBox<String> intitule;
		
	  @FXML
	  private ComboBox<String> creneaux;

	  @FXML
	  private JFXToggleButton RU;
		  
	  public static JFXDrawer drawerTabP;
	  @FXML
	  private JFXButton supr;
	  public static JFXButton suprP;
	  

	    @FXML
	    private Pane paneInscri;
	  
	  ObservableList<String> listCreneaux = FXCollections.observableArrayList();
	  ObservableList<String> listIntitule = FXCollections.observableArrayList();
	  
	  String User = System.getProperty("user.name");
	    
	  public void valider(ActionEvent event) throws RowsExceededException, WriteException, IOException, BiffException{
		  JFXSnackbar snack= new JFXSnackbar(paneInscri);
		  if(intitule.getValue().equals("")||creneaux.getValue().equals("")|| mention.getText().equals("")|| enseignant.getText().equals("")){
			  snack.show("Veuillez sélectionnez tous les champs", 3000);
		  }
		  else{		
				paneInscri.getStylesheets().add(getClass().getResource("main2.css").toExternalForm());
				Workbook workbook = null;
				try {	
						File ins = new File(Main.chemin);
						workbook = Workbook.getWorkbook(ins);
						File outFile = new File(Main.cheminTemp);
						WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook);
						WritableSheet dataSheet = wwb.getSheet("Inscription_" + InterfaceGlobaleEtudController.drawerNameP);
						WritableSheet dataSheet3 = wwb.getSheet("Création_" + InterfaceGlobaleEtudController.drawerNameP);
					    Workbook workbook2 = null;
					    String User = System.getProperty("user.name");
						workbook2 = Workbook.getWorkbook(new File(Main.chemin));
						Sheet dataSheet2 = workbook2.getSheet("Création_"+InterfaceGlobaleEtudController.drawerNameP);
											
						dataSheet.addCell(new Label(0, 0, "ID"));
						dataSheet.addCell(new Label(1, 0, "Crénaux"));
						dataSheet.addCell(new Label(2, 0, "Intitulé"));
						dataSheet.addCell(new Label(3, 0, "Mention"));
						dataSheet.addCell(new Label(4, 0, "Enseignant"));
						dataSheet.addCell(new Label(5, 0, "RU"));
						dataSheet.addCell(new Label(6, 0, "Nom"));
						dataSheet.addCell(new Label(7, 0, "Prenom"));
						
						int i = dataSheet.getColumn(0).length;
						
						dataSheet.addCell(new Number(0, i, i)); 
						dataSheet.addCell(new Label(1, i, (String) creneaux.getValue()));
						dataSheet.addCell(new Label(2, i, (String) intitule.getValue()));
						dataSheet.addCell(new Label(3, i, mention.getText()));
						dataSheet.addCell(new Label(4, i, enseignant.getText()));
						if(RU.isSelected()==true){dataSheet.addCell(new Label(5,i,"Oui"));}
						else{dataSheet.addCell(new Label(5,i,"Non" ));}
						dataSheet.addCell(new Label(6, i, LoginController.name));
						dataSheet.addCell(new Label(7, i, LoginController.username));
						
						for(int y=1; y<dataSheet2.getColumn(0).length;y++){
							if(dataSheet2.getCell(3,y).getContents().equals(intitule.getValue()) 
								&& dataSheet2.getCell(4,y).getContents().equals(mention.getText())
								&& dataSheet2.getCell(5,y).getContents().equals(enseignant.getText())
								&& (dataSheet2.getCell(1,y).getContents()+"->"+dataSheet2.getCell(2,y).getContents()).equals(creneaux.getValue())){
								    int nb_places= Integer.parseInt(dataSheet2.getCell(6,y).getContents());
									dataSheet3.addCell(new Number(6,y,nb_places-1));
									}
						}
						wwb.write();
						wwb.close(); 
						               
						File insb = new File(Main.chemin);
						insb.delete();
						               
						File insc = new File(Main.chemin);
						outFile.renameTo(insc);

						
				}
				
				finally {
					if(workbook!=null){
							workbook.close();
					}
				}

				
				mention.setText("");
				enseignant.setText("");
				RU.setSelected(false);
				intitule.setValue("");
				creneaux.setValue("");
				
				
				snack.show("Inscription faite", 3000);
				
				FlowPane contenuL = FXMLLoader.load(getClass().getResource("contenuInscription.fxml"));
				InterfaceGlobaleEtudController.drawerRefresh.setSidePane(contenuL);
				InterfaceGlobaleEtudController.drawerRefresh.requestFocus();	
						

		  }
	  }
	  
	  @FXML
	  public void select(ActionEvent event){
		    intitule.setDisable(false);
		    Workbook workbook = null;
			try {
				workbook = Workbook.getWorkbook(new File(Main.chemin));
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Sheet sheet = workbook.getSheet("Création_"+InterfaceGlobaleEtudController.drawerNameP);
			ObservableList<String> listIntitule = FXCollections.observableArrayList();
	        for(int i=1; i<sheet.getColumn(0).length;i++){
	        	if(creneaux.getValue().equals(sheet.getCell(1,i).getContents()+"->"+sheet.getCell(2,i).getContents())){
	        		listIntitule.add(sheet.getCell(3,i).getContents());
	        		
	        	} 
	        }
	        intitule.setItems(listIntitule);}
	  
	  public void select2(ActionEvent event){
		    Workbook workbook = null;
			try {
				workbook = Workbook.getWorkbook(new File(Main.chemin));
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Sheet sheet2 = workbook.getSheet("Création_"+InterfaceGlobaleEtudController.drawerNameP);
			
	        for(int i=1; i<sheet2.getColumn(0).length;i++){
	        	if((sheet2.getCell(1,i).getContents()+"->"+sheet2.getCell(2,i).getContents()).equals(creneaux.getValue()) && sheet2.getCell(3,i).getContents().equals(intitule.getValue())){        		
	        		mention.setText(sheet2.getCell(4,i).getContents());
	        		enseignant.setText(sheet2.getCell(5,i).getContents());
	        	}
	        } 
	  }
	
	  public void initialize(URL url, ResourceBundle rb) {    
	  		mention.setDisable(true);
	  		enseignant.setDisable(true);
		    intitule.setDisable(true);
		    suprP=supr;
	        Workbook workbook2 = null;
			try {
				workbook2 = Workbook.getWorkbook(new File(Main.chemin));
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Sheet sheet2 = workbook2.getSheet("Création_"+InterfaceGlobaleEtudController.drawerNameP);
			Sheet sheet3 = workbook2.getSheet("Inscription_"+InterfaceGlobaleEtudController.drawerNameP);
	        for(int i=1; i<sheet2.getColumn(0).length;i++){
	        	if(listCreneaux.contains(sheet2.getCell(1,i).getContents()+"->"+sheet2.getCell(2,i).getContents())==false && Integer.parseInt(sheet2.getCell(6,i).getContents())!=0)
	        		listCreneaux.add(sheet2.getCell(1,i).getContents()+"->"+sheet2.getCell(2,i).getContents());  
	        }
	        for(int j=1; j<sheet3.getColumn(0).length;j++){
	        	if(listCreneaux.contains(sheet3.getCell(1,j).getContents())==true && (LoginController.name+" "+LoginController.username).equals(sheet3.getCell(6,j).getContents()+ " "+sheet3.getCell(7,j).getContents())){
	        		listCreneaux.remove(sheet3.getCell(1,j).getContents());}
	        }
	        creneaux.setItems(listCreneaux);
			try {
				drawerTabP=drawerTab;
				FlowPane tab = FXMLLoader.load(getClass().getResource("TabEnsEtud.fxml"));
				drawerTab.setSidePane(tab);
				drawerTab.open();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
}}
