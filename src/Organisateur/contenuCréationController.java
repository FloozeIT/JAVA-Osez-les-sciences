package Organisateur;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class contenuCréationController implements Initializable{
	
	  @FXML
	  private JFXDrawer drawerTab; 
	  @FXML
	  private JFXTimePicker horaireDeb, horaireFin;
	  @FXML
	  private JFXTextField intitule, mention, enseignant;
	  @FXML
	  private JFXSlider nbPlaces;
	  @FXML
	  private Pane paneCreate;
	  @FXML
	  private Pane test;
	  @FXML
	  private FlowPane test2;
	    @FXML
	    private JFXButton supr;
	  
	  public static JFXDrawer drawerTabP;
	  public static JFXButton suprP;
	  

	  
	  public void valider(ActionEvent event) throws RowsExceededException, WriteException, IOException, BiffException{
		 
		  JFXSnackbar snack = new JFXSnackbar(paneCreate);
		  paneCreate.getStylesheets().add(getClass().getResource("main2.css").toExternalForm());
		  
			    Workbook workbook10 = null;
				try {
					workbook10 = Workbook.getWorkbook(new File(Main.chemin));
				} catch (BiffException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Sheet sheet = workbook10.getSheet("Création_"+CréationCoursController.drawerNameP);
				int deja = 0;
				for(int i=1; i<sheet.getColumn(0).length;i++){
					if(sheet.getCell(5,i).getContents().equals(enseignant.getText()) && horaireDeb.getValue().toString().equals(sheet.getCell(1,i).getContents())){
						snack.show("Cet enseignant a déjà un cours à cet horaire !", 3000); 
						deja = 1;
					}

				}
		  
		  
		  
		  if((""+horaireDeb.getValue()+"").equals("null") || (""+horaireFin.getValue()+"").equals("null") || intitule.getText().equals("") || mention.getText().equals("")|| enseignant.getText().equals("")){
			snack.show("Veuillez renseigner tous les champs", 3000); 

		  }		  
		  else if (deja!=1){
			 System.out.println(horaireDeb.getValue().toString().equals("16:00"));
			 if(horaireDeb.getValue().toString().equals("08:00")|| horaireDeb.getValue().toString().equals("10:00")|| 
				horaireDeb.getValue().toString().equals("14:00")|| horaireDeb.getValue().toString().equals("16:00")){
			 
				 paneCreate.getStylesheets().add(getClass().getResource("main2.css").toExternalForm());
			 
			     String User = System.getProperty("user.name");
				
				 Workbook workbook = null;
				 try {	
						File ins = new File(Main.chemin);
						workbook = Workbook.getWorkbook(ins);
						File outFile = new File(Main.cheminTemp);
						WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook);
						 
						WritableSheet dataSheet = wwb.getSheet("Création_" + CréationCoursController.drawerNameP);
						
						dataSheet.addCell(new Label(0, 0, "ID"));
						dataSheet.addCell(new Label(1, 0, "Horaire de début"));
						dataSheet.addCell(new Label(2, 0, "Horaire de fin"));
						dataSheet.addCell(new Label(3, 0, "Intitulé"));
						dataSheet.addCell(new Label(4, 0, "Mention"));
						dataSheet.addCell(new Label(5, 0, "Enseignant"));
						dataSheet.addCell(new Label(6, 0, "Nombre de places"));
						
						int i = dataSheet.getColumn(0).length;
						
						dataSheet.addCell(new Number(0, i, i)); 
						dataSheet.addCell(new Label(1, i,""+horaireDeb.getValue()+""));
						dataSheet.addCell(new Label(2, i,""+horaireFin.getValue()+""));
						dataSheet.addCell(new Label(3, i, intitule.getText()));
						dataSheet.addCell(new Label(4, i, mention.getText()));
						dataSheet.addCell(new Label(5, i, enseignant.getText()));
						dataSheet.addCell(new Number(6, i, Math.round(nbPlaces.getValue())));
						
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
				
				String mailTo = "";
		        URI uriMailTo = null;

		 
		        // Configuration des différentes variables du mail
		        String dateHeure = CréationCoursController.drawerNameP + " de " + horaireDeb.getValue()+ " à " + horaireFin.getValue();
		        String matiere = intitule.getText();
		        //mailTo = "enseignant1@gmail.com";
		        mailTo += "?subject=[OSEZ LES SCIENCES] - Nouveau cours";
		        mailTo += "&body=Bonjour,\nUn nouveau cours de " + matiere + " a été créé et vous devez assurer son organisation le " + dateHeure + ".\nMerci de confirmer votre présence. \nCordialement,\nL'équipe Organisateurs.";
		 
		        // Ouverture du logiciel de messagerie par défaut
		        if (Desktop.isDesktopSupported()) {
		            if (Desktop.getDesktop().isSupported(Desktop.Action.MAIL)) {
		                try {
		                    uriMailTo = new URI("mailto", mailTo, null);
		                    Desktop.getDesktop().mail(uriMailTo);
		                } catch (IOException ex) {
		                    Logger.getLogger(contenuCréationController.class.getName()).log(Level.SEVERE, null, ex);
		                } catch (URISyntaxException ex) {
		                    Logger.getLogger(contenuCréationController.class.getName()).log(Level.SEVERE, null, ex);
		                }
		            }
		        }	
		        

				
				snack.show("Création faite", 3000);
				
				FlowPane contenu = FXMLLoader.load(getClass().getResource("contenuCréation.fxml"));
				CréationCoursController.drawerRefresh.setSidePane(contenu);
				CréationCoursController.drawerRefresh.requestFocus();
				

				
				

		  }
			 else{
				 snack.show("Veuillez sélectionner des horraires adéquates !", 3000);
			 }
		 }
	  }
	  
	  public void changementHoraire(ActionEvent event){
		  horaireFin.setValue(horaireDeb.getValue().plusHours(2));
	  }
	
	  public void initialize(URL url, ResourceBundle rb) {
		  	//horaireDeb.setValue(LocalTime.of(8, 00));
		  	//horaireFin.setValue(horaireDeb.getValue().plusHours(2));
		    //JFXRippler a = new JFXRippler(test);
		    //test2.getChildren().add(a);
		    CréationCoursController.gridDayP.setDisable(false);
		    horaireFin.setDisable(true);
		 
			try {
				drawerTabP=drawerTab;
				suprP=supr;
				FlowPane tab = FXMLLoader.load(getClass().getResource("TabEns.fxml"));
				drawerTab.setSidePane(tab);
				drawerTab.open();	
				//System.out.println(CréationCoursController.drawerNameP);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
}}
