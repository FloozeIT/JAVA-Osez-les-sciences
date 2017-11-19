package Tuteur;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import Organisateur.CréationCoursController;
import Organisateur.listeLycéenController;
import Organisateur.listeTuteurController;
import application.LoginController;
import application.Main;
import Organisateur.TabEnsController.donnéesEns;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;



public class contenuTuteurController implements Initializable{
	
    @FXML
	private JFXDrawer drawerList;
    
    @FXML
    private Label int8;

    @FXML
    private Label men8;

    @FXML
    private Label ens8;

    @FXML
    private Label int10;

    @FXML
    private Label men10;

    @FXML
    private Label ens10;

    @FXML
    private Label int14;

    @FXML
    private Label men14;

    @FXML
    private Label ens14;

    @FXML
    private Label int16;

    @FXML
    private Label men16;

    @FXML
    private Label ens16;
    
	private ArrayList<JFXButton> cLT = new ArrayList<JFXButton>();
	private ArrayList<String> ajout = new ArrayList<String>();
	
	public static String name = "";
	public static String username="";
    
	public void initialize(URL url, ResourceBundle rb) {
		try {
			  Workbook workbook = null;
			  String User = System.getProperty("user.name");
			  VBox contenuL = FXMLLoader.load(getClass().getResource("listeLyTut.fxml"));
			  drawerList.setSidePane(contenuL);

 		  
			  try {
					workbook = Workbook.getWorkbook(new File(Main.chemin));
				} catch (BiffException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			  

			  EventHandler handlerLT = new EventHandler <InputEvent>() {
				    public void handle(InputEvent event){
					    try {
						    Workbook workbook2 = null;
						    String User = System.getProperty("user.name");
							workbook2 = Workbook.getWorkbook(new File(Main.chemin));
							Sheet sheet2 = workbook2.getSheet("Inscription_"+InterfaceGlobaleTutController.drawerNameP);
							JFXButton b = (JFXButton) event.getSource();
							int8.setText("");
							int10.setText("");
							int14.setText("");
							int16.setText("");
							men8.setText("");
							men10.setText("");
						    men14.setText("");
							men16.setText("");
							ens8.setText("");
							ens10.setText("");
							ens14.setText("");
							ens16.setText("");
					        if(b.isPressed()){		
					        	for(int i=1; i<sheet2.getColumn(0).length;i++){
					        		
					        		if(b.getText().split(" \\(")[0].equals(sheet2.getCell(6,i).getContents()+ " "+sheet2.getCell(7,i).getContents()) && 
					        		   sheet2.getCell(1,i).getContents().equals("08:00->10:00")){
					        			int8.setText(sheet2.getCell(2,i).getContents());
					        			men8.setText(sheet2.getCell(3,i).getContents());
					        			ens8.setText(sheet2.getCell(4,i).getContents());
					        			
					        		}
					        		if(b.getText().split(" \\(")[0].equals(sheet2.getCell(6,i).getContents()+ " "+sheet2.getCell(7,i).getContents()) && 
							        		   sheet2.getCell(1,i).getContents().equals("10:00->12:00")){
							        			int10.setText(sheet2.getCell(2,i).getContents());
							        			men10.setText(sheet2.getCell(3,i).getContents());
							        			ens10.setText(sheet2.getCell(4,i).getContents());
							        }
					        		if(b.getText().split(" \\(")[0].equals(sheet2.getCell(6,i).getContents()+ " "+sheet2.getCell(7,i).getContents()) && 
							        		   sheet2.getCell(1,i).getContents().equals("14:00->16:00")){
							        			int14.setText(sheet2.getCell(2,i).getContents());
							        			men14.setText(sheet2.getCell(3,i).getContents());
							        			ens14.setText(sheet2.getCell(4,i).getContents());
							        }
					        		if(b.getText().split(" \\(")[0].equals(sheet2.getCell(6,i).getContents()+ " "+sheet2.getCell(7,i).getContents()) && 
							        		   sheet2.getCell(1,i).getContents().equals("16:00->18:00")){
							        			int16.setText(sheet2.getCell(2,i).getContents());
							        			men16.setText(sheet2.getCell(3,i).getContents());
							        			ens16.setText(sheet2.getCell(4,i).getContents());
							        }
					        	}
					        }
					        else{

					        }
					    }
						catch (BiffException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();}
				    	
			   }};
			  
			  Sheet sheet = workbook.getSheet("Affectation");
			  Sheet sheet2 = workbook.getSheet("Login");
			  for(int i=1; i<sheet.getColumn(0).length;i++){  
					if(sheet.getCell(0,i).getContents().equals(LoginController.name + " " + LoginController.username)){					
						for(int j=0; j<sheet2.getColumn(0).length;j++){
								if((sheet.getCell(1,i).getContents()).equals(sheet2.getCell(3,j).getContents()+" "+sheet2.getCell(4,j).getContents())){
									JFXButton butLy = new JFXButton();
									butLy.setText(sheet.getCell(1,i).getContents()+ " ( "+sheet2.getCell(5,j).getContents()+" )");
									butLy.setPrefSize(339, 60);
									butLy.setAlignment(Pos.CENTER_LEFT);
									butLy.addEventHandler(MouseEvent.MOUSE_PRESSED,handlerLT);
									contenuL.getChildren().add(butLy);
									double h=listeLyTutController.listeLyTutP.getPrefHeight();
									listeLyTutController.listeLyTutP.setPrefHeight(h+60);
								}
					}
			  }}		
			  
		} catch (IOException e) {
			e.printStackTrace();
		}
		drawerList.open();
		
   }
}

