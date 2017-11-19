package Organisateur;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import Tuteur.InterfaceGlobaleTutController;
import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class contenuAffectationController implements Initializable{
	
	@FXML
	private JFXDrawer drawerL, drawerT;
	
	private JFXCheckBox checkLycéen;
	private JFXCheckBox checkTuteur;
	@FXML
	private Pane ajoutPane;
	
	private ArrayList<JFXCheckBox> cL = new ArrayList<JFXCheckBox>();
	private ArrayList<JFXCheckBox> cLT = new ArrayList<JFXCheckBox>();
	private ArrayList<JFXCheckBox> cT = new ArrayList<JFXCheckBox>();
	private ArrayList<String> ajout = new ArrayList<String>();
	
	@FXML
	private Pane affectation;
	
	@FXML
    private JFXButton validate;
    
    @FXML
    protected JFXTextField txtNom, txtPrenom, txtMail, user, pass;
    
    @FXML
    private Text txtCheck;
    
    @FXML
    private VBox contenuT;
    
    @FXML
    private EventHandler handlerT;
	
	  public void initialize(URL url, ResourceBundle rb) {
		CréationCoursController.gridDayP.setDisable(true); 
		try {
			  Workbook workbook = null;
			  String User = System.getProperty("user.name");
			  VBox contenuL = FXMLLoader.load(getClass().getResource("listeLycéen.fxml"));
			  drawerL.setSidePane(contenuL);

			  contenuT = FXMLLoader.load(getClass().getResource("listeTuteur.fxml"));
			  drawerT.setSidePane(contenuT);
			  
			  
			  
			  try {
					workbook = Workbook.getWorkbook(new File(Main.chemin));
				} catch (BiffException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			  
			  
			  EventHandler handlerL = new EventHandler <InputEvent>() {
				    public void handle(InputEvent event) {
				    	JFXCheckBox l = (JFXCheckBox) event.getSource();
				        if(l.isSelected()==false){
				        	ajout.add(l.getText());
				        }
				        else{
				        	if(ajout.contains(l.getText())){
				        		ajout.remove(l.getText());
				        	}
				        }
				    }
			   };
				  
			  handlerT = new EventHandler <InputEvent>() {
				    public void handle(InputEvent event) {
				    	ajout.clear();
				    	JFXCheckBox t = (JFXCheckBox) event.getSource();
				        if(t.isSelected()==false){
				        	ajout.add(t.getText());
				        	listeLycéenController.listeLyP.setDisable(false);
				        	for(int i=0; i<cL.size();i++){
				        		if(cL.get(i).isSelected()==false && cL.get(i).isDisable()==true)
				  			  		cL.get(i).setDisable(false);  
				  		  	}
				        	for(int i=0; i<cT.size();i++){
				  			  	cT.get(i).setSelected(false); 
				  		  	}
				        	for(int i=0; i<cLT.size();i++){
				        		cLT.get(i).setDisable(true); 
				        	}
				        }
				        else{
				        	for(int i=0; i<cL.size();i++){
				  			  	cL.get(i).setDisable(true);  
				  		  	}
				        	for(int i=0; i<cT.size();i++){
				  			  	t.setSelected(true);
				  		  	}
				        	if(ajout.contains(t.getText())){
				        		ajout.remove(t.getText());
				        		listeLycéenController.listeLyP.setDisable(true);
				        	}

				        }
				    }
			   };
			  
			  Sheet sheet = workbook.getSheet("Login");
			  
			  int k=0;
			  for(int i=1; i<sheet.getColumn(0).length;i++){
					if(sheet.getCell(6,i).getContents().equals("Etudiant")){
						checkLycéen = new JFXCheckBox();
						checkLycéen.setText(sheet.getCell(3,i).getContents() + " " + sheet.getCell(4,i).getContents());
						checkLycéen.setDisable(true);
						checkLycéen.setAlignment(Pos.CENTER_LEFT);
						cL.add(checkLycéen);
						contenuL.getChildren().add(cL.get(k));
						cL.get(k).addEventHandler(MouseEvent.MOUSE_PRESSED,handlerL);
						k=k+1;
						double h=listeLycéenController.listeLyP.getPrefHeight();
						listeLycéenController.listeLyP.setPrefHeight(h+60);
						listeLycéenController.listeLyP.setDisable(true);
					}
			  }

			  k=0;
			  for(int i=1; i<sheet.getColumn(0).length;i++){			  
					if(sheet.getCell(6,i).getContents().equals("Tuteur")){
						checkTuteur = new JFXCheckBox();
						checkTuteur.setText(sheet.getCell(3,i).getContents() + " " + sheet.getCell(4,i).getContents());
						checkTuteur.setAlignment(Pos.CENTER_LEFT);
						cT.add(checkTuteur);
						contenuT.getChildren().add(cT.get(k));
						cT.get(k).addEventHandler(MouseEvent.MOUSE_PRESSED,handlerT);
						k=k+1;
						double h=listeTuteurController.listeTuP.getPrefHeight();
						listeTuteurController.listeTuP.setPrefHeight(h+60);
					}
			  }
			  
			  Sheet sheet2 = workbook.getSheet("Affectation");
			  for(int i=1; i<sheet2.getColumn(0).length;i++){
				  for(int j=0; j<cL.size();j++){
					  if(sheet2.getCell(1,i).getContents().equals(cL.get(j).getText())){
						  cL.get(j).setDisable(true);
						  cL.get(j).setText("   "+cL.get(j).getText() +"\n    "+ " (Affecté à " + sheet2.getCell(0,i).getContents() + ")");
						  cL.get(j).setTextAlignment(TextAlignment.CENTER);
						  cL.get(j).setSelected(true);
						  cLT.add(cL.get(j));
					  }
				  }
			  }
			
			  
		} catch (IOException e) {
			e.printStackTrace();
		}
		drawerL.open();
		drawerT.open();	
		
   }
	  public void affect(ActionEvent event) throws BiffException, IOException, WriteException{
		 System.out.println(ajout);
		  
			  for(int i=0; i<cL.size();i++){
				  if(cL.get(i).isSelected()){
					  if(cL.get(i).isDisable()==false)
						  cL.get(i).setText("\n"+cL.get(i).getText() + " (Affecté à " + ajout.get(0) + ")");
					  cL.get(i).setDisable(true);
				  }
			  }
				      Workbook workbook = null;
					  String User = System.getProperty("user.name");
						try {	
							
														
								
								File ins = new File(Main.chemin);
								workbook = Workbook.getWorkbook(ins);
								File outFile = new File(Main.cheminTemp);
								WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook);
								
								WritableSheet dataSheet = wwb.getSheet(11);
								
								int w = dataSheet.getColumn(0).length;
								System.out.println("Gens cochés : " + ajout);
								System.out.println("taille ajout : " + ajout.size());
								for(int j=1; j<ajout.size(); j++){
									dataSheet.addCell(new Label(0, w, ajout.get(0))); 
									dataSheet.addCell(new Label(1, w, ajout.get(j)));
									w+=1;	
								}	
								
								wwb.write();
								wwb.close();
																               
								File insb = new File(Main.chemin);
								insb.delete();
								               
								File insc = new File(Main.chemin);
								outFile.renameTo(insc);
								
								FlowPane contenu = FXMLLoader.load(getClass().getResource("contenuAffectation.fxml"));
								CréationCoursController.drawerRefresh.setSidePane(contenu);
								CréationCoursController.drawerRefresh.requestFocus();
								
								JFXSnackbar snack = new JFXSnackbar(CréationCoursController.stackPaneP);
								CréationCoursController.stackPaneP.getStylesheets().add(getClass().getResource("main2.css").toExternalForm());
								  snack.show("Affectation réalisée", 3000);
						}
						
						finally {
							if(workbook!=null){
									workbook.close();
							}		  
						
			  }if(ajout.size()==1){JFXSnackbar snack = new JFXSnackbar(CréationCoursController.stackPaneP);
			  CréationCoursController.stackPaneP.getStylesheets().add(getClass().getResource("main2.css").toExternalForm());
				snack.show("Veuillez sélectionnez un ou plusieurs étudiants", 3000);}
						

				  }
				  
				  private static String encrypt(String password,String key){
						try{
							Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"),"Blowfish");
							Cipher cipher=Cipher.getInstance("Blowfish");
							cipher.init(Cipher.ENCRYPT_MODE,clef);
							return new String(cipher.doFinal(password.getBytes()));
						}
						catch (Exception e){
							return null;
						}

			  }
	
	  
	  public void valider(ActionEvent event) throws RowsExceededException, WriteException, IOException, BiffException {
		  	 JFXSnackbar snack = new JFXSnackbar(ajoutPane);
		  	
			 if(txtNom.getText().equals("") || txtPrenom.getText().equals("") || txtMail.getText().equals("") || 
					 user.getText().equals("") || pass.getText().equals("")){
				 
				 snack.show("Merci de renseigner tous les champs !", 3000);
				 
			 }else{	 
			 if(txtNom.getText().matches("^[a-zA-ZÀ-ÿ]* ?-?[a-zA-ZÀ-ÿ]*$")==false){
				 snack.show("Nom non valide !", 3000);
			 }else{
				 if(txtPrenom.getText().matches("^[a-zA-ZÀ-ÿ]+([a-zA-ZÀ-ÿ]*-[a-zA-ZÀ-ÿ]+)*$")==false){
					 snack.show("Prenom non valide !", 3000);
				 }
				 else{
					 if(txtMail.getText().matches("^[_a-z0-9-]+(.[a-z0-9-]+)@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$")==false){
						 snack.show("Mail non valide !", 3000);
					 } 
			 else{
			 
			 String User = System.getProperty("user.name");
				
				int userExist=0;
				Workbook workbook2 = null;
				try {	
						File ins = new File(Main.chemin);
						workbook2 = Workbook.getWorkbook(ins);
						File outFile = new File(Main.cheminTemp);
						WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook2);
						 
						WritableSheet dataSheet = wwb.getSheet(0);
						
						int i = dataSheet.getColumn(0).length;					
						
						for(int o=1; o<=i; o++){
							if(user.getText().equals(dataSheet.getCell(1,o).getContents())){
								userExist = 1;
								snack.show("Username déjà utilisé !", 3000);
							}
						}if(userExist==0){
						
						dataSheet.addCell(new Number(0, i, i)); 
						dataSheet.addCell(new Label(1, i, user.getText()));
						dataSheet.addCell(new Label(2, i, encrypt(pass.getText(), "KEYCRYPT")));
						dataSheet.addCell(new Label(3, i, txtNom.getText()));
						dataSheet.addCell(new Label(4, i, txtPrenom.getText()));
						dataSheet.addCell(new Label(5, i, txtMail.getText()));
						dataSheet.addCell(new Label(6, i, "Tuteur"));	
						
						checkTuteur = new JFXCheckBox();
						checkTuteur.setText(txtNom.getText() + " " + txtPrenom.getText());
						cT.add(checkTuteur);
						contenuT.getChildren().add(cT.get(cT.size()-1));
						cT.get(cT.size()-1).addEventHandler(MouseEvent.MOUSE_PRESSED,handlerT);
						
						txtNom.clear();
						txtPrenom.clear();
						txtMail.clear();
						user.clear();
						pass.clear();
						drawerT.open();
						
						snack.show("Tuteur crée !", 3000);
						
						}
						
						wwb.write();
						wwb.close(); 
						               
						File insb = new File(Main.chemin);
						insb.delete();
						               
						File insc = new File(Main.chemin);
						outFile.renameTo(insc);
				}
				
				
				
				finally {
					if(workbook2!=null){
							workbook2.close();
					}
				}
				FlowPane contenu = FXMLLoader.load(getClass().getResource("contenuAffectation.fxml"));
				CréationCoursController.drawerRefresh.setSidePane(contenu);
				CréationCoursController.drawerRefresh.requestFocus();
			 
			 }}}}
		 }
}
